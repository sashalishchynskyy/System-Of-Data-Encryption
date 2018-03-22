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
        KeyGenerator keyGen = KeyGenerator.getInstance("DES");
        keyGen.init(56);
        return keyGen.generateKey();
    }

    public static byte[] encryptTextDES(String string) throws Exception {
        System.out.println("\nStart encryption");
        byte[] stringBytes = string.getBytes("UTF8");
        cipher.init(Cipher.ENCRYPT_MODE, key);
        byte[] cipherByteString = cipher.doFinal(stringBytes);
        System.out.println("Finish encryption");
        return cipherByteString;
    }

    public static byte[] decryptCipherTextDES(byte[] cipherByteString) throws Exception {
        System.out.println("\nStart decryption");
        cipher.init(Cipher.DECRYPT_MODE, key);
        byte[] stringBytes = cipher.doFinal(cipherByteString);
        System.out.println("Finish decryption");
        return stringBytes;
    }
}