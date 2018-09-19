import java.security.*;
import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

public class AESUtils {

    public static final int AES_128 = 128;
    public static final int AES_192 = 192;
    public static final int AES_256 = 256;
    public static final String NoPadding = "AES/CBC/NoPadding";
    public static final String PKCS5Padding = "AES/CBC/PKCS5Padding";
    public static final String ISO10126Padding = "AES/CBC/ISO10126Padding";
    String key;
    byte[] iv;
    int type;
    String modeAndPadding;
    SecretKeySpec keySpec;
    Cipher encryptCipher;
    Cipher decryptCipher;

    public AESUtils(String key, int type, String modeAndPadding) throws Exception, NoSuchAlgorithmException {

        this.key = key;
        iv = getIV();
        this.type = type;
        this.modeAndPadding = modeAndPadding;
        KeyGenerator keyGen = KeyGenerator.getInstance("AES");

        SecureRandom rand = SecureRandom.getInstance("SHA1PRNG");
        rand.setSeed(key.getBytes());
        keyGen.init(type, rand);
        SecretKey secretKey = keyGen.generateKey();
        byte[] encodeFormat = secretKey.getEncoded();
        keySpec = new SecretKeySpec(encodeFormat, "AES");
        encryptCipher = Cipher.getInstance(modeAndPadding);
        if(iv != null) {
            encryptCipher.init(1, keySpec, new IvParameterSpec(iv));
        }

        else {
            encryptCipher.init(1, keySpec);
        }

        decryptCipher = Cipher.getInstance(modeAndPadding);

        if(iv != null) {
            decryptCipher.init(2, keySpec, new IvParameterSpec(iv));
        }

        else {
            decryptCipher.init(2, keySpec);
        }
    }

    public byte[] encryption(byte[] plainText) throws Exception {

        byte[] encrypted = encryptCipher.doFinal(plainText);
        return encrypted;
    }

    public byte[] decryption(byte[] cipherText) throws Exception {
        
        byte[] plainText = decryptCipher.doFinal(cipherText);
        return plainText;
    }

    public static final byte[] getIV() throws Exception {

        return "1234567812345678".getBytes("UTF-8");
    }

}