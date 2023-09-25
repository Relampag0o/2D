import java.io.Console;
import java.io.File;
import java.util.Scanner;
import java.util.logging.ConsoleHandler;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {


    public static void ls(String command) {
        String[] commands = command.split(" ");

        if (commands.length > 1) {
            File f = new File(commands[1]);
            if (f.isFile()) {
                System.out.println("Name: " + f.getName() + " route: " + f.getAbsolutePath());
            } else {
                String[] directories = f.list();
                for (String dir : directories) {
                    System.out.println(dir);

                }

            }


        } else {
            File f = new File(System.getProperty("user.dir"));
            String[] directories = f.list();
            for (String dir : directories) {
                System.out.println(dir);

            }
        }
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


            }
            command = sc.nextLine();
        }

    }
}
