package tutorial.learn.core._2_static_keyword;

class Student {

    // static variable `studentCount` is common to class
    // while `studentName` is unique to each Student object
    public static int studentCount;
    public String studentName;

    public Student(String studentName) {
        this.studentName = studentName;
        studentCount++;
    }
}

public class _1_StaticVariable {
    public static void main(String[] args) {
        Student s1 = new Student("s1");
        Student s2 = new Student("s2");
        System.out.println(Student.studentCount);
    }
}
