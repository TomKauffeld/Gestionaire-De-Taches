package security;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class PasswordHash {

    public static String sha256( String input) throws NoSuchAlgorithmException{
        MessageDigest md = MessageDigest.getInstance( "SHA-256");
        byte[] result = md.digest( input.getBytes());
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < result.length; i++)
            sb.append( Integer.toString(( result[i] & 0xff) + 0x100, 16).substring(1));
        return sb.toString();
    }

    public static boolean verify( String input, String checkSum) throws NoSuchAlgorithmException{
        String hash = sha256( input);
        return hash.equals(checkSum);
    }
}
