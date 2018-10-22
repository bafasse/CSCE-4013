// CSCE 4013- Applied Cryptography
// Part 3

// Part 1
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.Base64;
import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

// Part 2
import javax.crypto.Cipher;
import java.io.InputStream;
import java.security.*;
import java.util.Base64;
import static java.nio.charset.StandardCharsets.UTF_8;


public class Main {

    // ---------------------------------------------------------------------------------------
    // -------------------------- Part 1 -----------------------------------------------------
    // ---------------------------------------------------------------------------------------

    private static SecretKeySpec secretKey;
    private static byte[] key;

    public static void setKey(String myKey) {

        MessageDigest sha = null;
        try {
            key = myKey.getBytes("UTF-8");
            sha = MessageDigest.getInstance("SHA-256");
            key = sha.digest(key);
            key = Arrays.copyOf(key, 16);
            secretKey = new SecretKeySpec(key, "AES");
        }
        catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }

    public static String encrypt(String strToEncrypt, String secret) {

        try {
            setKey(secret);
            Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
            cipher.init(Cipher.ENCRYPT_MODE, secretKey);
            return Base64.getEncoder().encodeToString(cipher.doFinal(strToEncrypt.getBytes("UTF-8")));
        }
        catch (Exception e) {
            System.out.println("Error while encrypting: " + e.toString());
        }
        return null;
    }

    public static String decrypt(String strToDecrypt, String secret) {

        try {
            setKey(secret);
            Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5PADDING");
            cipher.init(Cipher.DECRYPT_MODE, secretKey);
            return new String(cipher.doFinal(Base64.getDecoder().decode(strToDecrypt)));
        }
        catch (Exception e) {
            System.out.println("Error while decrypting: " + e.toString());
        }
        return null;
    }

    // ---------------------------------------------------------------------------------------
    // -------------------------- Part 2 -----------------------------------------------------
    // ---------------------------------------------------------------------------------------

    public static KeyPair generateKeyPair() throws Exception {
        KeyPairGenerator generator = KeyPairGenerator.getInstance("RSA");
        generator.initialize(2048, new SecureRandom());
        KeyPair pair = generator.generateKeyPair();
    
        return pair;
    }

    public static String encrypt(String plainText, PublicKey publicKey) throws Exception {
        Cipher encryptCipher = Cipher.getInstance("RSA");
        encryptCipher.init(Cipher.ENCRYPT_MODE, publicKey);
    
        byte[] cipherText = encryptCipher.doFinal(plainText.getBytes(UTF_8));
    
        return Base64.getEncoder().encodeToString(cipherText);
    }

    public static String decrypt(String cipherText, PrivateKey privateKey) throws Exception {
        byte[] bytes = Base64.getDecoder().decode(cipherText);
    
        Cipher decriptCipher = Cipher.getInstance("RSA");
        decriptCipher.init(Cipher.DECRYPT_MODE, privateKey);
    
        return new String(decriptCipher.doFinal(bytes), UTF_8);
    }

    public static String sign(String plainText, PrivateKey privateKey) throws Exception {
        Signature privateSignature = Signature.getInstance("SHA256withRSA");
        privateSignature.initSign(privateKey);
        privateSignature.update(plainText.getBytes(UTF_8));
    
        byte[] signature = privateSignature.sign();
    
        return Base64.getEncoder().encodeToString(signature);
    }

    public static boolean verify(String plainText, String signature, PublicKey publicKey) throws Exception {
        Signature publicSignature = Signature.getInstance("SHA256withRSA");
        publicSignature.initVerify(publicKey);
        publicSignature.update(plainText.getBytes(UTF_8));
    
        byte[] signatureBytes = Base64.getDecoder().decode(signature);
    
        return publicSignature.verify(signatureBytes);
    }

    public static void main(String[] args) throws Exception {

        long a1 = 0;
        long a2 = 0;
        
        // ---------------------------------------------------------------------------------------
        // -------------------------- Part 1 -----------------------------------------------------
        // ---------------------------------------------------------------------------------------

        for (int i = 0; i < 100; ++i) {

            long start = System.currentTimeMillis();

            final String secretKey = "RideTheLightning";
            String originalString = "Metallica";
            String encryptedString = Main.encrypt(originalString, secretKey);
            String decryptedString = Main.decrypt(encryptedString, secretKey);

            long end = System.currentTimeMillis();
            long duration = end - start; 
            a1 += duration;

            // System.out.println(originalString);
            // System.out.println(encryptedString);
            // System.out.println(decryptedString);
        }
        
        System.out.println("HMAC Time: " + a1 + "ms");

        for (int i = 0; i < 100; ++i) {

            // ---------------------------------------------------------------------------------------
            // -------------------------- Part 2 -----------------------------------------------------
            // ---------------------------------------------------------------------------------------

            long start, end, duration = 0;
            start = System.currentTimeMillis();

            KeyPair pair = generateKeyPair();

            String signature = sign("foobar", pair.getPrivate());

            //Let's check the signature
            boolean isCorrect = verify("foobar", signature, pair.getPublic());
            // System.out.println("Signature correct: " + isCorrect);

            end = System.currentTimeMillis();
            duration = end - start;
            a2 += duration;
        }

        System.out.println("RSA Time: " + a2 + "ms");
    }
}