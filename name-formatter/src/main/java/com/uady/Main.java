package com.uady;

import java.util.List;
import java.util.Scanner;

import com.uady.util.MyFileReader;

public class Main {
    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            System.out.print("Enter the path: ");
            String filePath = scanner.nextLine();

            List<String> nameList = MyFileReader.readFile(filePath);
            if (nameList != null) {
                List<String> resultNamesList = NameProcessor.processNames(nameList);
                NamePrinter.printNames(resultNamesList);
            }
        }
    }
}