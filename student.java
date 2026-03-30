import java.io.*;
import java.util.*;

public class Student {
    private String name;
    private int id;
    private ArrayList<Double> grades;

    public Student() {
        name = "";
        id = 0;
        grades = new ArrayList<>();
    }

    public Student(String name, int id) {
        this.name = name;
        this.id = id;
        grades = new ArrayList<>();
    }

    public String getName() { return name; }
    public int getId() { return id; }
    public ArrayList<Double> getGrades() { return grades; }
    public int getGradeCount() { return grades.size(); }

    public void setName(String name) { this.name = name; }
    public void setId(int id) { this.id = id; }

    public void addGrade(double grade) {
        if (grade >= 0 && grade <= 100) {
            grades.add(grade);
            System.out.println("Grade added successfully!");
        } else {
            System.out.println("Invalid grade.");
        }
    }

    public double getAverage() {
        if (grades.isEmpty()) return 0.0;
        double sum = 0.0;
        for (double grade : grades) sum += grade;
        return sum / grades.size();
    }

    public double getHighest() {
        if (grades.isEmpty()) return 0.0;
        double highest = grades.get(0);
        for (double grade : grades) if (grade > highest) highest = grade;
        return highest;
    }

    public double getLowest() {
        if (grades.isEmpty()) return 0.0;
        double lowest = grades.get(0);
        for (double grade : grades) if (grade < lowest) lowest = grade;
        return lowest;
    }

    public void display() {
        System.out.println("ID: " + id + " | Name: " + name + " | Grades: " + grades.size());
        if (!grades.isEmpty()) {
            System.out.printf("  Average: %.2f | Highest: %.2f | Lowest: %.2f\n", 
                getAverage(), getHighest(), getLowest());
        }
    }

    public void displayGrades() {
        if (grades.isEmpty()) {
            System.out.println("No grades recorded.");
            return;
        }
        System.out.print("Grades: ");
        for (int i = 0; i < grades.size(); i++) {
            System.out.print(grades.get(i));
            if (i < grades.size() - 1) System.out.print(", ");
        }
        System.out.println();
    }

    public void saveToFile(PrintWriter out) {
        out.println(id);
        out.println(name);
        out.println(grades.size());
        for (double grade : grades) out.println(grade);
    }

    public void loadFromFile(Scanner in) {
        id = Integer.parseInt(in.nextLine());
        name = in.nextLine();
        int gradeCount = Integer.parseInt(in.nextLine());
        grades.clear();
        for (int i = 0; i < gradeCount; i++) {
            grades.add(Double.parseDouble(in.nextLine()));
        }
    }
}
