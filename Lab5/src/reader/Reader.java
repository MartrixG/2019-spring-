package reader;

import javafx.util.Pair;
import strategy.readStrategy.BufferRead;
import strategy.readStrategy.ScannerRead;
import strategy.readStrategy.StreamRead;
import types.ioType.IOType;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Reader {
    private Reader() {
    }

    public static String getRawString(String fileName, IOType type) {
        switch (type) {
            case BufferReader:
                return new BufferRead().readIn(fileName);
            case Stream:
                return new StreamRead().readIn(fileName);
            case Scanner:
                return new ScannerRead().readIn(fileName);
            default:
                return null;
        }
    }

    public static List<Pair<String, List<String>>> getArgs(String fileName, IOType type) {
        long start = System.currentTimeMillis();
        String rawString = getRawString(fileName, type);
        long end = System.currentTimeMillis();

        System.out.println("读入文件用时：");
        System.out.print(end - start);
        System.out.println("ms");
/*
        start = System.currentTimeMillis();
        BufferWrite.writeToFile(rawString);
        end = System.currentTimeMillis();
        System.out.println("BufferWriter输出用时：");
        System.out.print(end - start);
        System.out.println("ms");

        start = System.currentTimeMillis();
        StreamWrite.writeToFile(rawString);
        end = System.currentTimeMillis();
        System.out.println("StreamWriter输出用时：");
        System.out.print(end - start);
        System.out.println("ms");

        start = System.currentTimeMillis();
        PrintWrite.writeToFile(rawString);
        end = System.currentTimeMillis();
        System.out.println("PrintWriter输出用时：");
        System.out.print(end - start);
        System.out.println("ms");

        //System.exit(0);
*/
        List<Pair<String, List<String>>> reList = new ArrayList<>();

        String pattern1 = "([a-z,A-Z,0-9]+)( +)?:";
        Pattern r1 = Pattern.compile(pattern1);
        Matcher m1 = r1.matcher(rawString);

        String pattern2 = "=( +)?(.*)";
        Pattern r2 = Pattern.compile(pattern2);
        Matcher m2 = r2.matcher(rawString);
        while (m1.find()) {
            m2.find();
            List<String> tmpList = getAllParts(m2.group(2));
            String tmpString = m1.group(1);
            reList.add(new Pair<>(tmpString, tmpList));

        }
        return reList;
    }

    public static List<String> getAllParts(String rawString) {
        String[] tmpString;
        List<String> reList = new ArrayList<>();
        tmpString = rawString.split("\\s*[;,<> ]+\\s*");
        for (String eachString : tmpString) {
            if (eachString.hashCode() != 0) {
                reList.add(eachString);
            }
        }
        return reList;
    }
}
