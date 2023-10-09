import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.*;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {

    public static void writeData(LinkedList<Student> students, BufferedWriter bfw, BufferedReader bfr, File f2) {
        try {
            String line = bfr.readLine();
            double counter = 0;
            while ((line = bfr.readLine()) != null) {
                String[] studentsinfo = line.split(";");
                students.add(new Student(studentsinfo[0], studentsinfo[1], Integer.parseInt(studentsinfo[2])));
                counter += Integer.parseInt(studentsinfo[2]);
            }
            bfw.write("Amount of students: " + students.size() + '\n');
            bfw.write("Average: " + counter / students.size() + '\n');
            bfw.write("Mode: " + calculateMode(students) + '\n');
            Collections.sort(students);
            bfw.write("Median:" + "" + (students.get(students.size() / 2).getCalification()) + '\n');
            calculatePassedAndFailedStudents(students, bfw);
            listSpecialties(students, bfw);
            calitifactionsBySpecialty(students,"ciencias",bfw);

            bfw.write("---AMPLIATION TASKS---" + '\n');
            bfw.write("TASK 1: Search names that start with x occurrence: " + '\n');
            countStudentsByOccurrence(students, bfw, "j");
            bfw.write("TASK 2: Search names that have tilde: " + '\n');
            searchNamesWithTilde(students, f2, bfw);

        } catch (IOException io) {
            System.out.println("There was an error either creating or writing the file...");
        }
    }

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
                maxValue = entry.getValue();
                mostFrecuentNumber = entry.getKey();
            }
        }
        return mostFrecuentNumber;
    }


    public static void calculatePassedAndFailedStudents(LinkedList<Student> students, BufferedWriter bfw) {
        int counter = 0;
        // the first part of array is students that passed the exams.
        // second part of array is students that couldnt pass the exams.
        // this was the fastest way to output both results withouth using another method or unnecessary lines.
        double[] percents = new double[2];
        for (Student s : students) {
            if (s.getCalification() >= 5)
                percents[0]++;
            else
                percents[1]++;
        }
        try {
            bfw.write("Students that passed: " + Math.round(percents[0]) * 100 / students.size() + "%" + '\n' + "Students who failed: " + Math.round(percents[1]) * 100 / students.size() + "%" + '\n');
        } catch (IOException e) {
            throw new RuntimeException(e);
        }


    }

    public static void listSpecialties(LinkedList<Student> students, BufferedWriter bfw) {
        LinkedList<String> specialties = new LinkedList<String>();
        String s = "";
        for (Student st : students) {
            if (!specialties.contains(st.getSpeciality()))
                specialties.add(st.getSpeciality());


        }
        for (int i = 0; i < specialties.size(); i++) {
            if (i == specialties.size() - 1)
                s += specialties.get(i) + "";
            else
                s += specialties.get(i) + ", ";

        }

        try {
            bfw.write(s + '\n');
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    public static void calitifactionsBySpecialty(LinkedList<Student> students, String speciality,BufferedWriter bfw) {
        double[] califications = new double[3];
        int amountOfStudentsBySpeciality = 0;
        for (Student s : students) {
            if (s.getSpeciality().equalsIgnoreCase(speciality)) {
                califications[0] += (double) s.getCalification();
                amountOfStudentsBySpeciality++;
                if (s.getCalification() >= 5)
                    califications[1] = califications[1] + 1;
                else
                    califications[2] = califications[2] + 1;
            }

        }
        try {
            bfw.write(
             speciality + ": " + '\n' + " Average: " + califications[0] / amountOfStudentsBySpeciality +
                    '\n' + " Students that passed: " + Math.round(califications[1] * 100 / amountOfStudentsBySpeciality) + "% " + '\n' + " Students that failed: " +
                    Math.round(califications[2] * 100 / amountOfStudentsBySpeciality) + "%");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    public static void countStudentsByOccurrence(LinkedList<Student> students, BufferedWriter bfw, String occurrence) {
        LinkedList<String> names = new LinkedList<String>();

        int counter = 0;
        for (Student s : students) {
            if (String.valueOf(s.getName().charAt(0)).equalsIgnoreCase(occurrence)) {
                names.add(s.getName());
                counter++;
            }
        }
        for (String n : names) {
            try {
                bfw.write("Name: " + n + '\n');
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        try {
            bfw.write("There are " + counter + " names that start with the occurrence " + occurrence + '\n');
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    public static String searchNamesWithTilde(LinkedList<Student> students, File f, BufferedWriter bfw) {
        Set<String> namesWithTilde = new TreeSet<>();
        int[] asciiCodes = {225, 233, 237, 243, 250};
        // im using the TreeSet because it automatically sorts the list.
        // i couldve used the literally characters to compare them with the name but using ascii codes looks better! (char[] tildes = {á,é,í..};)
        for (Student s : students) {
            String name = s.getName();
            for (int i = 0; i < name.length(); i++) {
                Character c = name.charAt(i);
                for (int asciiCode : asciiCodes) {
                    if ((int) c == asciiCode)
                        namesWithTilde.add(s.getName());
                }

            }


        }
        for (String w : namesWithTilde) {
            try {
                bfw.write("Name: " + w + '\n');
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

        return "";
    }

    public static void main(String[] args) {
        File f1 = new File("C:\\Users\\Jose\\Desktop\\alumnos.csv");
        File f2 = new File("C:\\Users\\Jose\\Desktop\\output.txt");
        try (BufferedReader bfr = new BufferedReader(new FileReader(f1, StandardCharsets.UTF_8));
             BufferedWriter bfw = new BufferedWriter(new FileWriter(f2, StandardCharsets.UTF_8))) {
            LinkedList<Student> students = new LinkedList<Student>();
            writeData(students, bfw, bfr, f2);

        } catch (Exception e) {
            System.out.println("Error..");
        }


    }

}