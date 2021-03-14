package com.kangmin.blog.formbean;

import java.math.BigInteger;
import java.security.MessageDigest;

public final class SHA {

    public static final String KEY_SHA = "SHA";

    public static String getResult(final String inputStr) {

        final BigInteger sha;
        byte[] inputData = inputStr.getBytes();
        try {
            MessageDigest messageDigest = MessageDigest.getInstance(KEY_SHA);
            messageDigest.update(inputData);
            sha = new BigInteger(messageDigest.digest());
        } catch (final Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Sha exception");
        }
        return sha.toString(32);
    }

    private SHA() {

    }
}
