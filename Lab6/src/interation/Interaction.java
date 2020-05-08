package interation;

import java.util.Scanner;

public class Interaction {
    public static String[] getArgs() {
        String[] reS;
        Scanner cs = new Scanner(System.in);
        String str = cs.nextLine();
        reS = str.split(" ");
        return reS;
    }
}
