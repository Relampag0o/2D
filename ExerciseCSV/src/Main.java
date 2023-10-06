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
                maxValue = entry.getValue();
                mostFrecuentNumber = entry.getKey();
            }
        }
        return mostFrecuentNumber;
    }


    public static double[] calculatePassedAndFailedStudents(LinkedList<Student> students) {
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

        percents[0] = percents[0] * 100 / students.size();
        percents[1] = percents[1] * 100 / students.size();
        return percents;

    }

    public static String listSpecialties(LinkedList<Student> students) {
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

        return s;

    }

    public static String calitifactionsBySpecialty(LinkedList<Student> students, String speciality) {
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
        return speciality + ": " + '\n' + " Average: " + califications[0] / amountOfStudentsBySpeciality +
                '\n' + " Students that passed: " + Math.round(califications[1] * 100 / amountOfStudentsBySpeciality) + "% " + '\n' + " Students that failed: " +
                Math.round(califications[2] * 100 / amountOfStudentsBySpeciality) + "%";
    }

    public static int countStudentsByOccurrence(LinkedList<Student> students, String occurrence) {
        int counter = 0;
        for (Student s : students) {
            if (s.getName().contains(occurrence)) {
                counter++;
            }
        }
        return counter;
    }

    public static String searchNamesWithTilde(LinkedList<Student> students,File f,BufferedWriter bfw ) {
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
            System.out.println("Name: " + w);
            try {
                bfw.write("Name: " + w + '\n');
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

        return "";
    }

    public static void main(String[] args) {
        String pathsource = "C:\\Users\\Josem\\Desktop\\alumnos.csv";
        String pathdest = "C:\\Users\\Josem\\Desktop\\output.txt";
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
            System.out.println("Median:" + "" + (students.get(students.size() / 2).getCalification()));
            bfw.newLine();
            bfw.write("Median:" + "" + (students.get(students.size() / 2).getCalification()));
            bfw.newLine();
            System.out.println("Students that passed: " + Math.round(calculatePassedAndFailedStudents(students)[0]) + "%" + '\n' + "Students who failled: " + Math.round(calculatePassedAndFailedStudents(students)[1]) + "%");
            bfw.write("Students that passed: " + Math.round(calculatePassedAndFailedStudents(students)[0]) + "%" + '\n' + "Students who failled: " + Math.round(calculatePassedAndFailedStudents(students)[1]) + "%");
            bfw.newLine();
            System.out.println(listSpecialties(students));
            bfw.write("List of specialites: " + listSpecialties(students));
            bfw.newLine();
            System.out.println(calitifactionsBySpecialty(students, "ciencias"));
            System.out.println(calitifactionsBySpecialty(students, "letras"));
            System.out.println(calitifactionsBySpecialty(students, "artes"));
            bfw.write(calitifactionsBySpecialty(students, "ciencias") + '\n');
            bfw.write(calitifactionsBySpecialty(students, "letras") + '\n');
            bfw.write(calitifactionsBySpecialty(students, "artes") + '\n');
            System.out.println(countStudentsByOccurrence(students, "j"));
            searchNamesWithTilde(students,f2,bfw);

        } catch (Exception e) {
            System.out.println("Error..");
        }
    }

}