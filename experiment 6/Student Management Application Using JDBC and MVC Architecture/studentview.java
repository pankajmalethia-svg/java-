package view;

import java.util.*;
import controller.StudentController;
import model.Student;

public class StudentView {
    private Scanner sc = new Scanner(System.in);
    private StudentController controller = new StudentController();

    public void start() {
        while (true) {
            System.out.println("\n===== STUDENT MANAGEMENT SYSTEM =====");
            System.out.println("1. Add Student");
            System.out.println("2. View All Students");
            System.out.println("3. Update Student");
            System.out.println("4. Delete Student");
            System.out.println("5. Exit");
            System.out.print("Enter your choice: ");
            
            int choice = sc.nextInt();
            sc.nextLine(); // consume newline

            switch (choice) {
                case 1: addStudent(); break;
                case 2: viewStudents(); break;
                case 3: updateStudent(); break;
                case 4: deleteStudent(); break;
                case 5:
                    System.out.println("👋 Exiting... Goodbye!");
                    return;
                default:
                    System.out.println("⚠️ Invalid choice. Try again.");
            }
        }
    }

    private void addStudent() {
        System.out.print("Enter Name: ");
        String name = sc.nextLine();
        System.out.print("Enter Department: ");
        String dept = sc.nextLine();
        System.out.print("Enter Marks: ");
        double marks = sc.nextDouble();

        Student s = new Student(name, dept, marks);
        controller.addStudent(s);
    }

    private void viewStudents() {
        List<Student> list = controller.getAllStudents();
        System.out.println("\nID        Name                 Department     Marks");
        System.out.println("-----------------------------------------------------");
        for (Student s : list)
            System.out.println(s);
    }

    private void updateStudent() {
        System.out.print("Enter Student ID to update: ");
        int id = sc.nextInt(); sc.nextLine();
        System.out.print("Enter New Name: ");
        String name = sc.nextLine();
        System.out.print("Enter New Department: ");
        String dept = sc.nextLine();
        System.out.print("Enter New Marks: ");
        double marks = sc.nextDouble();

        Student s = new Student(id, name, dept, marks);
        controller.updateStudent(s);
    }

    private void deleteStudent() {
        System.out.print("Enter Student ID to delete: ");
        int id = sc.nextInt();
        controller.deleteStudent(id);
    }
}
