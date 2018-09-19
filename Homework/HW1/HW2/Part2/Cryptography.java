import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.security.*;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.util.Base64;

// import org.apache.commons.codec.binary.Base64;

public class Cryptography {

    private Cipher cipher;

    public Cryptography() throws NoSuchAlgorithmException, NoSuchPaddingException {
        this.cipher = Cipher.getInstance("RSA");
    }

    public PrivateKey getPrivateKey(String file) throws Exception {
        byte[] keyBytes = Files.readAllBytes(new File(file).toPath());
        PKCS8EncodedKeySpec spec = new PKCS8EncodedKeySpec(keyBytes);
        KeyFactory factory = KeyFactory.getInstance("RSA");

        return factory.generatePrivate(spec);
    }

    public PublicKey getPublicKey(String file) throws Exception {
        byte[] keyBytes = Files.readAllBytes(new File(file).toPath());
        X509EncodedKeySpec spec = new X509EncodedKeySpec(keyBytes);
        KeyFactory factory = KeyFactory.getInstance("RSA");

        return factory.generatePublic(spec);
    }

    // public void encryption(byte[] input, File output, PublicKey key) throws IOException, GeneralSecurityException {
    //     this.cipher.init(Cipher.ENCRYPT_MODE, key);
    //     toFile(output, this.cipher.doFinal(input));
    // }

    // public void decryption(byte[] input, File output, PrivateKey key) throws IOException, GeneralSecurityException {
    //     FileOutputStream out = new FileOutputStream(output);
    //     this.cipher.init(Cipher.DECRYPT_MODE, key);
    //     toFile(output, this.cipher.doFinal(input));
    // }

    // private void toFile(File output, byte[] toWrite) throws IllegalBlockSizeException, BadPaddingException, IOException {
    //     FileOutputStream out = new FileOutputStream(output);
    //     out.write(toWrite);
    //     out.flush();
    //     out.close();
    // }

    public String encryptText(String msg, PublicKey key) throws NoSuchAlgorithmException, NoSuchPaddingException, IllegalBlockSizeException, BadPaddingException, InvalidKeyException {
        byte[] message = msg.getBytes(StandardCharsets.UTF_8);
        this.cipher.init(Cipher.ENCRYPT_MODE, key);
        return Base64.getEncoder().encodeToString(message);
    }

    public String decryptText(String msg, PrivateKey key) throws InvalidKeyException, UnsupportedEncodingException, IllegalBlockSizeException, BadPaddingException {
        this.cipher.init(Cipher.DECRYPT_MODE, key);
        return new String(cipher.doFinal(Base64.getDecoder().decode(msg)));
    }

    public byte[]  getFileinBytes(File f) throws IOException {
        FileInputStream input = new FileInputStream(f);
        byte[] fbytes = new byte[(int) f.length()];
        input.read(fbytes);
        input.close();

        return fbytes;
    }

    public static void main(String[] args) throws Exception {
        Cryptography c = new Cryptography();
        PrivateKey privKey = c.getPrivateKey("privkey.txt");
        PublicKey pubKey = c.getPublicKey("pubkey.txt");

        String msg = "Cryptography is fun!";
        String encryptedMSG = c.encryptText(msg, pubKey);
        String decryptedMSG = c.decryptText(encryptedMSG, privKey);
        System.out.println("Original Message: "    + msg + 
                           "\nEncrypted Message: " + encryptedMSG +
                           "\nDecrypted Message: " + decryptedMSG);

    }
}

