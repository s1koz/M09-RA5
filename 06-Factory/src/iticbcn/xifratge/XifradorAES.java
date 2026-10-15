package iticbcn.xifratge;

import java.nio.charset.StandardCharsets;
import java.security.SecureRandom;
import java.util.Arrays;
import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

public class XifradorAES implements Xifrador {

    public final String ALGORSME_XIFRAT = "AES";
    public final String ALGORISME_HASH = "SHA-256";
    public final String FORMAT_AES = "AES/CBC/PKCS5Padding";

    private final int MIDA_IV = 16;
    private final SecureRandom sr = new SecureRandom();

    public byte[] xifraAES(String msg, String password)
            throws Exception {

        byte[] bytes = java.security.MessageDigest
                .getInstance(ALGORISME_HASH)
                .digest(password.getBytes(StandardCharsets.UTF_8));

        byte[] clauAES = Arrays.copyOf(bytes, 16);
        SecretKeySpec secretKeySpec = new SecretKeySpec(clauAES, ALGORSME_XIFRAT);

        byte[] iv = new byte[MIDA_IV];
        sr.nextBytes(iv);

        IvParameterSpec ivParameterSpec = new IvParameterSpec(iv);

        Cipher cipher = Cipher.getInstance(FORMAT_AES);
        cipher.init(Cipher.ENCRYPT_MODE, secretKeySpec, ivParameterSpec);

        byte[] msgXifrat = cipher.doFinal(msg.getBytes(StandardCharsets.UTF_8));

        byte[] resultat = new byte[iv.length + msgXifrat.length];

        System.arraycopy(iv, 0, resultat, 0, iv.length);
        System.arraycopy(msgXifrat, 0, resultat, iv.length, msgXifrat.length);

        return resultat;
    }

    public String desxifraAES(byte[] bMsgXifrat, String password)
            throws Exception {

        byte[] bytes = java.security.MessageDigest
                .getInstance(ALGORISME_HASH)
                .digest(password.getBytes(StandardCharsets.UTF_8));

        byte[] clauAES = Arrays.copyOf(bytes, 16);
        SecretKeySpec secretKeySpec = new SecretKeySpec(clauAES, ALGORSME_XIFRAT);

        byte[] iv = Arrays.copyOfRange(bMsgXifrat, 0, MIDA_IV);
        byte[] msgXifrat = Arrays.copyOfRange(
                bMsgXifrat, MIDA_IV, bMsgXifrat.length);

        IvParameterSpec ivParameterSpec = new IvParameterSpec(iv);

        Cipher cipher = Cipher.getInstance(FORMAT_AES);
        cipher.init(Cipher.DECRYPT_MODE, secretKeySpec, ivParameterSpec);

        byte[] msgDesxifrat = cipher.doFinal(msgXifrat);

        return new String(msgDesxifrat, StandardCharsets.UTF_8);
    }

    @Override
    public TextXifrat xifra(String msg, String clau)
            throws ClauNoSuportada {

        try {
            return new TextXifrat(xifraAES(msg, clau));
        } catch (Exception e) {
            System.err.println("Error de xifrat: " + e.getLocalizedMessage());
            System.exit(1);
            return null;
        }
    }

    @Override
    public String desxifra(TextXifrat xifrat, String clau)
            throws ClauNoSuportada {

        try {
            return desxifraAES(xifrat.getBytes(), clau);
        } catch (Exception e) {
            throw new ClauNoSuportada(
                    "Error de desxifratge AES: " + e.getLocalizedMessage());
        }
    }
}
