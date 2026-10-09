
import java.nio.charset.StandardCharsets;
import java.security.SecureRandom;
import java.util.Arrays;
import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

public class XifradorAES {

    public static final String ALGORSME_XIFRAT = "AES";
    public static final String ALGORISME_HASH = "SHA-256";
    public static final String FORMAT_AES = "AES/CBC/PKCS5Padding";

    private static final int MIDA_IV = 16;
    private static final byte[] iv = new byte[MIDA_IV];
    private static final String CLAU = "374647sj";

    private static SecureRandom sr = new SecureRandom();

    public static void main(String[] args) {

        String msgs[] = {"Lorem ipsum dicet",
                    "Hola Andrés cómo está tu cuñado",
                    "Àgora ïlla Ôtto"};

        for (int i = 0; i < msgs.length; i++) {
            String msg = msgs[i];

            byte[] bXifrats = null;
            String desxifrat = "";
            try {
                bXifrats = xifraAES(msg, CLAU);
                desxifrat = desxifraAES(bXifrats, CLAU);
            } catch (Exception e) {
                System.err.println("Error de xifrat: " + e.getLocalizedMessage());
            }

            System.out.println("-----------------");
            System.out.println("Msg: " + msg);
            System.out.println("Enc:" + new String(bXifrats));
            System.out.println("DEC: " + desxifrat);
        }
        
    }

    public static byte[] xifraAES(String msg, String password) throws Exception {

        byte[] bytes = java.security.MessageDigest.getInstance("SHA-256").digest(password.getBytes(StandardCharsets.UTF_8));
        byte[] clauAES = Arrays.copyOf(bytes, 16);

        SecretKeySpec secretKeySpec = new SecretKeySpec(clauAES, "AES");
        
        byte[] iv = new byte[MIDA_IV];
        sr.nextBytes(iv);

        IvParameterSpec ivparameterspec = new IvParameterSpec(iv);

        Cipher cipher = Cipher.getInstance(FORMAT_AES);
        cipher.init(Cipher.ENCRYPT_MODE, secretKeySpec, ivparameterspec);

        byte[] msgXifrat = cipher.doFinal(msg.getBytes(StandardCharsets.UTF_8));

        byte[] result = new byte[iv.length + msgXifrat.length];

        System.arraycopy(iv, 0, result, 0, iv.length);
        System.arraycopy(msgXifrat, 0, result, iv.length, msgXifrat.length);

        return result;

    }

    public static String desxifraAES(byte[] bMsgXifrat, String password) throws Exception {

        byte[] bytes = java.security.MessageDigest.getInstance("SHA-256").digest(password.getBytes(StandardCharsets.UTF_8));
        byte[] clauAES = Arrays.copyOf(bytes, 16);

        SecretKeySpec secretKeySpec = new SecretKeySpec(clauAES, "AES");

        byte[] iv = Arrays.copyOfRange(bMsgXifrat, 0, MIDA_IV);
        byte[] msgXifrat = Arrays.copyOfRange(bMsgXifrat, MIDA_IV, bMsgXifrat.length);

        IvParameterSpec ivParameterSpec = new IvParameterSpec(iv);
        

        Cipher cipher = Cipher.getInstance(FORMAT_AES);
        cipher.init(Cipher.DECRYPT_MODE, secretKeySpec, ivParameterSpec);

        byte[] msgDesxifrat = cipher.doFinal(msgXifrat);

        return new String(msgDesxifrat, StandardCharsets.UTF_8);

    }
}