package ua.nure.sereda.SummaryTask4.utils;

import org.apache.commons.codec.digest.DigestUtils;

/**
 * Created by Vladyslav.
 */
public class PassEncryption {
    public static String encrypt(String password) {
        return DigestUtils.sha1Hex(password);
    }
}
