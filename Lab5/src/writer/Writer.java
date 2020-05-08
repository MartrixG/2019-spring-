package writer;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class Writer {
    private Writer() {
    }

    public static void writeToFile(String s, String fileName) {
        try {
            String fileNameTemp = "savedData/" + fileName + ".txt";
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
