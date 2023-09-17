/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */
package com.mycompany.practica1ficherosaccesodatos;

import java.util.LinkedList;
import java.util.Scanner;

/**
 *
 * @author josem
 */
public class Practica1FicherosAccesoDatos {

    public static void showMenuOptions() {
        System.out.println("What would you like to do?");
        System.out.println("1. Add a File");
        System.out.println("2. Modify a File");
        System.out.println("3. Look for a File");
        System.out.println("4. List all Files");
        System.out.println("5. Delete a File");
        System.out.println("6. Exit");
    }

    public static void addFile(LinkedList<File> files) {
        Scanner scannernums = new Scanner(System.in);
        Scanner scannerwords = new Scanner(System.in);

        System.out.println("Insert the name and extension: ");
        String nameextension = scannerwords.nextLine();
        System.out.println("Insert the size: ");
        double size = scannernums.nextDouble();
        String[] fulldetails = nameextension.split("\\.");
        files.add(new File(fulldetails[0], "." + fulldetails[1], size, ""));

        System.out.println("The file has been added sucessfully!");

    }

    public static void modifyFile(LinkedList<File> files) {
        Scanner scannernums = new Scanner(System.in);
        Scanner scannerwords = new Scanner(System.in);
        int id = 0;
        do {
            System.out.println("Insert the id of the file that you would like to modify: ");
            id = scannernums.nextInt();

        } while (id < 0 || id >files.size());

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

        if (files.size() <= 0) {
            System.out.println("Sadly there arent any files to be shown. Please try again later!");
        } else {
            System.out.println("Listing all files: ");

            for (int i = 0; i < files.size(); i++) {
                System.out.println(" + File " + String.format("%02d", files.get(i).getId()) + ": " + "" + files.get(i).getName() + files.get(i).getExtension());
                System.out.println(" - Size: " + files.get(i).getSize() + " kb");
                System.out.println("");
            }
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

    public static void main(String[] args) {

        // ask if it would be better to add the scanners as static variables to avoid
        // making so many calls on each method. 
        Scanner scannernums = new Scanner(System.in);

        LinkedList<File> files = new LinkedList();

        files.add(new File("pepe", ".pdf", 900, "jose"));
        files.add(new File("juan", ".pdf", 600, "jose"));
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
                        System.out.println("Exiting program..");

                        break;

                    default:
                        System.out.println("Invalid command. Please try again");
                        break;

                }

            } catch (Exception e) {
                System.out.println("Please, do not insert non-numeric characters!");
                scannernums.nextLine();
                // i clear the buffer so i avoid any possible troubles. i need to ask if this is needed at all.
            }

        } while (choice != 6);

    }
}
