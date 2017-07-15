package ua.nure.sereda.Practice3;

import com.sun.org.apache.xerces.internal.impl.dv.util.HexBin;

import java.io.UnsupportedEncodingException;
import java.security.*;


public class Part4 {

    public static String hash(String input, String algorithm) {
        try {
            MessageDigest digest = MessageDigest.getInstance(algorithm);
            digest.update(input.getBytes("UTF-8"));
            byte[] hash = digest.digest();
            return HexBin.encode(hash);
        } catch (NoSuchAlgorithmException ex){
            return "No such algorithm!!!";
        } catch (UnsupportedEncodingException e) {
            return "";
        }
    }

    public static void main(String[] args) throws NoSuchAlgorithmException {
        System.out.println(hash("password md5", "MD5"));
        System.out.println(hash("password sha256", "SHA-256"));
        System.out.println(hash("password sha512", "SHA-512"));
    }

}