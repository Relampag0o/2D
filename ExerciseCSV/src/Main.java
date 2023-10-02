import java.io.*;
import java.util.LinkedList;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {
    public static void main(String[] args) {
        String pathsource = "C:\\Users\\Jose\\Desktop\\alumnos.csv";
        String pathdest = "C:\\Users\\Jose\\Desktop\\output.txt";
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
            bfw.write("Amount of students: " + students.size());

            System.out.println("..");
        } catch (Exception e) {
            System.out.println("..");
        }
    }

}