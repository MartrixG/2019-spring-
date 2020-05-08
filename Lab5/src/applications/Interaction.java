package applications;

import types.ApplicationType;
import types.ioType.IOType;
import types.trackGameTypes.GameType;

import java.io.File;
import java.util.List;
import java.util.Scanner;

public class Interaction {
    private Interaction() {
    }

    public static IOType chooseIOType() {
        int num = 0;
        for (IOType eachType : IOType.values()) {
            num++;
            System.out.print(num);
            System.out.print(' ');
            System.out.println(eachType);
        }
        Scanner sc = new Scanner(System.in);
        int op = sc.nextInt();
        switch (op) {
            case 1:
                return IOType.BufferReader;
            case 2:
                return IOType.Stream;
            case 3:
                return IOType.Scanner;
            default:
                throw new IllegalArgumentException();
        }
    }

    public static ApplicationType chooseApp() {
        int num = 0;
        for (ApplicationType eachType : ApplicationType.values()) {
            num++;
            System.out.print(num);
            System.out.print(' ');
            System.out.println(eachType);
        }
        Scanner sc = new Scanner(System.in);
        int op = sc.nextInt();
        switch (op) {
            case 1:
                return ApplicationType.AtomStructure;
            case 2:
                return ApplicationType.TrackGame;
            case 3:
                return ApplicationType.SocialNetwork;
            default:
                throw new IllegalArgumentException();
        }
    }

    public static String chooseFile(String path) {
        File file = new File(path);
        File[] array = file.listFiles();
        assert array != null;
        for (int i = 0; i < array.length; i++) {
            if (array[i].isFile()) {
                System.out.print(i);
                System.out.print(' ');
                System.out.println(array[i].getName());
            }
        }
        Scanner sc = new Scanner(System.in);
        int op = sc.nextInt();
        return array[op].getName();
    }

    public static GameType chooseGameType() {
        int num = 0;
        for (GameType eachType : GameType.values()) {
            num++;
            System.out.print(num);
            System.out.print(' ');
            System.out.println(eachType);
        }
        Scanner sc = new Scanner(System.in);
        int op = sc.nextInt();
        switch (op) {
            case 1:
                return GameType.Random;
            case 2:
                return GameType.Records;
            default:
                throw new IllegalArgumentException();
        }
    }

    public static int chooseOp(List<String> args) {
        for (int i = 0; i < args.size(); i++) {
            System.out.print(i);
            System.out.print(" ");
            System.out.println(args.get(i));
        }
        Scanner sc = new Scanner(System.in);
        int choice = sc.nextInt();
        return choice;
    }

    public static String[] getArgs() {
        String[] reS;
        Scanner cs = new Scanner(System.in);
        String str = cs.nextLine();
        reS = str.split(" ");
        return reS;
    }
}
