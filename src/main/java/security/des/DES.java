package security.des;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import java.security.Key;

public class DES {
    private static Cipher cipher;
    private static Key key;

    static {
        try {
            key = getDESKey();
            cipher = Cipher.getInstance("DES/ECB/PKCS5Padding"); // get a DES cipher object and print the provider
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("\n" + cipher.getProvider().getInfo());
    }

    private static Key getDESKey() throws Exception {
        System.out.println("\nStart generating DES key");
        KeyGenerator keyGen = KeyGenerator.getInstance("DES");
        keyGen.init(56);
        System.out.println("Finish generating DES key");
        return keyGen.generateKey();
    }

    public static byte[] encryptTextDES(String password) throws Exception {
        System.out.println("\nStart encryption");
        byte[] plainText = password.getBytes("UTF8");
        cipher.init(Cipher.ENCRYPT_MODE, key);
        byte[] cipherText = cipher.doFinal(plainText);
        System.out.println("Finish encryption: ");
        return cipherText;
    }

    public static byte[] decryptCipherTextDES(byte[] cipherText) throws Exception {
        System.out.println("\nStart decryption");
        cipher.init(Cipher.DECRYPT_MODE, key);
        byte[] newPlainText = cipher.doFinal(cipherText);
        System.out.println("Finish decryption: ");
        return newPlainText;
    }
}