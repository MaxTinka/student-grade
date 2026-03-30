public class Main {
    public static void main(String[] args) {
        GradeManager manager = new GradeManager();
        
        System.out.println("=== STUDENT GRADE MANAGER ===\n");
        
        // Add sample students
        manager.addStudent("John Smith", 1001);
        manager.addStudent("Jane Doe", 1002);
        manager.addStudent("Bob Johnson", 1003);
        
        // Add sample grades
        manager.addGrade(1001, 85.5);
        manager.addGrade(1001, 90.0);
        manager.addGrade(1002, 78.0);
        manager.addGrade(1002, 88.5);
        manager.addGrade(1003, 92.0);
        manager.addGrade(1003, 87.5);
        manager.addGrade(1003, 95.0);
        
        // Display results
        manager.displayAllStudents();
        manager.displayClassStatistics();
        
        System.out.println("\n=== Program completed ===");
    }
}
