package com.example.billoa.common.utils;

import org.apache.tomcat.util.codec.binary.Base64;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Md5Utils {

    private static  final String SALF="qwre54353.,";

    public  static  String getMd5Str(String strVal) throws NoSuchAlgorithmException {
        MessageDigest md5 = MessageDigest.getInstance("MD5");
        return Base64.encodeBase64String(md5.digest((strVal+SALF).getBytes()));
    }
}
