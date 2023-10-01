import java.awt.*;
import java.io.Console;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Scanner;
import java.util.logging.ConsoleHandler;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {

    public static void recursiveDelete(String path) {
        File f1 = new File(path);

        for (File f : f1.listFiles()) {
            if (f.isDirectory())
                recursiveDelete(f.getPath());
            else
                f.delete();
        }
        f1.delete();

    }


    public static void recursiveMkdir(String path) {

        File f1 = new File(path);

        if (f1.getParentFile().exists()) {
            f1.mkdir();
        } else
            recursiveMkdir(f1.getParent());

        f1.mkdir();


    }


    public static void recursiveLs(String path) {
        File f1 = new File(path);

        if (f1.isDirectory()) {
            System.out.println("Folder: " + f1.getName());
            File[] dirs = f1.listFiles();

            if (dirs != null) {
                for (File f : dirs) {
                    recursiveLs(f.getAbsolutePath());
                }
            }
        } else {
            System.out.println("File: " + f1.getName());
        }
    }

    public static void recursiveGrep(String path, String occurrence) {
        // i honestly dont know how this works. been testing it and somehow it does what i expect to.
        // any feedback is much apreciated!
        File f1 = new File(path);

        if (f1.isDirectory()) {
            File[] dirs = f1.listFiles();
            if (dirs.length > 0) {
                for (File f : dirs) {
                    if (f.getName().contains(occurrence)) {
                        if (f.isDirectory()) {
                            System.out.println("Folder: " + f.getName());
                            recursiveGrep(f.getPath(), occurrence);
                        } else
                            System.out.println("File: " + f.getName() + " Parent folder: " + f.getParent());
                    } else
                        recursiveGrep(f.getPath(), occurrence);

                }
            }
        } else if (f1.getName().contains(occurrence)) {
            System.out.println("File: " + f1.getName());
        }

    }


    public static void ls(String command) {

// THIS SECTION HAS ITS RECURSIVE METHOD. ADD -r as third param to test it!

        String[] commands = command.split(" ");
        int numArgs = commands.length;

        switch (numArgs) {
            case 1:
                File f = new File(System.getProperty("user.dir"));
                String[] directories = f.list();
                for (String dir : directories) {
                    System.out.println(dir);
                }
                break;

            case 2:
                f = new File(commands[1]);
                if (f.exists()) {
                    if (f.isFile()) {
                        System.out.println("Name: " + f.getName() + " || " + " Route: " + f.getAbsolutePath());
                    } else {
                        directories = f.list();
                        for (String dir : directories) {
                            System.out.println(dir);

                        }
                    }
                } else
                    System.out.println("The file is not valid or doesnt exist.");
                break;

            case 3:
                if (commands[2].equals("-r"))
                    recursiveLs(commands[1]);
                break;

            default:
                System.out.println("Invalid amount of arguments. Try again");
                break;

        }

    }


    public static void cp(String command) throws IOException {
        String[] commands = command.split(" ");
        if (commands.length == 3) {

            File f1 = new File(commands[1]);
            File f2 = new File(commands[2]);

            if (f1.isFile() && f2.exists()) {

                try {
                    Files.copy(f1.toPath(), f2.toPath().resolve(f1.getName()), StandardCopyOption.REPLACE_EXISTING);
                    //Files.copy(f1.toPath(), Path.of(f2.getPath(),f1.getName()), StandardCopyOption.REPLACE_EXISTING);
                    // BOTH OPTIONS WORK BUT I WANTED TO TEST THE RESOLVE FUNCTION.
                    System.out.println("The file has been copied successfully.");
                } catch (IOException e) {
                    System.out.println("Invalid files.");
                }
            } else System.out.println("The files dont exist. Try again later or specify a valid argument.");
        } else System.out.println("Invalid amount of arguments. Try again later");


    }


    public static void mv(String command) {
        String[] commands = command.split(" ");
        if (commands.length == 3) {
            File f1 = new File(commands[1]);
            File f2 = new File(commands[2]);
            if (f1.isFile()) {
                if (f2.isDirectory()) {
                    try {
                        Files.move(f1.toPath(), f2.toPath().resolve(f1.getName()), StandardCopyOption.REPLACE_EXISTING);
                        System.out.println("The file has been moved successfully.");
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                } else { //if (f1.renameTo(new File(f1.getParent(),f2.getName())))
                    // ask if i could do this withouth the new...
                    // checking if this is possible with Files.move again instead rename.

                    try {
                        Files.move(f1.toPath(), f1.toPath().resolveSibling(f2.getName()), StandardCopyOption.REPLACE_EXISTING);
                        System.out.println("The file has been renamed successfully.");
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }


                }

            }
        } else
            System.out.println("Invalid amount of arguments. Try again");
    }


    public static void rm(String command) {

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

                } else
                    System.out.println("The -r recursive param is needed for this functionality.");


                break;

            case 3:
                if (commands[2].equals("-r") && new File(commands[1]).isDirectory()) {
                    f1 = new File(commands[1]);
                    recursiveDelete(f1.getPath());


                }

                break;

            default:
                System.out.println("There was an error with the arguments. Try again.");

                break;


        }

    }


    public static void mkdir(String command) {

        // This section has a recursive method. use -r as second param to test it!
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

                } else if (commands[2].equals("-r")) {
                    recursiveMkdir(commands[1]);
                    System.out.println("The folder were created successfully.");
                } else
                    System.out.println("Arguments are not valid.");


                break;

            default:
                System.out.println("There was an error with the arguments. Try again.");

                break;


        }
    }

    public static void touch(String command) {
        String[] commands = command.split(" ");
        if (commands.length == 3) {
            File f1 = new File(commands[2], commands[1]);
            if (f1.getParentFile().isDirectory()) {
                try {
                    if (f1.createNewFile()) {
                        System.out.println("The file has been successfully created.");
                    }
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }

        }
    }

    public static void grep(String command) {

        // This section has a recursive method. check in the first functions and unncomment this line of code to make it work
        // // you also need to add the occurrence to the second param of the function:
        // recursiveGrep(commands[1],"your occurrence");

        Scanner sc = new Scanner(System.in);
        String[] commands = command.split(" ");

        if (commands.length == 3) {
            File f1 = new File((commands[2]));
            File[] files = f1.listFiles();
            if (f1.isDirectory()) {
                for (File f : files) {
                    if (f.getName().contains(commands[1]))
                        System.out.println(f.getName());
                    else
                        System.out.println("There werent occurrences that fill the search. ");
                }
            } else
                System.out.println("The path is not a directory. Try again");
        } else if (commands.length == 2) {
            File f1 = new File(System.getProperty("user.dir"));
            File[] files = f1.listFiles();


            for (File f : files) {
                if (f.getName().contains(commands[1]))
                    System.out.println(f.getName());

            }

        }


    }


    public static void main(String[] args) throws IOException {
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
                    grep(command);

                    break;

                default:
                    System.out.println("Invalid command.");
                    break;

            }
            command = sc.nextLine();
        }

    }
}
