import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.PrivateKey;
import java.security.PublicKey;

public class KeyGenerator {

    private KeyPairGenerator keyGen;
    private KeyPair keyPair;
    private PrivateKey privKey;
    private PublicKey pubKey;

    public KeyGenerator(int keyLength) throws NoSuchAlgorithmException, NoSuchProviderException {
        this.keyGen = KeyPairGenerator.getInstance("RSA");
        this.keyGen.initialize(keyLength);
    }

    public void Keys() {
        this.keyPair = this.keyGen.generateKeyPair();
        this.privKey = keyPair.getPrivate();
        this.pubKey = keyPair.getPublic();
    }

    public PrivateKey getPrivKey() {
        return this.privKey;
    }

    public PublicKey getPubKey() {
        return this.pubKey;
    }

    public void toFile(String path, byte[] key) throws IOException {
        File f = new File(path);
        f.getParentFile().mkdirs();

        FileOutputStream out = new FileOutputStream(f);
        out.write(key);
        out.flush();
        out.close();
    }

    public static void main(String[] args) {
        KeyGenerator key;

        try {
            key = new KeyGenerator(1024);
            key.toFile("pubkey.txt", key.getPubKey().getEncoded());
            key.toFile("privkey.txt", key.getPrivKey().getEncoded());
        } catch (NoSuchAlgorithmException | NoSuchProviderException e) {
            System.err.println(e.getMessage());
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }
}