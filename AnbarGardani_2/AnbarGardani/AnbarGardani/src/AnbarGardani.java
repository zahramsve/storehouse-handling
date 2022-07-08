import java.io.FileNotFoundException;
import java.util.Scanner;
import java.io.File;
import java.io.*;
import java.io.IOException;
public class AnbarGardani {
    public static String data, data2;
    public static File file1, file2;
    public static String productName, productId, productPrice, categoriesCode, categoriesName,categoryName;
    public static String[] arr , arr2 , checkArr,nameArr,idArr,priceArr;
    public static int name , id ,pSpace ,pEnd, h=0,n=0,p=0;
    public static Scanner fileReader;
    public static boolean check;
    public static int[] nArr,pArr;
    public static void main(String[] args) {
        arr = new String[100] ;
        arr2 = new String[100] ;
        checkArr = new String[100] ;
        nameArr= new String[100] ;
        idArr= new String[100] ;
        priceArr= new String[100] ;
        methodArrangement();
    }
    public static void methodArrangement(){
        readFile();
        readCategoriesFile();
        createFile();
        fileWriter();
    }
    public static void readFile() {
        try {
            file1 = new File("C:\\Users\\AC30\\IdeaProjects\\AnbarGardani\\products.txt");
            fileReader = new Scanner(file1);
            while (fileReader.hasNextLine()) {
                data = fileReader.nextLine();
               nameSeparator();
            }
            fileReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }
    public static void readCategoriesFile() {
        try {
            file2 = new File("C:\\Users\\AC30\\IdeaProjects\\AnbarGardani\\categories.txt");
            fileReader = new Scanner(file2);
            int i=-1 , i2=-1;
            while (fileReader.hasNextLine()) {
                data2 = fileReader.nextLine();
                i++; i2++;
                categoriesCode = " " ; categoriesName= " ";
                for (int j = 0;j < 4; j++) {
                    categoriesCode = categoriesCode + data2.charAt(j);
                }  arr[i] =categoriesCode;
                for (int j = 4;j <data2.length(); j++) {
                    categoriesName = categoriesName + data2.charAt(j);

                } arr2[i2]=categoriesName;
            }
            fileReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }
    public static void nameSeparator() {
        productName = " ";
        name=0;
        if (data.charAt(0) == '&') {
            for (int i = 1; i <= 25; i++) {
                if (data.charAt(i) != '&') {
                    productName = productName + data.charAt(i);
                }
                else {
                    break;
                }
                name = i;
                if(name==0){
                    continue;
                }
            }
        }
//        if(productName != " ") {
//            nameArr[n] = productName;
//            nArr[n]=name;
//            n++;
//        }
        idSeparator();
        //System.out.println(productName);
    }
    public static void idSeparator() {
        productId = " ";
        for (int i = 0; i < data.length(); i++) {
            if (data.charAt(i) == '#') {
                for (int j = i; j <= i + 4; j++)
                    productId = productId + data.charAt(j);
            }
            id =i;
        }
//      if( productId != " ") {
//          idArr[h] = productId;
//          h++;
//      }
       priceSeparator();
       //System.out.println(productId);
    }
    public static void priceSeparator() {
        productPrice = " ";
        for (int i = 0; i < data.length(); i++) {
            if (data.charAt(i) == '$') {
                for (int j = i; ; j--) {
                    if (data.charAt(j) == ' ') {
                        pSpace=j;
                        while (data.charAt(j) != '$') {
                            productPrice = productPrice + data.charAt(j);
                            j++;
                            pEnd=j;
                        }
                    }
                    if (productPrice != " ")
                        break;
                }
            }
        }
//        int f =pEnd-pSpace;
//        if( productPrice != " ") {
//            priceArr[p] = productPrice;
//            pArr[p]=f;
//            p++;
//        }
        //System.out.println(productPrice);
    }
    public static void createFile(){
        try{
            File sorted= new File("C:\\Users\\AC30\\IdeaProjects\\AnbarGardani\\SortedFile.txt");
            if (sorted.createNewFile()){
                System.out.println("The file was created with the name " + sorted.getName() + "  .\n Please go to AnbarGardani folder to see that.");
            }
            else{
                System.out.println(" File already exists.");
            }
        } catch(IOException e){
            System.out.println(" An error occurred.");
            e.printStackTrace();
        }
    }
    public static void fileWriter() {
        try {
            FileWriter myWriter=new FileWriter("C:\\Users\\AC30\\IdeaProjects\\AnbarGardani\\SortedFile.txt");
            myWriter.write("|       product Name       |  ID  |   Price   | categories |\n");
            int t=0;
            if ((25 -name) % 2 == 0) {
                int ordered1 = (25 -  name) / 2;
                myWriter.write("|");
                for (int i = 0; i < ordered1; i++)
                    myWriter.write(" ");
               // productName=priceArr[t];
                myWriter.write(productName);
               // t++;
                for (int i = 0; i < ordered1; i++)
                    myWriter.write(" ");
                myWriter.write("|");
            }
            if ((25 -  name) % 2 == 1) {
                int ordered2 = (25 -  name) / 2;
                myWriter.write("|");
                for (int i = 0; i <= ordered2; i++)
                    myWriter.write(" ");
              // productName=priceArr[t];
                myWriter.write(productName);
               // t++;
                for (int i = 0; i < ordered2; i++)
                    myWriter.write(" ");
                myWriter.write("|");
            }
            int pi=0;
           // productId=idArr[pi];
            myWriter.write(productId);
           // pi++;
            myWriter.write("|");
            int h = (pEnd - pSpace)+1;
            int pa=0;
            if( (11-h)%2==0){
                int ordered1 =(11-h)/2;
                for (int i = 0; i < ordered1; i++)
                    myWriter.write(" ");
                //productPrice=priceArr[pa];
                myWriter.write(productPrice + "&");
                //pa++;
                for (int i = 0; i < ordered1; i++)
                    myWriter.write(" ");
                myWriter.write("|");
            }
            if((11-h)%2==1){
                int ordered2 = (11-h) / 2;
                for (int i = 0; i < ordered2; i++)
                    myWriter.write(" ");
                //productPrice=priceArr[pa];
                myWriter.write(productPrice + "&");
                //pa++;
                for (int i = 0; i < ordered2; i++)
                    myWriter.write(" ");
                myWriter.write("|");
            }
            int g=0;
            for (int i = 0; i < 100; i++) {
                if (arr[i]==null)
                    continue;
                categoriesCode = arr[i];
                check=false;
                for (int j = name; j < data.length(); j++) {
                    if ( categoriesCode.charAt(0) == data.charAt(j-1)&&categoriesCode.charAt(1) == data.charAt(j)&& categoriesCode.charAt(2) == data.charAt(j+1) && categoriesCode.charAt(3) == data.charAt(j+2)) {
                        if ( (j<id || j>(id+4)) && (j<pSpace || j>pEnd) ) {
                            categoryName=arr2[i];
                            if(g==0)
                                myWriter.write(categoryName);
                           else{
                                myWriter.write(","+categoryName);
                        } g++;
                    }
                }
                }

            }myWriter.write("|");
            myWriter.close();
        } catch (IOException e) {
        System.out.println("An error occurred.");
        e.printStackTrace();
    }
    }
}