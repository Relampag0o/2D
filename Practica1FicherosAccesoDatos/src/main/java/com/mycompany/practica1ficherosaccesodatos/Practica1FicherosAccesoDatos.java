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

    public static void main(String[] args) {

        Scanner scannernums = new Scanner(System.in);
        Scanner scannerwords = new Scanner(System.in);

        LinkedList<File> files = new LinkedList();

        int choice = 1;
        do {

            System.out.println("What would you like to do?");
            System.out.println("1. Add a File");
            System.out.println("2. Modify a File");
            System.out.println("3. Look for a File");
            System.out.println("4. List all Files");
            System.out.println("5. Delete a File");
            System.out.println("6. Exit");

            choice = scannernums.nextInt();

            switch (choice) {

                case 1:

                    System.out.println("Insert the name: ");
                    String filename = scannerwords.nextLine();
                    System.out.println("Insert the size: ");
                    double size = scannernums.nextDouble();
                    System.out.println("Insert the extension: ");
                    String extension = scannerwords.nextLine();

                    files.add(new File(filename, extension, size));

                    System.out.println("The file has been added sucessfully!");

                    break;

                case 2:

                    System.out.println("Insert the id you would like to modify: ");
                    int id = scannernums.nextInt();

                    for (int i = 0; i < files.size(); i++) {

                        if (files.get(i).getId() == id) {

                            System.out.println("File to modify: " + files.get(i).getId());
                            System.out.println("Insert the new name: ");
                            files.get(i).setName(scannerwords.nextLine());
                            System.out.println("Insert the new size: ");
                            files.get(i).setSize(scannernums.nextDouble());
                            System.out.println("The file has been modified sucessfully!");
                        }

                    }

                    break;
                case 3:
                    System.out.println("Insert the name or extension you would like to search: ");
                    filename = scannerwords.nextLine();

                    for (int i = 0; i < files.size(); i++) {

                        if (files.get(i).getName().equals(filename) || files.get(i).getExtension().equals(filename)) {
                            System.out.println(" + File " + i + ":" + "" + files.get(i).getName() + "." + files.get(i).getExtension());
                            System.out.println(" - Size: " + files.get(i).getSize() + "kb");
                            System.out.println("");
                        }
                    }

                    break;
                case 4:
                    System.out.println("Listing all files: ");
                    for (int i = 0; i < files.size(); i++) {
                        System.out.println(" + File " + String.format("%03d", i) + i + ":" + "" + files.get(i).getName() + "." + files.get(i).getExtension());
                        System.out.println(" - Size: " + files.get(i).getSize() + " kb");
                        System.out.println("");
                    }

                    break;
                case 5:

                    System.out.println("Insert the name or extension you would like to delete: ");
                    filename = scannerwords.nextLine();

                    for (File file : files) {

                        if (file.getName().equals(filename) || file.getExtension().equals(filename)) {
                            files.remove(file);
                        }
                    }

                    System.out.println("The file has been deleted sucesfully!");
                    break;

                case 6:
                    System.out.println("Exiting program..");

            }

        } while (choice != 6);

    }
}
