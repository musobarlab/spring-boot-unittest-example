package com.wuriyanto.example.pbkdf2;

import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import java.util.Arrays;
import java.util.Base64;

public final class Pbkdf2 {

    private static final String ALGORITHM = "PBKDF2WithHmacSHA1";
    private static final String RANDOM_ALGORITHM = "SHA1PRNG";

    private Pbkdf2() {

    }

    // Usage
//    public static void main(String[] args) throws NoSuchAlgorithmException {
//
//        String password = "12345";
//        int iterations = 1000;
//        int keyLen = 32;
//        int saltLen = 16;
//        String h = hashPassword(password, saltLen, iterations, keyLen);
//        System.out.println(h);
//
//        System.out.println(verifyPassword("12345", h));
//    }
    public static String hashPassword(String password, final int saltLen, final int iterations, final int keyLen) throws NoSuchAlgorithmException {
        char[] passwordChars = password.toCharArray();
        byte[] saltBytes = getSalt(saltLen);

        byte[] hashedBytes = pbkdf2(passwordChars, saltBytes, iterations, keyLen);
        String hashedString = toBase64(hashedBytes);
        String saltString = toBase64(saltBytes);

        return iterations + ":" + saltString + ":" + hashedString;
    }

    public static Boolean verifyPassword(String password, String cipherText) {
        String[] parts = cipherText.split(":");
        int iterations = Integer.parseInt(parts[0]);
        byte[] saltBytes = fromBase64(parts[1]);
        byte[] hash = fromBase64(parts[2]);

        char[] passwordChars = password.toCharArray();

        byte[] hashedBytes = pbkdf2(passwordChars, saltBytes, iterations, hash.length * 8);

        if (!Arrays.equals(hash, hashedBytes)) {
            return false;
        }

        return true;
    }

    public static Boolean verifyPassword(String password, String cipherText, String salt, Integer iterations) {
        byte[] saltBytes = fromBase64(salt);
        byte[] hash = fromBase64(cipherText);

        char[] passwordChars = password.toCharArray();

        byte[] hashedBytes = pbkdf2(passwordChars, saltBytes, iterations, hash.length * 8);

        if (!Arrays.equals(hash, hashedBytes)) {
            return false;
        }

        return true;
    }

    private static byte[] pbkdf2(final char[] password, final byte[] salt, final int iterations, final int keyLength) {

        try {
            SecretKeyFactory skf = SecretKeyFactory.getInstance(ALGORITHM);
            PBEKeySpec spec = new PBEKeySpec(password, salt, iterations, keyLength);
            SecretKey key = skf.generateSecret(spec);
            byte[] res = key.getEncoded();
            return res;
        } catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
            throw new RuntimeException(e);
        }
    }

    private static String toBase64(byte[] b) {
        return Base64.getEncoder().encodeToString(b);
    }

    private static byte[] fromBase64(String s) {
        return Base64.getDecoder().decode(s);
    }

//    private static String toHex(byte[] array) {
//        BigInteger bi = new BigInteger(1, array);
//        String hex = bi.toString(16);
//        int paddingLength = (array.length * 2) - hex.length();
//        if (paddingLength > 0) {
//            return String.format("%0" + paddingLength + "d", 0) + hex;
//        } else {
//            return hex;
//        }
//    }
    private static byte[] getSalt(int saltLen) throws NoSuchAlgorithmException {
        SecureRandom sr = SecureRandom.getInstance(RANDOM_ALGORITHM);
        byte[] salt = new byte[saltLen];
        sr.nextBytes(salt);
        return salt;
    }
}
