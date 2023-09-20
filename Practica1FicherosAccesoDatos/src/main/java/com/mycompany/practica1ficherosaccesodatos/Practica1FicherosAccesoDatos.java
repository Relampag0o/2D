/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */
package com.mycompany.practica1ficherosaccesodatos;

import java.util.LinkedList;
import java.util.Scanner;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author josem
 */
public class Practica1FicherosAccesoDatos {

    public Scanner scannerletras = new Scanner(System.in);

    public static void showMenuOptions() {
        System.out.println("What would you like to do?");
        System.out.println("1. Add a File");
        System.out.println("2. Modify a File");
        System.out.println("3. Look for a File");
        System.out.println("4. List all Files");
        System.out.println("5. Delete a File");
        System.out.println("6. Search files for extension");
        System.out.println("7. Display different extensions");

        System.out.println("9. Exit");
    }

    public static void addFile(LinkedList<File> files) {

        try {
            Scanner scannernums = new Scanner(System.in);
            Scanner scannerwords = new Scanner(System.in);

            System.out.println("Insert the name and extension: ");
            String nameextension = scannerwords.nextLine();

            System.out.println("Insert the size: ");
            double size = scannernums.nextDouble();

            String[] fulldetails = nameextension.split("\\.");
            files.add(new File(fulldetails[0], "." + fulldetails[1], size, ""));

            System.out.println("The file has been added sucessfully!");
        } catch (Exception e) {
            System.out.println("The file couldnt be added. Please, try again later.");
        }
    }

    // reminder to ask if there is any way to add a try-catch checking if the scanner receives a String.
    // if thats the case we just ask for another input again, waiting for a number.
    public static void modifyFile(LinkedList<File> files) {
        Scanner scannernums = new Scanner(System.in);
        Scanner scannerwords = new Scanner(System.in);
        int id = 0;
        do {
            System.out.println("Insert the id of the file that you would like to modify: ");
            id = scannernums.nextInt();

        } while (id < 0 || id > files.size());

        for (int i = 0; i < files.size(); i++) {

            if (files.get(i).getId() == id) {

                System.out.println("File to modify: " + files.get(i).getId());
                System.out.println("Insert the new fullname: ");
                String fullname = scannerwords.nextLine();
                String details[] = fullname.split("\\.");

                files.get(i).setName(details[0]);
                files.get(i).setExtension("." + details[1]);
                System.out.println("Insert the new size: ");
                files.get(i).setSize(scannernums.nextDouble());
                System.out.println("The file has been modified sucessfully!");
            }

        }

    }

    public static void searchFile(LinkedList<File> files) {
        Scanner scannernums = new Scanner(System.in);
        Scanner scannerwords = new Scanner(System.in);
        System.out.println("Insert the name or extension you would like to search: ");

        String filename = scannerwords.nextLine();

        for (int i = 0; i < files.size(); i++) {
            String fulldetailss = files.get(i).getName() + files.get(i).getExtension();

            if (fulldetailss.contains(filename)) {
                System.out.println(" + File " + String.format("%02d", files.get(i).getId()) + ": " + "" + files.get(i).getName() + files.get(i).getExtension());
                System.out.println(" - Size: " + files.get(i).getSize() + " kb");
                System.out.println(" * Author: " + files.get(i).getAuthor());
                System.out.println("");

            }
        }

    }

    public static void listFiles(LinkedList<File> files) {
        int counter = 0;
        if (files.size() <= 0) {
            System.out.println("Sadly there arent any files to be shown. Please try again later!");
        } else {
            System.out.println("Listing all files: ");
            System.out.println("");

            for (int i = 0; i < files.size(); i++) {
                System.out.println(" + File " + String.format("%02d", files.get(i).getId()) + ": " + "" + files.get(i).getName() + files.get(i).getExtension());
                System.out.println(" - Size: " + files.get(i).getSize() + " kb");
                System.out.println("");
                counter += files.get(i).getSize();
            }
            System.out.println("Total size of files: " + counter + " kb");
        }

    }

