package writer;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;

public class PrintWrite {
    public static void writeToFile(String s) {
        try {
            PrintWriter out = new PrintWriter("savedData/BufferWrite.txt", "UTF-8");
            out.print(s);
        } catch (FileNotFoundException | UnsupportedEncodingException e) {
            e.printStackTrace();
        }

    }
}
