package com.sms.main;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import com.sms.config.AppConfig;
import com.sms.model.Student;
import com.sms.service.StudentService;
import com.sms.service.FeeService;

import java.util.Scanner;

public class MainApp {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
        StudentService studentService = context.getBean(StudentService.class);
        FeeService feeService = context.getBean(FeeService.class);

        Scanner sc = new Scanner(System.in);
        while (true) {
            System.out.println("\n=== Student Management Menu ===");
            System.out.println("1. Add Student\n2. View Students\n3. Pay Fee\n4. Refund Fee\n5. Exit");
            System.out.print("Enter choice: ");
            int ch = sc.nextInt();

            switch (ch) {
                case 1:
                    System.out.print("Enter Name: ");
                    String name = sc.next();
                    Student s = new Student();
                    s.setName(name);
                    s.setBalance(1000);
                    studentService.addStudent(s);
                    System.out.println("✅ Student added!");
                    break;

                case 2:
                    studentService.viewAll().forEach(st -> 
                        System.out.println(st.getStudentId() + " - " + st.getName() + " - Balance: " + st.getBalance()));
                    break;

                case 3:
                    System.out.print("Enter Student ID: ");
                    int id = sc.nextInt();
                    System.out.print("Enter Amount: ");
                    double amt = sc.nextDouble();
                    feeService.payFee(id, amt);
                    break;

                case 4:
                    System.out.print("Enter Student ID: ");
                    int sid = sc.nextInt();
                    System.out.print("Enter Refund Amount: ");
                    double ref = sc.nextDouble();
                    feeService.refundFee(sid, ref);
                    break;

                case 5:
                    context.close();
                    System.exit(0);
            }
        }
    }
}
