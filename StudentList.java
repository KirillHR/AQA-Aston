import java.util.HashSet;
import java.util.Set;

public class StudentList {
    public static void main(String[] args) {
        Set<Student> students = new HashSet<>();

        students.add(new Student("Василий Пупкин", "52", 2, 5));
        students.add(new Student("Иван Иванов", "31", 3, 4));
        students.add(new Student("Александра Петрова", "10", 1, 5));

        removeAndPromoteStudents(students);

        printStudents(students, 2);
    }

    public static void removeAndPromoteStudents(Set<Student> students) {
        students.removeIf(student -> student.grades < 3);
        for (Student student : students) {
            if (student.grades >= 3) {
                student.course++;
            }
        }
    }

    public static void printStudents(Set<Student> students, int course) {
        System.out.println("Студенты на курсе " + course + ":");
        for (Student student : students) {
            if (student.course == course) {
                System.out.println(student.name);
            }
        }
    }
}

class Student {
    String name;
    String group;
    int course;
    int grades;

    public Student(String name, String group, int course, int grades) {
        this.name = name;
        this.group = group;
        this.course = course;
        this.grades = grades;
    }
}
