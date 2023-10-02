import java.io.*;
import java.util.Scanner;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        try (BufferedReader bfr = new BufferedReader(new FileReader("C:\\Users\\Jose\\Desktop\\reescribirr.txt"));
             BufferedWriter bfw = new BufferedWriter(new FileWriter("C:\\Users\\Jose\\Desktop\\sobreescribir.txt"))) {
            String line = "";
            int lineindex = 1;

            System.out.println("Specify the occurrence that you would like to search: ");
            String occurrence = sc.nextLine();
            if (new File("C:\\Users\\Jose\\Desktop\\reescribirr.txt").exists() &&
                    new File("C:\\Users\\Jose\\Desktop\\sobreescribir.txt").exists()) {

                while ((line = bfr.readLine()) != null) {
                    String[] occurrences = line.split("[\\s.,]+");
                    for (int i = 0; i < occurrences.length; i++) {
                        if (occurrences[i].contains(occurrence)) {
                            bfw.write("Line: " + lineindex + " Column: " + i + "");


                        }


                    }
                    lineindex++;
                    bfw.newLine();
                }


                bfr.close();
                bfw.close();
            } else
                System.out.println("The paths are either wrong or doesnt exist.. Try again later");
        } catch (Exception e) {
            System.out.println("Error..");
        }


    }
}