package ua.nure.sereda.Practice4;

import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.io.UnsupportedEncodingException;

import static org.junit.Assert.assertEquals;


public class Part5Test {
    @Test
    public void main() throws UnsupportedEncodingException {
        Part5 obj = new Part5();
        System.setIn(new ByteArrayInputStream("table ru\ntable en\napple ru\nstop".getBytes("Cp1251")));
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        System.setOut(new PrintStream(output));
        Part5.main(new String[]{});
        String expected = "стол" + System.lineSeparator() +
                "table" + System.lineSeparator() +
                "яблоко" + System.lineSeparator();
        assertEquals(expected, output.toString());
        System.setIn(System.in);
        System.setOut(System.out);
    }

}