package com.quach.shop_giay.util;

import org.apache.commons.codec.binary.Base64;

import java.security.MessageDigest;

public class Encryption {
    public static String toSHA1(String str) {
        String salt = "Duy-1762003";
        String result = null;
        str = str + salt;
        try {
            byte[] dataBytes = str.getBytes("UTF-8");
            MessageDigest md = MessageDigest.getInstance("SHA-1");
            result = Base64.encodeBase64String((md.digest(dataBytes)));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }
}
