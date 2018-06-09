package security.mauthorization;

import javax.crypto.KeyGenerator;
import javax.crypto.Mac;
import javax.crypto.SecretKey;
import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

public final class MessageAuthentication {

    private static final String H_MAC_MD5 = "HmacMD5";

    private MessageAuthentication() {
    }

    public static String hashedString(String plainText) throws NoSuchAlgorithmException, UnsupportedEncodingException {
        System.out.println("\nStart generating key");
        KeyGenerator keyGen = KeyGenerator.getInstance(H_MAC_MD5);
        SecretKey md5Key = keyGen.generateKey();
        System.out.println("Finish generating key");

        Mac mac = Mac.getInstance(H_MAC_MD5);
        System.out.println("\n" + mac.getProvider().getInfo());
        try {
            mac.init(md5Key);
            mac.update(plainText.getBytes("UTF8"));
        } catch (UnsupportedEncodingException | InvalidKeyException e) {
            e.printStackTrace();
        }
        return new String(mac.doFinal(), "UTF8");
    }
}