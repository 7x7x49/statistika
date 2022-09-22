package com.company;
import java.io.*;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {

    public static void main(String[] args) {
        System.out.print((char) 27 + "[33mEnter the path to the file: "+ (char)27 + "[0m");
        Scanner in = new Scanner(System.in);
        String path = in.nextLine();                 //путь до файла, который нужно прочитать
        Pattern pattern = Pattern.compile(" ");      //создание РВ (ищет пробел)

        try(FileInputStream fis = new FileInputStream(path);    //файл, который собираемся анализировать
            FileWriter fileWriter = new FileWriter("WithoutComments.txt")){

            BufferedReader br = new BufferedReader(new InputStreamReader(fis));   //позволяет прочитать данные файла построчно
            String string;      //текущая строка
            int length = 0;     //кол-во символов
            int spaces = 0;     //кол-во пробелов
            while((string = br.readLine()) != null){
                length += string.length()+1;                   //+перенос строк (редлайн его не учитывает)
                Matcher matcher = pattern.matcher(string);     //поиск пробелов
                while (matcher.find()){
                    spaces++;
                }
            }

            String tcif = "Total characters in file: " + length;
            String tcwsif = "Total characters without spaces in file: " + (length - spaces);
            String twif = "Total words in file: " + (spaces+1);
            System.out.println((char) 27 + "[35m" +  tcif + (char)27 +  "[0m");
            System.out.println((char) 27 + "[36m" + tcwsif + (char)27 + "[0m");
            System.out.println((char) 27 + "[34m" +  twif + (char)27 +  "[0m");

            fileWriter.write(tcif + "\n" + tcwsif + "\n" + twif);
        }
        catch(IOException ex) {
           ex.printStackTrace();
        }

    }
}
