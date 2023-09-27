import java.io.Console;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
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
                    if(f2.createNewFile()){
                        System.out.println("The file has been created successfully!");
                        // ig i could've used a buffered wr and fw instead. ask about this bec i don't remember properly.
                    }else
                        System.out.println("The file couldnt be created. Try again later.");

                } catch (Exception e) {
                    System.out.println("An error has occurred. Please try again later.");
                }



            }
        } else
            System.out.println("Not enough arguments or invalid ones.. Please try again.");


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


                    break;
                case "rm":


                    break;
                case "mkdir":


                    break;
                case "touch":


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
