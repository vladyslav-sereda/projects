package ua.nure.sereda.Practice4;

import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.assertEquals;


public class Part4Test {
    @Test
    public void main() throws Exception {
        Part4 obj = new Part4();
        Part4.main(new String[]{});
    }

    @Test
    public void Parser() throws IOException {
        String[] expected = {"Класс должен разбирать текстовый файл и возвращать предложения из файла.",
                "Под предложением понимать последовательность, которая начинается с большой буквы и заканчивается точкой.",
                "Исходный файл брать небольшим по размеру.",
                "Достаточно несколько строк и несколько предложений."};
        int i = 0;
        Parser parser = new Parser("part4Tests.txt", "Cp1251");
        for (String str : parser) {
            assertEquals(expected[i], str);
            i++;
        }
    }
}