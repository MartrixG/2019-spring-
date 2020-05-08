package strategy.readStrategy;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class BufferRead implements ReadStrategy {
    @Override
    public String readIn(String fileName) {
        StringBuilder rawString = new StringBuilder();
        String readLine;
        BufferedReader bufr = null;
        try {
            FileReader fr = new FileReader(new File(fileName));
            bufr = new BufferedReader(fr);
            while ((readLine = bufr.readLine()) != null) {
                rawString.append(readLine);
                rawString.append("\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (bufr != null) {
                    bufr.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return rawString.toString();
    }
}
