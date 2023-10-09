package org.example;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {
    public static void main(String[] args) {
        LinkedList<MyProcess> processes = new LinkedList<>();
        processes.add(new MyProcess("p1", 0, 5));
        processes.add(new MyProcess("p2", 1, 3));
        processes.add(new MyProcess("p3", 2, 2));
        processes.add(new MyProcess("p4", 3, 2));

        for (MyProcess process : processes) {
            String command = "java";
            String mainClass = "org.example.MyProcess";
            ProcessBuilder pb = new ProcessBuilder(command, mainClass, String.valueOf(process.getExetime()));
            pb.directory(new File("C:\\Users\\Jose\\IdeaProjects\\RepoGit2D\\2D\\PBTask\\target\\classes"));

            try {
                System.out.println("I am the process " + process.getName() + " and i am starting my execution: ");
                Process p = pb.start();

                int exitCode = p.waitFor();
                System.out.println("Finishing process " + process.getName() + " with the exit code: " + exitCode);

            } catch (IOException | InterruptedException e) {
                System.out.println(process.getName() + ": There was an error..");
                e.printStackTrace();
            }
        }
    }
    }

