package security.rsa;


import javax.crypto.Cipher;
import javax.crypto.NoSuchPaddingException;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;

public class RSA {
    private static KeyPair key;
    private static Cipher cipher;

    static {
        try {
            generateRsaKey();
            cipher = getRsaCipher();
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("\n" + cipher.getProvider().getInfo());
    }

    private static void generateRsaKey() throws Exception {
        System.out.println("\nStart generating RSA key");
        KeyPairGenerator keyGen = KeyPairGenerator.getInstance("RSA");
        keyGen.initialize(1024);
        key = keyGen.generateKeyPair();
        System.out.println("Finish generating RSA key");
    }

    private static Cipher getRsaCipher() throws NoSuchPaddingException, NoSuchAlgorithmException {
        return Cipher.getInstance("RSA/ECB/PKCS1Padding");
    }

    public static byte[] encryptTextRSA(String string) throws Exception {
        System.out.println("\nStart encryption");
        byte[] stringBytes = string.getBytes("UTF8");
        cipher.init(Cipher.ENCRYPT_MODE, key.getPublic());
        byte[] cipherStringBytes = cipher.doFinal(stringBytes);
        System.out.println("Finish encryption: ");
        return cipherStringBytes;
    }

    public static byte[] decryptCipherTextRSA(byte[] cipherStringBytes) throws Exception {
        System.out.println("\nStart decryption");
        cipher.init(Cipher.DECRYPT_MODE, key.getPrivate());
        byte[] stringBytes = cipher.doFinal(cipherStringBytes);
        System.out.println("Finish decryption: ");
        return stringBytes;
    }
}