import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;


public class Main {
    public static void close(Closeable c) {
        try {
            c.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public static void createIndex(String src, String path) {
        BufferedReader bfr = null;
        BufferedWriter bfw = null;
        try {
            bfr = new BufferedReader(new FileReader(src, StandardCharsets.ISO_8859_1));
            bfw = new BufferedWriter((new FileWriter(path, StandardCharsets.UTF_8)));
            int c;
            int charCounter = 0;
            bfw.write("0" + ":");
            while ((c = bfr.read()) != -1) {
                if (c == '.') {
                    bfw.write(c);
                    bfw.newLine();
                    charCounter++;
                    bfw.write(charCounter + ":");
                } else if (c != (int) '\n' && c != (int) '\r') {
                    charCounter++;
                    bfw.write(c);
                } else {
                    charCounter++;
                }
            }


        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("ERror..");
        } finally {
            close(bfw);
            close(bfr);
        }
    }

    public static void readPhrase(int phrase) {
        BufferedReader bfr = null;
        try {
            bfr = new BufferedReader(new FileReader("C:\\Users\\Jose\\Desktop\\QuijoteIndexes.txt", StandardCharsets.ISO_8859_1));
            String line = "";
            int counter = 0;
            boolean found = false;

            if (phrase == 0) {
                RandomAccessFile raf = new RandomAccessFile("C:\\Users\\Jose\\Downloads\\quijote.txt", "r");
                raf.seek(0);
                FileReader fr = new FileReader(raf.getFD());
                bfr = new BufferedReader(fr);
                System.out.println(bfr.readLine());
            } else {

                while ((line = bfr.readLine()) != null && !found) {
                    counter++;
                    if (counter == phrase) {
                        int index = Integer.parseInt(line.split(":")[0]);
                        RandomAccessFile raf = new RandomAccessFile("C:\\Users\\Jose\\Downloads\\quijote.txt", "r");
                        raf.seek(index);
                        FileReader fr = new FileReader(raf.getFD());
                        bfr = new BufferedReader(fr);
                        System.out.println(bfr.readLine());
                        found = true;
                    }
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            close(bfr);
        }
    }


    public static void main(String[] args) {

// revisar como se estan contando los caracteres;
        createIndex(args[0], args[1]);
        readPhrase(11);

    }

}