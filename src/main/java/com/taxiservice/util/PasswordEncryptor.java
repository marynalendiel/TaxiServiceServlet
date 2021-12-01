package com.taxiservice.util;

import org.apache.commons.codec.digest.DigestUtils;

/**
 * Class that encrypts user's password with algorithm MD5.
 *
 * @author Maryna Lendiel
 */
public class PasswordEncryptor {

    public PasswordEncryptor() {
    }

    public static String encrypt(String password) {
        return DigestUtils.md5Hex(password);
    }
}
