package iticbcn.xifratge;

import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.PrivateKey;
import java.security.PublicKey;

import javax.crypto.Cipher;

public class ClauPublica {
    public KeyPair generaParellClausRSA() throws Exception {
        KeyPairGenerator generator = KeyPairGenerator.getInstance("RSA");
        generator.initialize(2048);
        return generator.generateKeyPair();
    }

    public byte[] xifraRSA(String msg, PublicKey clauPublica) throws Exception {
        Cipher xifrador = Cipher.getInstance("RSA");
        xifrador.init(Cipher.ENCRYPT_MODE, clauPublica);
        return xifrador.doFinal(msg.getBytes("UTF-8"));
    }

    public String desxifraRSA(byte[] msgXifrat, PrivateKey clauPrivada) throws Exception {
        Cipher desxifrador = Cipher.getInstance("RSA");
        desxifrador.init(Cipher.DECRYPT_MODE, clauPrivada);
        byte[] msgDesxifrat = desxifrador.doFinal(msgXifrat);
        return new String(msgDesxifrat, "UTF-8");
    }
}
