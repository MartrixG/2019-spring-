package applications;

import applications.logs.MyFormatter;
import exception.GramarException;
import exception.InputException;

import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.Logger;

public class Main {
    private Main() {
    }

    public static void main(String[] args) throws InputException, GramarException {
        try {
            FileHandler fileHandler = new FileHandler("log\\log.txt");
            fileHandler.setFormatter(new MyFormatter());
            Logger.getGlobal().addHandler(fileHandler);
        } catch (SecurityException | IOException e) {
            e.printStackTrace();
        }
        Applications.doOperation(Applications.newApplication());
    }
}
