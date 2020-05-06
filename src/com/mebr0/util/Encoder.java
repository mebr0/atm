package com.mebr0.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Class that encode pins for {@link com.mebr0.database.Account}
 *
 * @author A.Yergali
 * @version 1.0
 */
public abstract class Encoder {

    private static MessageDigest digest;

    private final static Logger LOG = Logger.getInstance();

    private Encoder() {
        throw new AssertionError("No " + getClass().getSimpleName() + " instances for you!");
    }

    static {
        try {
            digest = MessageDigest.getInstance("MD5");
        }
        catch (NoSuchAlgorithmException e) {
            LOG.error("MD5 algorithm not found");
        }
    }

    public static String encode(String string) {
        digest.update(string.getBytes());

        StringBuilder builder = new StringBuilder();

        for (byte aByte : digest.digest()) {
            builder.append(Integer.toString((aByte & 0xff) + 0x100, 16).substring(1));
        }

        return builder.toString();
    }
}
