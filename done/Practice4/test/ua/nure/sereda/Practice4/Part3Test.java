package ua.nure.sereda.Practice4;

import org.junit.Test;

import java.io.*;
import java.util.regex.Pattern;

import static org.junit.Assert.assertEquals;


public class Part3Test {
    @Test
    public void main() throws Exception {
        Part3 obj = new Part3();
        System.setIn(new ByteArrayInputStream("char\nString\nint\ndouble\nstop".getBytes("Cp1251")));
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        System.setOut(new PrintStream(output));
        String expected = "я f и л " + System.lineSeparator() +
                "bcd фвыа " + System.lineSeparator() +
                "432 89 " + System.lineSeparator() +
                "43.43 51. .98 " + System.lineSeparator();
        Part3.main(new String[]{});
        assertEquals(expected, output.toString());
        System.setIn(System.in);
        System.setOut(System.out);

    }

    @Test
    public void readText() throws Exception {
        String expected = "я f bcd 43.43 51. 432 и л фвыа 89 .98" + System.lineSeparator();
        String actual = Part3.readTxt("part3.txt");
        assertEquals(expected, actual);
    }

    @Test(expected = FileNotFoundException.class)
    public void ReadWithoutExistingFile() throws IOException {
        Part3.readTxt("NoSochFile.txt");
    }

    @Test
    public void printType() {
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        System.setOut(new PrintStream(output));
        Part3.printType("s 3 gsd 0.24", Pattern.compile("(?U)(\\b[\\w&&\\D]\\b)"));
        assertEquals("s " + System.lineSeparator(), output.toString());
        System.setOut(System.out);
    }

}