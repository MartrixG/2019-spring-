package writer;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;

public class StreamWrite {
    public static void writeToFile(String s) {
        File f = new File("savedData/BufferWrite.txt");
        try {
            FileOutputStream fop = new FileOutputStream(f);
            OutputStreamWriter writer = new OutputStreamWriter(fop, "UTF-8");
            writer.append(s);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
