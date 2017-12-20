package security;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 *  @author Tom KAUFFELD
 *  @version 1
 *  @see java.security.MessageDigest
 */
public class PasswordHash {

    /**
     * Encrypts an string using the SHA-256 algorithm
     * @param input the string to be encrypted
     * @return an encryped string of the input string
     */
    public static String sha256( String input){
        MessageDigest md;
        try {
            md = MessageDigest.getInstance( "SHA-256");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return null;
        }
        byte[] result = md.digest( input.getBytes());
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < result.length; i++)
            sb.append( Integer.toString(( result[i] & 0xff) + 0x100, 16).substring(1));
        return sb.toString();
    }

    /**
     * Checks if the input string is the same as the one that's encrypted
     * @param input the unencrypted string to verify with
     * @param checkSum the encrypted string to verify it to
     * @return false if the strings are not the same and true if they are
     */
    public static boolean verify( String input, String checkSum){
        String hash = sha256( input);
        return hash.equals(checkSum);
    }
}
