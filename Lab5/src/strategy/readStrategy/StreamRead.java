package strategy.readStrategy;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

public class StreamRead implements ReadStrategy {

    @Override
    public String readIn(String fileName) {
        StringBuilder s = new StringBuilder();
        try {
            InputStream is = new FileInputStream(fileName);
            byte[] b = new byte[is.available()];
            while (is.read(b) != -1) {
                s.append(new String(b));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return s.toString();
    }
}
