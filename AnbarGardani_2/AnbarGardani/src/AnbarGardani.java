import java.io.FileNotFoundException;
import java.util.Scanner;
import java.io.File;
import java.io.*;
import java.io.IOException;
import java.util.Vector;

public class AnbarGardani {
    public static String data, data2;
    public static String filename, categoryname;
    public static File file1, file2;
    public static String productName, productId, productPrice, categoriesCode, categoriesName, categoryName;
    public static Vector<String> arr, arr2;
    public static int name, id, pSpace, pEnd, h = 0;
    public static Scanner fileReader;
    public static boolean check;

    public static void main(String[] args) {

        arr = new Vector<String>();
        arr2 = new Vector<String>();
        Scanner scanner = new Scanner(System.in);
        System.out.println("please enter your product name.");
        filename = scanner.next();
        System.out.println("please enter your category name.");
        categoryname = scanner.next();
        methodArrangement();
    }

    public static void methodArrangement() {// tartib raftan too method ha.
        readCategoriesFile();
        deleteFile();
        readFile();
    }

    public static void readFile() {//khoondan file product.
        try {
            file1 = new File(filename + ".txt");
            fileReader = new Scanner(file1);
            while (fileReader.hasNextLine()) {
                data = fileReader.nextLine();
                nameSeparator();//bad az khoondan har khat vared method ha (baraye joda sazi) beshe.
            }
            fileReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    public static void readCategoriesFile() {// khoondan file category baraye vijhegi haye product ha.
        try {
            file2 = new File(categoryname + ".txt");
            fileReader = new Scanner(file2);
            int i = -1, i2 = -1;
            while (fileReader.hasNextLine()) {
                data2 = fileReader.nextLine();//dar har khat code anha va vighegi ha az an ha joda shavad.
                i++;
                i2++;
                categoriesCode = " ";
                categoriesName = " ";
                for (int j = 0; j < 4; j++) {
                    categoriesCode = categoriesCode + data2.charAt(j);
                }
                arr.add(categoriesCode);
                for (int j = 4; j < data2.length(); j++) {
                    categoriesName = categoriesName + data2.charAt(j);

                }
                arr2.add(categoriesName);
            }
            fileReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    public static void nameSeparator() {//esm product ru joda karde va dar yek string minevissad.
        productName = " ";
        name = 0;
        if (data.charAt(0) == '&') {
            for (int i = 1; i <= 25; i++) {
                if (data.charAt(i) != '&') {
                    productName = productName + data.charAt(i);
                } else {
                    break;
                }
                name = i;//baray inke bedanim har esm chand character darad.
                if (name == 0) {
                    continue;
                }
            }
        } else
            productName = null;
        idSeparator();
    }

    public static void idSeparator() {//ID product ru joda karde va dar yek string minevissad.
        productId = " ";
        for (int i = 0; i < data.length(); i++) {
            if (data.charAt(i) == '#') {
                for (int j = i; j <= i + 4; j++)
                    productId = productId + data.charAt(j);
            }
            id = i;
        }
        priceSeparator();
    }

    public static void priceSeparator() {//gheymat product ru joda karde va dar yek string minevissad.
        productPrice = " ";
        for (int i = 0; i < data.length(); i++) {
            if (data.charAt(i) == '$') {
                for (int j = i; ; j--) {
                    if (data.charAt(j) == ' ') {
                        pSpace = j;// space ghabl az price dar khane chand ast.
                        while (data.charAt(j) != '$') {
                            productPrice = productPrice + data.charAt(j);
                            j++;
                            pEnd = j;//$dar khane chandom ast.
                        }
                    }
                    if (productPrice != " ")
                        break;
                }
            }
        }
        if (productName != null)
            fileWriter();
    }

    public static void deleteFile() {

        File sorted = new File("SortedFile.txt");
        if (sorted.exists()) {
            sorted.delete();
        }
            System.out.println("file was created & sorted with the name " + sorted.getName() +   "\n Please go to AnbarGardani folder to see that.");
    }
    public static void fileWriter() {//neveshtan be tartib product ha dar file jadid.
        try {
            FileWriter myWriter = new FileWriter("SortedFile.txt", true);
            if (h == 0)
                myWriter.write("|       product Name       |  ID  |   Price   | categories |");
            h++;
            myWriter.write("\n");
            if ((25 - name) % 2 == 0) {//rikhtan name
                int ordered1 = (25 - name) / 2;
                myWriter.write("|");
                for (int i = 0; i < ordered1; i++)
                    myWriter.write(" ");
                myWriter.write(productName);
                for (int i = 0; i < ordered1; i++)
                    myWriter.write(" ");
                myWriter.write("|");
            }
            if ((25 - name) % 2 == 1) {//rikhtan name
                int ordered2 = (25 - name) / 2;
                myWriter.write("|");
                for (int i = 0; i <= ordered2; i++)
                    myWriter.write(" ");
                myWriter.write(productName);
                for (int i = 0; i < ordered2; i++)
                    myWriter.write(" ");
                myWriter.write("|");
            }
            myWriter.write(productId);//rikhtan ID
            myWriter.write("|");
            int h = (pEnd - pSpace) + 1;////rikhtan gheynat
            if ((11 - h) % 2 == 0) {
                int ordered1 = (11 - h) / 2;
                for (int i = 0; i < ordered1-1; i++)
                    myWriter.write(" ");
                myWriter.write(productPrice + "$");
                for (int i = 0; i < ordered1; i++)
                    myWriter.write(" ");
                myWriter.write("|");
            }
            if ((11 - h) % 2 == 1) {//rikhtan gheymat
                int ordered2 = (11 - h) / 2;
                for (int i = 0; i < ordered2; i++)
                    myWriter.write(" ");
                myWriter.write(productPrice + "$");
                for (int i = 0; i < ordered2; i++)
                    myWriter.write(" ");
                myWriter.write("|");
            }
            int g = 0; //rikhhtan category ha dar file.
            for (int i = 0; i < arr.size(); i++) {

                categoriesCode = arr.get(i);
                check = false;
                for (int j = name; j < data.length(); j++) {
                    if (data == null || categoriesCode == null)
                        continue;
                    if (categoriesCode.charAt(0) == data.charAt(j - 1) && categoriesCode.charAt(1) == data.charAt(j) && categoriesCode.charAt(2) == data.charAt(j + 1) && categoriesCode.charAt(3) == data.charAt(j + 2)) {
                        if ((j < id || j > (id + 4)) && (j < pSpace || j > pEnd)) {
                            categoryName = arr2.get(i);
                            if (g == 0)
                                myWriter.write(categoryName);
                            else {
                                myWriter.write("," + categoryName);
                            }
                            g++;
                        }
                    }

                }

                h++;

            }
            myWriter.write("|");
            myWriter.close();
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }
}
