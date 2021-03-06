import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

public class HMAC {

    public static void main(String[] args) throws NoSuchAlgorithmException, InvalidKeyException {
        String secretKey = "This is the secret";
        String hashMessage = "This is the hash message";
        System.out.println(HMAC.hash(secretKey, hashMessage, "HmacSHA256"));
        System.out.println(HMAC.hashAndGetHexStringValue(secretKey, hashMessage, "HmacSHA256"));
        System.out.println(HMAC.hashAndConvertToBase64String(secretKey, hashMessage, "HmacSHA256"));
    }

    public static byte[] hash(String key, String message, String algorithm) throws NoSuchAlgorithmException, InvalidKeyException {
        byte[] keyBytes = key.getBytes(StandardCharsets.UTF_8);
        byte[] messageBytes = message.getBytes(StandardCharsets.UTF_8);

        SecretKeySpec secretKey = new SecretKeySpec(keyBytes, algorithm);

        Mac hmac = Mac.getInstance(algorithm);
        hmac.init(secretKey);
        return hmac.doFinal(messageBytes);
    }

    public static String hashAndGetHexStringValue(String key, String message, String algorithm) throws NoSuchAlgorithmException, InvalidKeyException {
        byte[] hash = hash(key, message, algorithm);
        StringBuilder hashVal = new StringBuilder();
        for (byte b : hash) {
            String hex = Integer.toHexString(0xFF & b);
            if (hex.length() == 1) {
                hashVal.append('0');
            }
            hashVal.append(hex);
        }
        return hashVal.toString();
    }

    public static String hashAndConvertToBase64String(String key, String message, String algorithm) throws NoSuchAlgorithmException, InvalidKeyException {
        byte[] hash = hash(key, message, algorithm);
        return Base64.getEncoder().encodeToString(hash);
    }

}