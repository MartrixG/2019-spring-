package writer;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class BufferWrite {
    public static void writeToFile(String s) {
        try {
            String fileNameTemp = "savedData/" + "BufferWrite" + ".txt";
            File writeName = new File(fileNameTemp);
            writeName.createNewFile();
            BufferedWriter out = new BufferedWriter(new FileWriter(writeName));
            out.write(s);
            out.flush();
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
