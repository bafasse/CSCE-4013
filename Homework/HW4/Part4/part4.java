// CSCE 4013- Applied Cryptography
// Part 4

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.util.HashMap;
import java.util.Random;


public class part4 {

    public static void main(String[] args) throws Exception{

        HashMap<Byte, String> hashes = new HashMap<>();
        while(true) {
            String msg = getRandomString(10);
            Byte hash = hash(msg);
            // check if hash is already contained in the map, i.e 
            // we have found a collision
            if (hashes.containsKey(hash)) {
                System.out.println("Collision Found ");
                System.out.println("First Message: " + hashes.get(hash));
                System.out.println("Second Message: " + msg);
                System.out.println("Total Messages: " +hashes.size() + 1);
                break;
            }

            System.out.println(msg);
            hashes.put(hash, msg);

        }
    }

    public static String getRandomString(int length) {
        String CHARS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
        StringBuilder randomStr = new StringBuilder();
        Random rnd = new Random();
        while (randomStr.length() < length) { // length of the random string.
            int index = (int) (rnd.nextFloat() * CHARS.length());
            randomStr.append(CHARS.charAt(index));
        }
        return randomStr.toString();

    }

    public static Byte hash(String msg) throws Exception{
        MessageDigest digest = MessageDigest.getInstance("SHA-256");
        byte[] hash = digest.digest(msg.getBytes(StandardCharsets.UTF_8));
        byte first = hash[0]; // First 8 bits of the message
        return first;

    }

}