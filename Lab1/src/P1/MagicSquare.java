package P1;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.lang.Integer;

public class MagicSquare {
  public static boolean isLegalMagicSquare(String fileName) {
    /**
     * 判断是否是幻方
     * @param fileName 文件相对地址寻址参数
     * @return true 传入的文件是幻方
     * 		 false 传入的文件不是幻方或不符合输入规范
     */
	FileReader fr=null;
	BufferedReader bufr=null;
	ArrayList<ArrayList<Integer>> numberInSquare=new ArrayList<ArrayList<Integer>>();
	int lengthOfSquare=0;
	int heightOfSquare=0;
	int[] vis=new int[20000];
    try{
      fr=new FileReader(fileName);
      bufr=new BufferedReader(fr);
      String line=null;
      while((line=bufr.readLine())!=null) {
    	for(int i=0;i<line.length();i++) {
    	  if(line.charAt(i)==32) {
    	    /**
    	     * 空格(' ')ASCII码为32
    	     */
    	    System.out.println("All numbers need to be separated by tabs('\\t').\n");
    	    return false;
    	  }else if(line.charAt(i)==46||line.charAt(i)==45) {
    	    /**
    	      * 小数点('.')ASCII码为46
    	      * 负号('-')ASCII码为45
    	      */
    	    System.out.println("All numbers should be positive integer.\n");
    	    return false;
    	  }
    	}
    	String[] readInStr=null;
    	readInStr=line.split("\t");
    	ArrayList<Integer> thisLineNumber=new ArrayList<Integer>();
    	
  	    if(readInStr.length!=lengthOfSquare&&lengthOfSquare!=0) {
  		  /**
  		   * 若当前行元素个数和幻方的长度不同并且当前行不是第一行返回false
  		   */
  		  System.out.println("The number of elements per line should be the same.\n");
  		  return false;
  	    }
  	    if(lengthOfSquare==0) {
  		  lengthOfSquare=readInStr.length;
  	    }
  	    for(String nowStr : readInStr) {
  	      if(Integer.valueOf(nowStr)==0) {
  	    	System.out.println("All numbers should be positive integer.\n");
  	    	return false;
  	      }else if(Integer.valueOf(nowStr)>lengthOfSquare*lengthOfSquare) {
  	    	System.out.println("Does not meet the Magic Standard.\n");
  	    	return false;
  	      }else if(vis[Integer.valueOf(nowStr)]==1) {
  	    	System.out.println("Does not meet the Magic Standard.\n");
  	    	return false;
  	      }
  	      vis[Integer.valueOf(nowStr)]=1;
  	      thisLineNumber.add(Integer.valueOf(nowStr));
  	    }
  	    numberInSquare.add(thisLineNumber);
  	    heightOfSquare++;
      }
      if(heightOfSquare!=lengthOfSquare) {
    	System.out.println("The magic square should have the same number of rows and columns.\n");
    	return false;
      }
    }catch(IOException e) {
      e.printStackTrace();
    }finally {
      try {
    	if(bufr!=null) {
    	  bufr.close();
    	}
      }catch(IOException e){
    	e.printStackTrace();
      }
    }
    int eigenvalueOfMagicSquare=0;
    for(int i=0;i<numberInSquare.size();i++) {
      eigenvalueOfMagicSquare+=numberInSquare.get(1).get(i);
    }
    for(int i=0;i<numberInSquare.size();i++) {
      int ithLineSum=0;
      int jthColumnSum=0;
      for(int j=0;j<numberInSquare.get(i).size();j++) {
    	  ithLineSum+=numberInSquare.get(i).get(j);
    	  jthColumnSum+=numberInSquare.get(j).get(i);
      }
      if(ithLineSum!=eigenvalueOfMagicSquare||jthColumnSum!=eigenvalueOfMagicSquare) {
    	System.out.println("Not all rows, all columns, and both diagonals sum to the same constant.\n");
    	return false;
      }
    }
    int diagonal1Sum=0;
    int diagonal2Sum=0;
    for(int i=0;i<numberInSquare.size();i++) {
    	diagonal1Sum+=numberInSquare.get(i).get(i);
    	diagonal2Sum+=numberInSquare.get(numberInSquare.size()-i-1).get(i);
    }
    if(diagonal1Sum!=eigenvalueOfMagicSquare||diagonal2Sum!=eigenvalueOfMagicSquare) {
      System.out.println("Not all rows, all columns, and both diagonals sum to the same constant.\n");
      return false;
    }
    return true;
  }
public static boolean generateMagicSquare(int n) {
    if(n<0) {
      System.out.println("n should be a positive number.\n");
      return false;
    }
    if(n%2==0) {
      System.out.println("n should be a odd number.\n");
      return false;
    }
	int[][] magic=new int[n][n];
    int row=0, col=n/2,i,j,square=n*n;//初始状态下，从第0行第n/2列开始生成
    for(i=1;i<=square;i++) {//从数字1开始，共有square个数字
      magic[row][col]=i;
	  if (i%n==0){//若当前数字模n为0（即按照规则，下一个数字将会替代之前填入的数字），所以将行数加一
        row++;
	  }
	  else{
	    if(row==0) {//若当前行是第一行，则下一个数字排在最后一行
	      row=n-1;
	    }
	    else{
	      row--;
	    }
	    if(col==(n-1)){//若当前列是最后一列，则下一个数字排在第一列
	      col=0;
	    }
	    else{
	      col++;//否则下一个数字排在下一列
	    }
      } 
    }
    FileWriter fr=null;
    BufferedWriter br=null;
    try {
    	fr=new FileWriter("src//P1//txt//6.txt");
    	br=new BufferedWriter(fr);
    	for (i=0;i<n;i++) {
          for (j=0;j<n;j++) {
		    br.write(magic[i][j]+"\t");
          }
          br.write("\n");
        }
    }catch(IOException e) {
      e.printStackTrace();
    }finally {
    	try {
    	  br.close();
    	  fr.close();
    	}catch(IOException e) {
    	  e.printStackTrace();
    	}
    }
	return true; 
  }
  public static String makeFileName(int num) {
  /**
   * 产生文件读入的文件名
   * @param num 作为文件编号传入
   * @return 返回相对寻址的String类地址
   */  
    String file;
    file="src//P1//txt//";
    file+=String.valueOf(num);
    file+=".txt";
    return file;
  }
  public static void main(String[] args) {
	generateMagicSquare(10);
    for(int i=1;i<=6;i++) {
      System.out.printf("%d.txt:",i);
      if(isLegalMagicSquare(makeFileName(i))) {
    	System.out.println("It is a Magic Square\n");
      }
    }
  }
}