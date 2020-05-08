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
     * �ж��Ƿ��ǻ÷�
     * @param fileName �ļ���Ե�ַѰַ����
     * @return true ������ļ��ǻ÷�
     * 		 false ������ļ����ǻ÷��򲻷�������淶
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
    	     * �ո�(' ')ASCII��Ϊ32
    	     */
    	    System.out.println("All numbers need to be separated by tabs('\\t').\n");
    	    return false;
    	  }else if(line.charAt(i)==46||line.charAt(i)==45) {
    	    /**
    	      * С����('.')ASCII��Ϊ46
    	      * ����('-')ASCII��Ϊ45
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
  		   * ����ǰ��Ԫ�ظ����ͻ÷��ĳ��Ȳ�ͬ���ҵ�ǰ�в��ǵ�һ�з���false
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
    int row=0, col=n/2,i,j,square=n*n;//��ʼ״̬�£��ӵ�0�е�n/2�п�ʼ����
    for(i=1;i<=square;i++) {//������1��ʼ������square������
      magic[row][col]=i;
	  if (i%n==0){//����ǰ����ģnΪ0�������չ�����һ�����ֽ������֮ǰ��������֣������Խ�������һ
        row++;
	  }
	  else{
	    if(row==0) {//����ǰ���ǵ�һ�У�����һ�������������һ��
	      row=n-1;
	    }
	    else{
	      row--;
	    }
	    if(col==(n-1)){//����ǰ�������һ�У�����һ���������ڵ�һ��
	      col=0;
	    }
	    else{
	      col++;//������һ������������һ��
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
   * �����ļ�������ļ���
   * @param num ��Ϊ�ļ���Ŵ���
   * @return �������Ѱַ��String���ַ
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