package me.ianhe.io;

import org.junit.Test;

import java.io.FileReader;
import java.io.FileWriter;

/**
 * @author iHelin
 * @since 2017/8/12 10:48
 */
public class FileReaderAndFileWriterTest {

    /**
     * @author iHelin
     * @since 2017/11/29 14:49
     */
    @Test
    public void test() throws Exception {
        FileReader fileReader = new FileReader("demo/read.txt");
        FileWriter fileWriter = new FileWriter("demo/fileWriter.txt");
        char[] buffer = new char[2056];
        int c;
        while ((c = fileReader.read(buffer, 0, buffer.length)) != -1) {
            fileWriter.write(buffer, 0, c);
            fileWriter.flush();
        }
        fileWriter.close();
        fileReader.close();
    }
}
