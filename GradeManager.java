import java.io.*;
import java.util.*;

public class GradeManager {
    private ArrayList<Student> students;

    public GradeManager() {
        students = new ArrayList<>();
        loadData();
    }

    private Student findStudentById(int id) {
        for (Student s : students) {
            if (s.getId() == id) return s;
        }
        return null;
    }

    public void addStudent(String name, int id) {
        if (findStudentById(id) != null) {
            System.out.println("Student ID already exists.");
            return;
        }
        students.add(new Student(name, id));
        System.out.println("Student added: " + name + " (ID: " + id + ")");
        saveData();
    }

    public void removeStudent(int id) {
        Student s = findStudentById(id);
        if (s == null) {
            System.out.println("Student not found.");
            return;
        }
        students.remove(s);
        System.out.println("Student removed: " + s.getName());
        saveData();
    }

    public void addGrade(int id, double grade) {
        Student s = findStudentById(id);
        if (s == null) {
            System.out.println("Student not found.");
            return;
        }
        s.addGrade(grade);
        saveData();
    }

    public void displayAllStudents() {
        if (students.isEmpty()) {
            System.out.println("No students in the system.");
            return;
        }
        System.out.println("\n========== ALL STUDENTS ==========");
        for (Student s : students) {
            s.display();
        }
    }

    public void displayStudentGrades(int id) {
        Student s = findStudentById(id);
        if (s == null) {
            System.out.println("Student not found.");
            return;
        }
        s.display();
        s.displayGrades();
    }

    public void displayClassStatistics() {
        if (students.isEmpty()) {
            System.out.println("No students.");
            return;
        }
        
        double classTotal = 0;
        int studentsWithGrades = 0;
        double highest = 0, lowest = 100;
        int a = 0, b = 0, c = 0, d = 0, f = 0;
        
        for (Student s : students) {
            if (s.getGradeCount() > 0) {
                double avg = s.getAverage();
                classTotal += avg;
                studentsWithGrades++;
                if (s.getHighest() > highest) highest = s.getHighest();
                if (s.getLowest() < lowest) lowest = s.getLowest();
                if (avg >= 90) a++;
                else if (avg >= 80) b++;
                else if (avg >= 70) c++;
                else if (avg >= 60) d++;
                else f++;
            }
        }
        
        System.out.println("\n=== CLASS STATISTICS ===");
        System.out.println("Total Students: " + students.size());
        System.out.println("Students with Grades: " + studentsWithGrades);
        if (studentsWithGrades > 0) {
            System.out.printf("Class Average: %.2f\n", classTotal / studentsWithGrades);
            System.out.printf("Highest Grade: %.2f\n", highest);
            System.out.printf("Lowest Grade: %.2f\n", lowest);
            System.out.println("A: " + a + " | B: " + b + " | C: " + c + " | D: " + d + " | F: " + f);
        }
    }

    public void loadData() {
        try {
            File file = new File("students.txt");
            if (!file.exists()) return;
            Scanner scanner = new Scanner(file);
            int count = Integer.parseInt(scanner.nextLine());
            students.clear();
            for (int i = 0; i < count; i++) {
                Student s = new Student();
                s.loadFromFile(scanner);
                students.add(s);
            }
            scanner.close();
        } catch (Exception e) {
            System.out.println("Error loading data.");
        }
    }

    public void saveData() {
        try {
            PrintWriter writer = new PrintWriter("students.txt");
            writer.println(students.size());
            for (Student s : students) {
                s.saveToFile(writer);
            }
            writer.close();
        } catch (Exception e) {
            System.out.println("Error saving data.");
        }
    }
}
