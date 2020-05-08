package applications;

import applications.logs.myFormatter;
import exception.GramarException;
import exception.InputException;

import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.Logger;

public class Main {
    public static void main (String[] args) throws InputException, GramarException {
        try{
            FileHandler fileHandler=new FileHandler("log\\log.txt");
            fileHandler.setFormatter(new myFormatter());
            Logger.getGlobal().addHandler(fileHandler);
        }catch (SecurityException | IOException e){
            e.printStackTrace();
        }
        Applications.doOperation(Applications.newApplication());
    }
}
