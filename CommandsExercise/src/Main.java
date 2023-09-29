import java.io.Console;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.Scanner;
import java.util.logging.ConsoleHandler;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {


    public static void ls(String command) {
        String[] commands = command.split(" ");
        System.out.println(commands.length);

        if (commands.length == 2) {

            File f = new File(commands[1]);
            if (f.exists()) {
                if (f.isFile()) {
                    System.out.println("Name: " + f.getName() + " || " + " Route: " + f.getAbsolutePath());
                } else {
                    String[] directories = f.list();
                    for (String dir : directories) {
                        System.out.println(dir);

                    }

                }
            } else {
                System.out.println("The argument is not valid. Please try again.");
            }

        } else if (commands.length == 1) {
            File f = new File(System.getProperty("user.dir"));
            String[] directories = f.list();
            for (String dir : directories) {
                System.out.println(dir);

            }
        }
    }


    public static void cp(String command) {
        String[] commands = command.split(" ");

        if (commands.length == 3) {
            File f = new File(commands[1]);
            if (f.isFile()) {
                // todo: take a look of Apache FileUtils library. It allows to do this. Ask if i could use an external library for the exam.
                // note for myself that i need to install the jar in order to use this library.
                String[] path = commands[1].split("\\\\");
                String filename = path[path.length - 1];

                File f2 = new File(commands[2], filename);
                try {
                    if (f2.createNewFile()) {
                        System.out.println("The file has been created successfully!");
                        // ig i could've used a buffered wr and fw instead. ask about this bec i don't remember properly.
                    } else
                        System.out.println("The file couldnt be created. Try again later.");

                } catch (Exception e) {
                    System.out.println("An error has occurred. Please try again later.");
                }


            }
        } else
            System.out.println("Not enough arguments or invalid ones.. Please try again.");


    }


    public static void mv(String command) {
        String[] commands = command.split(" ");

        if (commands.length == 3) {
            File f1 = new File(commands[1]);
            File f2 = new File(commands[2]);
            String[] splitspath1 = commands[1].split("\\\\");
            String filename = splitspath1[splitspath1.length - 1];


            Path path1 = Path.of(commands[1]);
            Path path2 = Path.of(commands[2] + "\\\\" + filename);


            if (f1.isFile() && f2.isDirectory()) {
                try {
                    Files.move(path1, path2, StandardCopyOption.REPLACE_EXISTING);
                    System.out.println("The file has been moved successfully.");
                } catch (Exception e) {
                    System.out.println("There was an error. ");

                }


            } else if (!f2.isDirectory()) {

                if (f1.renameTo(new File(f1.getParent(), f2.getName())))
                    System.out.println("File successfully renamed.");
            } else
                System.out.println("Failed to rename the file.");

        }


    }
    // APPARENTLY FILES.MOVE JUST TAKES 2 PATHS AND DELETES AND ADDS, IT DOESNT BOTHER ABOUT FILES OR FOLDERS.

    // TODO: OPTIMIZE THE CODE USING GETPARENT Y GETNAME. CAMBIAR TAMBIEN EL NEW DEL EJERCICIO ANTERIOR UTILIZANDO DIRECTAMENTE UN NEW FILE();


    public static void rm(String command) {
/*
El comando rm eliminará el fichero pasado por primer parámetro si es un directorio deberá dar un error.
Ampliación: Para poder borrar un directorio se usa un segundo parámetro que sería -r, esto indicará que se hará un borrado recursivo,
es decir, se borrará el contenido del directorio y luego el propio directorio.
 */
        String[] commands = command.split(" ");

        int numArgs = commands.length;
        switch (numArgs) {

            case 2:
                File f1 = new File(commands[1]);
                if (f1.isFile()) {
                    try {
                        Files.delete(f1.toPath());
                        if (!Files.exists(f1.toPath())) {
                            System.out.println("The file has been deleted successfully.");
                        }
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }

                }


                break;

            case 3:
                // RECURSION IN PROCESS..

                break;

            default:
                System.out.println("There was an error with the arguments. Try again.");

                break;


        }

    }


    public static void mkdir(String command) {

        String[] commands = command.split(" ");

        int numArgs = commands.length;
        switch (numArgs) {

            case 2:
                File f1 = new File(commands[1], "test");

                if (f1.mkdir())
                    System.out.println("Folder created successfully.");
                else
                    System.out.println("There was an error creating the folder. Try again");

                break;

            case 3:

                if (commands[2].equals("-p")) {
                    f1 = new File(commands[1]);
                    if (!f1.getParentFile().exists()) {
                        if (f1.getParentFile().mkdir()) {
                            if (f1.mkdir()) {
                                System.out.println("The folders were created successfully.");
                            }
                        }
                    } else if (f1.mkdir())
                        System.out.println("The folder were created successfully.");

                } else
                    System.out.println("Argument is not valid.");


                break;

            default:
                System.out.println("There was an error with the arguments. Try again.");

                break;

            // ASK HOW TO IMPLEMENT RECURSION HERE.
        }

    }

    public static void touch(String command) {
        Scanner sc = new Scanner(System.in);
        String[] commands = command.split(" ");
        if (commands.length == 2) {
            System.out.println("Indicate the route that you would like to create the file in: ");
            String path = sc.nextLine();
            System.out.println(path);
            File f1 = new File(path);
            // File f1 = new File(path,commands[1]); // ESTA LINEA ARREGLA EL FALLO Y DESPUES CREATENEWFILE.
            if (f1.isDirectory()) {
                if (f1.renameTo(new File(commands[1])))
                    System.out.println("File successfully created.");
            } else
                System.out.println("Invalid path.");

        } else
            System.out.println("Invalid amount of arguments. Try again");




        /*
        El comando touch creará un fichero en la ubicación indicada y con el nombre indicado como primer parámetro.
        *** Que la ubicación que se le pase al comando touch exista. ***
         */


    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String command = sc.nextLine();

        while (!command.equalsIgnoreCase("Exit")) {

            switch (command.split(" ")[0]) {

                case "ls":
                    ls(command);

                    break;

                case "cp":
                    cp(command);

                    break;

                case "mv":
                    mv(command);

                    break;
                case "rm":
                    rm(command);

                    break;
                case "mkdir":
                    mkdir(command);

                    break;
                case "touch":
                    touch(command);

                    break;
                case "grep":


                    break;

                default:
                    System.out.println("Invalid command.");
                    break;


            }
            command = sc.nextLine();
        }

    }
}
