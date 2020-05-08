package reader;

import javafx.util.Pair;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Reader {
    public static String getRawString(String fileName){
        String rawString=new String();
        String readLine=new String();
        BufferedReader bufr=null;
        try{
            FileReader fr = new FileReader(new File(fileName));
            bufr=new BufferedReader(fr);
            while((readLine=bufr.readLine())!=null){
               rawString+=readLine;
               rawString+="\n";
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally{
            try{
                if(bufr!=null){
                    bufr.close();
                }
            }catch (IOException e){
                e.printStackTrace();
            }
        }
        return rawString;
    }

    public static List<Pair<String, List<String>>> getArgs(String fileName){
        String rawString=getRawString(fileName);
        List<Pair<String, List<String>>> reList=new ArrayList<>();

        String pattern1="([a-z,A-Z,0-9]+)( +)?:";
        Pattern r1 = Pattern.compile(pattern1);
        Matcher m1 = r1.matcher(rawString);

        String pattern2="=( +)?(.*)";
        Pattern r2=Pattern.compile(pattern2);
        Matcher m2=r2.matcher(rawString);
        while(m1.find()){
            m2.find();
            List<String> tmpList=getAllParts(m2.group(2));
            String tmpString=m1.group(1);
            reList.add(new Pair<>(tmpString,tmpList));

        }
        return reList;
    }

    public static List<String> getAllParts(String rawString){
        String[] tmpString;
        List<String> reList=new ArrayList<>();
        tmpString=rawString.split("\\s*[;,<> ]+\\s*");
        for(String eachString:tmpString){
            if(eachString.hashCode()!=0)
                reList.add(eachString);
        }
        return reList;
    }
}
