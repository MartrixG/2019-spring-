package strategy.readStrategy;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class ScannerRead implements ReadStrategy {
    @Override
    public String readIn(String fileName) {
        StringBuilder s = new StringBuilder();
        Scanner sc = null;
        File f = new File(fileName);
        try {
            sc = new Scanner(f);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        assert sc != null;
        while (sc.hasNext()) {
            s.append(sc.nextLine()).append('\n');
        }
        sc.close();
        return s.toString();
    }
}
