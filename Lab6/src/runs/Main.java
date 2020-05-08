package runs;

import javafx.util.Pair;
import log.MyFormatter;
import reader.Reader;
import types.InputType;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.logging.FileHandler;
import java.util.logging.Logger;

public class Main {
    public static void main(String[] args) {
        FileHandler fileHandler = null;
        try {
            fileHandler = new FileHandler("log\\log.txt");
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (fileHandler != null) {
            fileHandler.setFormatter(new MyFormatter());
        }
        Logger.getGlobal().addHandler(fileHandler);
        Logger.getGlobal().info("Build cross river thread.");
        CrossRiver crossThread = crossRiverInit();
        crossThread.start();
        try {
            crossThread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private static CrossRiver crossRiverInit() {
        String path = "src/source_DATA";
        InputType newType = chooseInput();
        switch (newType) {
            case FileInput:
                return new CrossRiver(Reader.getArgs(path + "/" + chooseFile(path)));
            case ManualInput:
                return new CrossRiver(paramProcess());
            default:
                throw new IllegalArgumentException();
        }
    }


    private static List<Pair<String, List<String>>> paramProcess() {
        List<Pair<String, List<String>>> paramList = new ArrayList<>();
        System.out.println("Input all params as(n h t N k MV)");
        Scanner sc = new Scanner(System.in);
        paramList.add(new Pair<>("n", new ArrayList<>(Arrays.asList(String.valueOf(sc.nextInt())))));
        paramList.add(new Pair<>("h", new ArrayList<>(Arrays.asList(String.valueOf(sc.nextInt())))));
        paramList.add(new Pair<>("t", new ArrayList<>(Arrays.asList(String.valueOf(sc.nextInt())))));
        paramList.add(new Pair<>("N", new ArrayList<>(Arrays.asList(String.valueOf(sc.nextInt())))));
        paramList.add(new Pair<>("k", new ArrayList<>(Arrays.asList(String.valueOf(sc.nextInt())))));
        paramList.add(new Pair<>("MV", new ArrayList<>(Arrays.asList(String.valueOf(sc.nextInt())))));
        return paramList;
    }

    private static InputType chooseInput() {
        int num = 0;
        for (InputType eachType : InputType.values()) {
            num++;
            System.out.print(num);
            System.out.print(' ');
            System.out.println(eachType);
        }
        //Scanner sc = new Scanner(System.in);
        //int op = sc.nextInt();
        int op = 2;
        switch (op) {
            case 1:
                return InputType.ManualInput;
            case 2:
                return InputType.FileInput;
            default:
                throw new IllegalArgumentException();
        }
    }

    private static String chooseFile(String path) {
        File file = new File(path);
        File[] array = file.listFiles();
        assert array != null;
        for (int i = 0; i < array.length; i++) {
            if (array[i].isFile()) {
                System.out.print(i + 1);
                System.out.print(' ');
                System.out.println(array[i].getName());
            }
        }
        //Scanner sc = new Scanner(System.in);
        //int op = sc.nextInt();
        return array[2].getName();
    }
}
