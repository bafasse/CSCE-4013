import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.Base64;
import java.util.Scanner;
import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.lang.Object;
import java.io.*;
import java.nio.file.Paths;
import java.nio.file.Files;
import java.lang.IllegalArgumentException;


public class AES {

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

    public static void main(String[] args) throws Exception, IOException, IllegalArgumentException {

        final String secretKey = "RideTheLightning";
        // String originalString = "Metallica";

        String alice;
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter your message: ");
        alice = sc.nextLine();

        // String encryptedString = AES.encrypt(alice, secretKey) ;
        String enc = encrypt(alice, secretKey);

        FileOutputStream out = new FileOutputStream("mactext.txt");
        byte[] strToBytes = enc.getBytes();
        out.write(strToBytes);
        out.close();


        String data = "";
         try { 
             data = new String(Files.readAllBytes(Paths.get("mactext.txt"))); 
        } catch (IOException e) { 
            e.printStackTrace(); 
        } 
        
        // System.out.println("The encrypted message: " + data); 
        String dec = decrypt(data,secretKey);
        // System.out.println("The decrypted message: " + dec);

        if (alice.equals(dec)) {
            System.out.println("The encrypted message: " + data); 
            System.out.println("The decrypted message: " + dec);
            System.out.println("True");
        }

        else {
            System.out.println("False");
        }


        // System.out.println(alice);
        // System.out.println(encryptedString);
        // System.out.println(decryptedString);

    }
}