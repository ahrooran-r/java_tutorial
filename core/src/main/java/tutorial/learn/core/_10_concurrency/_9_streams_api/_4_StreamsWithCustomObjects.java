package tutorial.learn.core._10_concurrency._9_streams_api;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class _4_StreamsWithCustomObjects {

    public static void main(String[] args) {

        List<Student> students = Arrays.asList(
                new Student(1, true),
                new Student(2, false),
                new Student(3, true),
                new Student(4, true),
                new Student(5, false),
                new Student(6, false),
                new Student(7, true),
                new Student(8, false),
                new Student(9, true),
                new Student(10, false));

        // map is used to convert one object to another
        // map returns a new stream -> which has only the IDs of the students
        // we can use map to extract certain properties of custom objects
        // so stream() is the original stream, and map() returns a new stream with IDs only
        students.stream().map(Student::getId).forEach(System.out::println);

        // we can have additional operations
        // filters local students and counts their number
        // map(), filter() -> intermediate operation
        // count() -> terminal operation
        long localStudents = students
                .stream()
                .map(Student::isLocal)
                .filter(local -> local)
                .count();
        System.out.println("Local students: " + localStudents);

        // we can construct single string on local students
        localStudents = students.stream().filter(Student::isLocal).map(Student::getId).count();
        System.out.println("Local students: " + localStudents);
        // above two results are same
        // but the way of computing is different

        String output = students
                .stream()
                .filter(Student::isLocal)
                .map(Student::getId)
                // I get the if first, but joining() cannot work with integers
                // So I again map it into string
                .map(String::valueOf)
                // then do the joining
                .collect(Collectors.joining(" "));
        System.out.println(output);
    }

    private static void myFunc(int i) {
        System.out.println(i);
    }
}

class Student {

    private final int id;
    private boolean local;

    public Student(int id, boolean local) {
        this.id = id;
        this.local = local;
    }


    public int getId() {
        return id;
    }

    public boolean isLocal() {
        return local;
    }

    public void setLocal(boolean local) {
        this.local = local;
    }
}
