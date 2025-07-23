package Lambda;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Lambda_20_SortListOfObjects {

    public interface InnerSortListOfObjects {
        void run(List<Student> students);
    }

    static class Student {
        private int age;
        private String name;
        public Student(String n, int a){
            age = a; name = n;
        }
        public String getName() { return this.name;}
        public int getAge() { return this.age;}
    }

    public static void main(String[] args) {

        ArrayList<Student> student_list = new ArrayList<Student>();
        
        student_list.add(new Student("Adriana Jamie", 15));
        student_list.add(new Student("Felix Uisdean", 15));
        student_list.add(new Student("Conceicao Palmira", 14));
        student_list.add(new Student("Jair Camila", 14));
        student_list.add(new Student("Micaela Rosana", 15));
        
        System.out.println("Student details:");
        for (Student Student: student_list) {
            System.out.println(Student.getName() + " - " + Student.getAge());
        }
        student_list.sort(Comparator.comparing(Student::getName));
        System.out.println("\nSorted list based on Student Name:");
        for (Student Student: student_list) {
          System.out.println(Student.getName() + " - " + Student.getAge());
        }    }
}