    public static void deleteFile(LinkedList<File> files) {

        Scanner scannernums = new Scanner(System.in);
        Scanner scannerwords = new Scanner(System.in);
        System.out.println("Insert the id to search the file and delete it: ");
        int id = scannerwords.nextInt();
        boolean deleted = false;
        File tobedeleted = null;
        for (File file : files) {

            if (file.getId() == id) {
                tobedeleted = file;
                deleted = true;

            }
        }

        if (!deleted) {
            System.out.println("The file couldnt be deleted!");
        } else {
            files.remove(tobedeleted);
            System.out.println("The file has been deleted sucesfully!");
        }

    }

    public static void findExtension(LinkedList<File> files) {
        Scanner scannernums = new Scanner(System.in);
        Scanner scannerwords = new Scanner(System.in);
        System.out.println("Insert the extension you would like to search: ");
        String extension = scannerwords.nextLine();
        int counter = 0;
        boolean found = false;
        for (int i = 0; i < files.size(); i++) {

            if (files.get(i).getExtension().equals(extension)) {
                System.out.println(" + File " + String.format("%02d", files.get(i).getId()) + ": " + "" + files.get(i).getName() + files.get(i).getExtension());
                System.out.println(" - Size: " + files.get(i).getSize() + " kb");
                System.out.println("");
                counter += files.get(i).getSize();
                found = true;

            }

        }
        if (found) {
            System.out.println("Total size of the extensions " + extension + ": " + counter + " kbs");
        } else {
            System.out.println("There arent available files. ");
        }

    }

    public static void displayExtensions(LinkedList<File> files) {
        String[] extensions = new String[files.size()];
        Map<String, Integer> extensionsmap = new HashMap<>();
        for (int i = 0; i < files.size(); i++) {

            extensions[i] = files.get(i).getExtension();
        }

        for (String extension : extensions) {
            if (extensionsmap.containsKey(extension)) {
                extensionsmap.put(extension, extensionsmap.get(extension) + 1);
            } else {
                extensionsmap.put(extension, 1);
            }
        }

        for (Map.Entry<String, Integer> entry : extensionsmap.entrySet()) {
            String extension = entry.getKey();
            int amount = entry.getValue();
            System.out.println("Extension: " + extension + ", amount: " + amount);
        }

    }

    public static void main(String[] args) {

        // Reminder to ask if it would be better to add the scanners as static variables to avoid
        // making so many calls on each method. 
        Scanner scannernums = new Scanner(System.in);

        LinkedList<File> files = new LinkedList();

        // random instances to check if everything works as intended. 
        files.add(new File("pepe", ".pdf", 900, "jose"));
        files.add(new File("juan", ".csv", 600, "jose"));
        files.add(new File("contrasenias", ".txt", 900, "jose"));
        files.add(new File("practica1", ".pdf", 600, "jose"));

        int choice = 0;

        do {
            try {
                showMenuOptions();

                choice = scannernums.nextInt();

                switch (choice) {

                    case 1:

                        addFile(files);

                        break;

                    case 2:

                        modifyFile(files);

                        break;
                    case 3:
                        searchFile(files);

                        break;
                    case 4:
                        listFiles(files);

                        break;
                    case 5:

                        deleteFile(files);
                        break;

                    case 6:
                        findExtension(files);
                        break;

                    case 7:
                        displayExtensions(files);
                        break;
                    case 9:
                        System.out.println("Exiting program..");

                        break;

                    default:
                        System.out.println("Invalid command. Please try again");
                        break;

                }

            } catch (Exception e) {
                System.out.print("Please, do not insert non-numeric characters!");
                scannernums.nextLine();
                // Im clearing the buffer so i avoid any possible troubles. I need to ask if this is needed at all.
            }

        } while (choice != 9);

    }
}
