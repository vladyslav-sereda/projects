package ua.nure.sereda.SummaryTask4.utils;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import java.sql.SQLException;

import static org.junit.Assert.*;


@RunWith(MockitoJUnitRunner.class)
public class ValidatorTest {
    @Test
    public void constructorTest() {
        Validator validator = new Validator();
        assertNotNull(validator);
    }

    @Test
    public void testEmail_Correct() throws SQLException {
        assertTrue(Validator.Email("sasha@ukr.net"));
    }

    @Test
    public void testEmail_Incorrect() throws SQLException {
        assertFalse(Validator.Email("anystring"));
    }

    @Test
    public void testEmail_Null() throws SQLException {
        assertFalse(Validator.Email(null));
    }

    @Test
    public void testEmail_Empty() throws SQLException {
        assertFalse(Validator.Email(""));
    }

    @Test
    public void testName_Correct() throws SQLException {
        assertTrue(Validator.Name("Oleksandra"));
    }

    @Test
    public void testName_Incorrect() throws SQLException {
        assertFalse(Validator.Name("0le4ka"));
    }

    @Test
    public void testName_Null() throws SQLException {
        assertFalse(Validator.Name(null));
    }

    @Test
    public void testName_Empty() throws SQLException {
        assertFalse(Validator.Name(""));
    }


    @Test
    public void testPhone_Empty() throws SQLException {
        assertFalse(Validator.Password(""));
    }

    @Test
    public void testPassword_Correct() throws SQLException {
        assertTrue(Validator.Password("g00d_pass"));
    }

    @Test
    public void testPassword_Incorrect() throws SQLException {
        assertFalse(Validator.Password("0000"));
    }

    @Test
    public void testPassword_Null() throws SQLException {
        assertFalse(Validator.Password(null));
    }

    @Test
    public void testPassword_Empty() throws SQLException {
        assertFalse(Validator.Password(""));
    }


}
