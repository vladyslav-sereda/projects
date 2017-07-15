package ua.nure.sereda.SummaryTask4.utils;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import static org.junit.Assert.*;

@RunWith(MockitoJUnitRunner.class)
public class PassEncryptionTest {

    @Test
    public void constructorTest() {
        PassEncryption passEncryption = new PassEncryption();
        assertNotNull(passEncryption);
    }

    @Test
    public void twoDifferentStringsTest() {
        String s1 = PassEncryption.encrypt("mypassword");
        String s2 = PassEncryption.encrypt("yourpassword");

        assertNotEquals(s1, s2);
    }

    @Test
    public void twoSameStringsTest() {
        String s1 = PassEncryption.encrypt("mypassword");
        String s2 = PassEncryption.encrypt("mypassword");

        assertEquals(s1, s2);
    }
}
