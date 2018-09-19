import java.io.PrintStream;
import java.util.Arrays;
// import com.sun.org.apache.xml.internal.serializer.utils.Utils;

public class Bob {

    public static void main(String[] args) {
        String key = Utils.read("key.txt");
        String encode = Utils.read("ctext.txt");
        AESUtils aes = new AESUtils(key, 192, "AES/CBC/PKC5Padding");

        System.out.println("Hexadecimal: " + encode);
        System.out.println("Encryption Byte Array: " + Arrays.toString(aes.hexStringToBytes(encode)));
        byte[] decrypt = utils.decrypt(Utils.hexStringToBytes(encode));
        String decode = new String(decrypt, "UTF-8");
        System.out.println("Your message is: " + decode);
    }
}