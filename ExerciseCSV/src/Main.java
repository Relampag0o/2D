import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.*;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {
    public static int calculateMode(LinkedList<Student> students) {
        HashMap<Integer, Integer> results = new HashMap<Integer, Integer>();
        int maxValue = 0;
        int mostFrecuentNumber = 0;
        for (Student s : students) {
            if (results.containsKey(s.getCalification()))
                results.put(s.getCalification(), results.get(s.getCalification()) + 1);
            else
                results.put(s.getCalification(), 1);
        }


        for (Map.Entry<Integer, Integer> entry : results.entrySet()) {
            if (entry.getValue() > maxValue) {
                System.out.println();
                maxValue = entry.getValue();
                mostFrecuentNumber = entry.getKey();
            }
        }
        return mostFrecuentNumber;
    }


    public static double[] calculatePassedAndFailledStudents(LinkedList<Student> students) {
        int counter = 0;
        // the first part of array is students that passed the exams.
        // second part of array is students that couldnt pass the exams.
        double[] percents = new double[2];
        for (Student s : students) {
            if (s.getCalification() >= 5)
                percents[0]++;
            else
                percents[1]++;
        }

        percents[0] = percents[0] * 100 / students.size();
        percents[1] = percents[1] * 100 / students.size();
        return percents;

    }


    public static void main(String[] args) {
        String pathsource = "C:\\Users\\Jose\\Desktop\\alumnos.csv";
        String pathdest = "C:\\Users\\Jose\\Desktop\\output.txt";
        File f1 = new File(pathsource);
        File f2 = new File(pathdest);

        try (BufferedReader bfr = new BufferedReader(new FileReader(f1, StandardCharsets.UTF_8));
             BufferedWriter bfw = new BufferedWriter(new FileWriter(f2, StandardCharsets.UTF_8))) {
            String line = bfr.readLine();
            LinkedList<Student> students = new LinkedList<Student>();
            double counter = 0;
            while ((line = bfr.readLine()) != null) {
                String[] studentsinfo = line.split(";");
                students.add(new Student(studentsinfo[0], studentsinfo[1], Integer.parseInt(studentsinfo[2])));
                counter += Integer.parseInt(studentsinfo[2]);
            }
            System.out.println("Amount of students: " + students.size());
            System.out.println("Average: " + counter / students.size());
            bfw.write("Amount of students: " + students.size());
            bfw.newLine();
            bfw.write("Average: " + counter / students.size());
            bfw.newLine();
            System.out.println("Mode: " + calculateMode(students));
            bfw.write("Mode: " + calculateMode(students));
            Collections.sort(students);
            System.out.println((students.get(students.size() / 2).getCalification()));
            bfw.newLine();
            bfw.write((students.get(students.size() / 2).getCalification()));
            bfw.newLine();
            System.out.println("Students that passed: " + calculatePassedAndFailledStudents(students)[0] + "Students who failled: " + calculatePassedAndFailledStudents(students)[1]);
<<<<<<< HEAD
            bfw.write("Students that passed: " + calculatePassedAndFailledStudents(students)[0] + "%" + " Students who failled: " + calculatePassedAndFailledStudents(students)[1] + "%");
            
=======
bfw.write("Students that passed: " + calculatePassedAndFailledStudents(students)[0] + "%" + " Students who failled: " + calculatePassedAndFailledStudents(students)[1] + "%");
>>>>>>> parent of 4cce797 (.)
        } catch (Exception e) {
            System.out.println("Error..");
        }
    }

}