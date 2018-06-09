package security.mdfive;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public final class MessageDigest5 {

    private static final int RADIX = 16;

    private MessageDigest5() {
    }

    @Override
    public String toString() {
        return "MessageDigest5";
    }

    public static String hashedString(String plainText) throws NoSuchAlgorithmException {
        MessageDigest messageDigest = MessageDigest.getInstance("MD5");
        messageDigest.reset();
        messageDigest.update(plainText.getBytes());
        byte[] digest = messageDigest.digest();
        BigInteger bigInt = new BigInteger(1, digest);
        return bigInt.toString(RADIX);
    }
}