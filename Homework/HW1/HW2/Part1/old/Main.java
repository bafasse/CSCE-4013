import java.io.UnsupportedEncodingException;
import java.security.*;
import java.util.Arrays;
import java.util.Base64;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
// import java.util.Base64;

public class Main {

    public static String encrypt(String plainText) throws Exception {

        try {

            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");

            KeyGenerator keygen = KeyGenerator.getInstance("AES");
            keygen.init(192);
            byte[] key = keygen.generateKey().getEncoded();
            SecretKeySpec secKeySpec = new SecretKeySpec(key, "AES");
            // SecretKey skey = key.generateKey();

            byte[] iv = new byte[128/8];
            SecureRandom srandom = new SecureRandom();
            srandom.newBytes(iv);
            IvParameterSpec ivSpec = new IvParameterSpec(iv);

            cipher.init(Cipher.ENCRYPT_MODE, secKeySpec, ivSpec);

            // byte[] cipherText = cipher.doFinal(value.getBytes());
            // return Base64.encodeBase64String(cipherText);

        } catch(Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }

    // public void ivGeneration() {
    //     byte[] iv = new byte[192];
    //     srandom.newBytes(iv);
    //     IvParameterSpec spec = new IvParameterSpec(iv);
    // }

    // public void keyGeneration() {
    //     KeyGenerator key = KeyGenerator.getInstance("AES");
    //     SecretKey skey = key.generateKey();
    // }

    // public void cipher() {
    //     ivGeneration();
    //     keyGeneration();
    //     Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
    //     cipher.init(Cipher.ENCRYPT_MODE, skey, spec);   
    // }

    public static void main(String[] args) {
        
          
    }
}