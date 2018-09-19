import java.util.Arrays;
import java.util.Scanner;
// import java.util;
// import com.sun.org.apache.xml.internal.serializer.utils.Utils;

public class Alice {

    public Alice() {}

    public static void main(String[] args) throws Exception {

        System.out.println("Enter the message you'd like encrypted: ");
        Scanner alice = new Scanner(System.in);
        String msg = alice.nextLine();
        alice.close();

        String key = Utils.read("key.txt");
        AESUtils aes = new AESUtils(key, 192, "AES/CBC/PKC5Padding");
        byte[] encrypt = aes.encryption(msg.getBytes());
        System.out.println("The array after encryption is: " + Arrays.toString(encrypt));

        String encode = Utils.bytesToHexString(encrypt);
        System.out.println("Hexadecimal: " + encode);
        Utils.save("ctext.txt", encode);
    }
}