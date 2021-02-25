package com.marvel.example.utils;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.time.Instant;

public class AppUtil {

    public static Instant instant = Instant.now();
    public static long timeStamp = instant.toEpochMilli();

    public static String MD5hash(String publicApiKey, String privateApiKey, Long timestamp) {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            String toHash = timestamp + privateApiKey + publicApiKey;
            return new BigInteger(1, md.digest(toHash.getBytes())).toString(16);
        } catch (NoSuchAlgorithmException exception) {
            exception.getMessage();
        }
        return null;
    }
}
