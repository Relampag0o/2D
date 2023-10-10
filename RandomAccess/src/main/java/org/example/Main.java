package org.example;

import java.io.*;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {

    public static void createIndex(String src, String dst) {
        File f = new File(src);
        File f2 = new File(dst);
        try (BufferedReader bfr = new BufferedReader(new FileReader(f));
             BufferedWriter bfw = new BufferedWriter(new FileWriter(dst))) {
            String line = bfr.readLine();
            int index = line.getBytes().length;

            while ((line = bfr.readLine()) != null) {
                String[] words = line.split(";");

                bfw.write(words[0] + ";" + index + '\n');
                System.out.println(words[0] + ";" + index + '\n');
                index += line.getBytes().length;
            }

        } catch (Exception e) {
            System.out.println("Error...");
        }
    }

    public static void searchInfo(String dni, String indexFile, String dbSrc) {

        File f = new File(indexFile);
        try {
            BufferedReader bfr = new BufferedReader(new FileReader(f));
            String line = "";
            int index = 0;
            boolean found = false;
            while ((line = bfr.readLine()) != null && !found) {
                String[] words = line.split(";");
                if (words[0].equalsIgnoreCase(dni)) {
                    index = Integer.parseInt(words[1]);
                    found = true;
                }
            }

            if (found) {
                try {
                    RandomAccessFile raf = new RandomAccessFile(dbSrc, "r");
                    raf.seek(index);
                    FileReader fr = new FileReader(raf.getFD());
                    BufferedReader bfr2 = new BufferedReader(fr);
                    System.out.println(bfr2.readLine());
                } catch (Exception e) {
                    System.out.println("Error..");
                }


            }


        } catch (Exception e) {
            System.out.println("error.");

        }
    }


    public static void main(String[] args) {

        if (args.length == 3) {
            switch (args[0]) {
                case "-g":
                    createIndex(args[1], args[2]);
                    break;
                default:
                    System.err.println("Not supported opperattion");
                    break;
            }
        } else if (args.length == 4) {
            switch (args[0]) {
                case "-f":

                    // -f 84939447V C:\Users\Jose\Desktop\output2.txt C:\Users\Jose\Downloads\db.csv
                    searchInfo(args[1], args[2], args[3]);

                    break;

            }
        } else
            System.out.println("Invalid amount of arguments..");
    }

}

