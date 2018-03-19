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

    public static byte[] encryptTextRSA(String password) throws Exception {
        System.out.println("\nStart encryption");
        byte[] plainText = password.getBytes("UTF8");
        cipher.init(Cipher.ENCRYPT_MODE, key.getPublic());
        byte[] cipherText = cipher.doFinal(plainText);
        System.out.println(new String(cipherText, "UTF8"));
        System.out.println("Finish encryption: ");
        return cipherText;
    }

    public static byte[] decryptCipherTextRSA(byte[] cipherText) throws Exception {
        System.out.println("\nStart decryption");
        cipher.init(Cipher.DECRYPT_MODE, key.getPrivate());
        byte[] newPlainText = cipher.doFinal(cipherText);
        System.out.println("Finish decryption: ");
        System.out.println(new String(newPlainText, "UTF8"));
        return newPlainText;
    }
}