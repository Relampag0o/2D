import java.io.*;
import java.util.*;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {
    public static int calculateMode(LinkedList<Student> students) {
        HashMap<String, Integer> results = new HashMap<String, Integer>();
        int maxValue = 0;

        for (Student s : students) {
            if (results.containsKey(s.getSpeciality()))
                results.put(s.getSpeciality(), +1);
            else
                results.put(s.getSpeciality(), 1);
        }
        for (Map.Entry<String, Integer> entry : results.entrySet()) {
            if (entry.getValue() > maxValue)
                maxValue = entry.getValue();
        }
        return maxValue;
        // dont know if there is any way to calculate the maxvalue of the map withouth iterating it.
        // ive heard about Collections.max() but it doesnt work.
    }


    public static void main(String[] args) {
        String pathsource = "C:\\Users\\josem\\Desktop\\alumnos.csv";
        String pathdest = "C:\\Users\\josem\\Desktop\\output.txt";
        File f1 = new File(pathsource);
        File f2 = new File(pathdest);

        try (BufferedReader bfr = new BufferedReader(new FileReader(f1));
             BufferedWriter bfw = new BufferedWriter(new FileWriter(f2))) {
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
            System.out.println((students.get(students.size() / 2).getCalification() + students.get((students.size() - 1) / 2).getCalification()) / 2.0);


        } catch (Exception e) {
            System.out.println("Error..");
        }
    }

}