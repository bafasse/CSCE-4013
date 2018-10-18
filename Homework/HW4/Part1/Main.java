// CSCE 4013- Applied Cryptography
// HW4 Part 1
import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Scanner;
import java.io.*;

public class Main {

    public static String hmac (String msg, String keyString, String algo) {
        String digest = null;
        try {
            SecretKeySpec key = new SecretKeySpec((keyString).getBytes("UTF-8"), algo);
            // System.out.println("Key: " + key);
            Mac mac = Mac.getInstance(algo);
            mac.init(key);

            byte[] bytes = mac.doFinal(msg.getBytes("ASCII"));

            StringBuffer hash = new StringBuffer();
            for (int i = 0; i < bytes.length; ++i) {
                String hex = Integer.toHexString(0xFF & bytes[i]);
                if (hex.length() == 1) {
                    hash.append('0');
                }
                hash.append(hex);
            }
            digest = hash.toString();
        } catch (UnsupportedEncodingException e) {
        } catch (InvalidKeyException e) {
        } catch (NoSuchAlgorithmException e){
        }
        return digest;
    }

    public static void main(String[] args) throws Exception, IOException{
        // System.out.println("Hello World");
        String alice;
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter your message: ");
        alice = sc.nextLine();
        String hmac = hmac(alice, "key", "HmacSHA256");

        FileOutputStream out = new FileOutputStream("mactext.txt");
        byte[] strToBytes = hmac.getBytes();
        out.write(strToBytes);
        out.close();
        // System.out.println("Your message is: ");
        // System.out.println(hmac(alice, "key", "HmacSHA256"));
    }
}