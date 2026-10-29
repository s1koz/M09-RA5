
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.HexFormat;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;

public class Hashes {

    public int npass = 0;

    public String getSHA512AmbSalt(String pw, String salt) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-512");
            md.update(salt.getBytes());
            return HexFormat.of().formatHex(md.digest(pw.getBytes()));
        } catch (NoSuchAlgorithmException e) {
            return null;
        }
    }

    public String getPBKDF2AmbSalt(String pw, String salt) {
        PBEKeySpec spec = null;

        try {
            spec = new PBEKeySpec(pw.toCharArray(),salt.getBytes(),65536,256);

            SecretKeyFactory skf = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA256");

            return HexFormat.of().formatHex(skf.generateSecret(spec).getEncoded());

        } catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
            return null;
        } finally {
            if (spec != null) {
                spec.clearPassword();
            }
        }
    }

    public String forcaBruta(String alg, String hashObjetivo, String salt) {

        npass = 0;

        char[] charset = "abF!".toCharArray();
        int maxLen = 6;

        for (int len = 1; len <= maxLen; len++) {
            char[] current = new char[len];

            String resultado = probarCombinaciones(
                current, 0, charset, alg, hashObjetivo, salt
            );

            if (resultado != null) {
                return resultado;
            }
        }

        return null;
    }

    private String probarCombinaciones(
            char[] current, int index, char[] charset,
            String alg, String hashObjetivo, String salt) {

        if (index == current.length) {
            npass++;

            String pw = new String(current);
            String hashGenerado;

            if (alg.equals("SHA-512")) {
                hashGenerado = getSHA512AmbSalt(pw, salt);
            } else if (alg.equals("PBKDF2")) {
                hashGenerado = getPBKDF2AmbSalt(pw, salt);
            } else {
                return null;
            }

            if (hashGenerado != null && hashGenerado.equals(hashObjetivo)) {
                return pw;
            }

            return null;
        }

        for (char c : charset) {
            current[index] = c;

            String resultado = probarCombinaciones(
                current, index + 1, charset, alg, hashObjetivo, salt
            );

            if (resultado != null) {
                return resultado;
            }
        }

        return null;
    }

    public String getInterval(long t1, long t2) {

        long diff = t2 - t1;

        long dias = diff / (1000L * 60 * 60 * 24);
        long horas = (diff / (1000L * 60 * 60)) % 24;
        long minutos = (diff / (1000L * 60)) % 60;
        long segundos = (diff / 1000L) % 60;
        long milisegundos = diff % 1000L;

        return String.format(
            "%d dies / %d hores / %d minuts / %d segons / %d millis",
            dias, horas, minutos, segundos, milisegundos
        );
    }

    public static void main(String[] args) throws Exception {
        String salt = "qpowelruañslkdfjz";
        String pw = "aaabF!";

        Hashes h = new Hashes();

        String[] aHashes = { h.getSHA512AmbSalt(pw, salt),
                            h.getPBKDF2AmbSalt(pw, salt) };
        String pwTrobat = null;
        String[] algorismes = {"SHA-512", "PBKDF2"};

        for(int i=0; i< aHashes.length; i++){
            System.out.printf("===============================\n");
            System.out.printf("Algorisme: %s\n", algorismes[i]);
            System.out.printf("Hash: \n%s\n", aHashes[i]);
            System.out.printf("---------------------------\n");
            System.out.printf("-- Inici de força bruta ---\n");

            long t1 = System.currentTimeMillis();
            pwTrobat = h.forcaBruta(algorismes[i], aHashes[i], salt);
            long t2 = System.currentTimeMillis();

            System.out.printf("Pass : %s\n", pwTrobat);
            System.out.printf("Provats: %d\n", h.npass);
            System.out.printf("Temps : %s\n", h.getInterval(t1, t2));
            System.out.printf("---------------------------\n\n");
        }
    }
}
