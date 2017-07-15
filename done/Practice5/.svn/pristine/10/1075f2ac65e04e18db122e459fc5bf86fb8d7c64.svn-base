package ua.nure.sereda.Practice5;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.nio.charset.Charset;

public class Part2 {


    public static void main(String[] args) throws IOException, InterruptedException {
        ByteArrayInputStream byteInputStream = new ByteArrayInputStream(System.getProperty("line.separator").getBytes(Charset.defaultCharset()));
        byteInputStream.skip(System.lineSeparator().length());
        System.setIn(byteInputStream);
        Spam.main(args);
        Thread.sleep(5000);
        byteInputStream.reset();
        System.setIn(System.in);
    }

}
