package ua.nure.sereda.Photostudio.utils.encoding;

import org.apache.commons.codec.digest.DigestUtils;

/**
 * Created by Vladyslav.
 */
public class Codec {
    public static String md5(String password) {
        return DigestUtils.md5Hex(password);
    }
}
