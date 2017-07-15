package ua.nure.sereda.Practice4;

import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;

import static org.junit.Assert.assertEquals;


public class Part1Test {
    @Test
    public void mainOut() throws IOException {
        Part1 obj = new Part1();
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        System.setOut(new PrintStream(output));
        Part1.main(new String[]{});
        String expected = "ЕСЛИ ПРИЛОЖЕНИЕ СЧИТЫВАЕТ ИНФОРМАЦИЮ из ФАЙЛА," + System.lineSeparator() +
                "то НЕОБХОДИМО УКАЗАТЬ КОДИРОВКУ, в" + System.lineSeparator() +
                "КОТОРОЙ она (ИНФОРМАЦИЯ) ЗАПИСАНА." + System.lineSeparator();
        assertEquals(expected, output.toString());
        System.setOut(System.out);
    }

    @Test
    public void convert() {
        String actual = Part1.convert("London is a capital of Great Britain.");
        assertEquals("LONDON is a CAPITAL of GREAT BRITAIN.", actual);
    }

}