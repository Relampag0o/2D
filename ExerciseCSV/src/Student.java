public class Student implements Comparable<Student> {
    private String name;
    private String speciality;

    private int calification;


    public Student(String name, String speciality, int calification) {
        this.name = name;
        this.speciality = speciality;
        this.calification = calification;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSpeciality() {
        return speciality;
    }

    public void setSpeciality(String speciality) {
        this.speciality = speciality;
    }

    public int getCalification() {
        return calification;
    }

    public void setCalification(int calification) {
        this.calification = calification;
    }


    @Override
    public int compareTo(Student otherStudent) {

        return Integer.compare(this.calification, otherStudent.calification);
    }
}
