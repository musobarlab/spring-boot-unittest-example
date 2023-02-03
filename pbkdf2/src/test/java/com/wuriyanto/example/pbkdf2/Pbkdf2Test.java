package com.wuriyanto.example.pbkdf2;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import java.security.NoSuchAlgorithmException;


/**
 * Unit test for Pbkdf2.
 */
public class Pbkdf2Test {

    @Test
    public void hashPassword() throws NoSuchAlgorithmException {
        String hashedPassword = Pbkdf2.hashPassword("12345", 16, 1000, 32);
        assertNotNull(hashedPassword);
    }

    @Test
    public void verifyPasswordTest() throws NoSuchAlgorithmException {
        String hashedPassword = Pbkdf2.hashPassword("12345", 16, 1000, 32);

        assertTrue(Pbkdf2.verifyPassword("12345", hashedPassword));
        assertFalse(Pbkdf2.verifyPassword("1234", hashedPassword));

    }

    @Test
    public void verifyPasswordTest2() {
        String hashedPassword = "8AVJJA==";

        assertTrue(Pbkdf2.verifyPassword("12345", hashedPassword, "dAqmbHDlDeYPfh5qABrzOw==", 1000));
    }
}
