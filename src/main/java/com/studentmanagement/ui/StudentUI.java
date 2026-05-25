package com.studentmanagement.ui;

import com.studentmanagement.model.Address;
import com.studentmanagement.model.Department;
import com.studentmanagement.model.Gender;
import com.studentmanagement.model.Student;
import com.studentmanagement.service.StudentService;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Scanner;


public class StudentUI {

    private final StudentService service;
    private final Scanner scanner;
    private final DateTimeFormatter formatter;

    public StudentUI() {
        this.service = new StudentService();
        this.scanner = new Scanner(System.in);
        this.formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    }

    public void start() {
        System.out.println(" Welcome to Student Management System! ");

        while(true) {
            printMenu();
            int choice = getIntInput("Enter your choice: ");

            switch (choice) {
                case 1 -> createStudent();
                case 2 -> viewAllStudents();
                case 3 -> findStudentById();
                case 4 -> updateStudent();
                case 5 -> deleteStudent();
                case 6 -> {
                    System.out.println("Thank you for using Student Management System! ");
                        service.close();
                        scanner.close();
                        return;
                }

                default -> System.out.println("Invalid option! Please try again.");
            }
        }
    }

    private void printMenu() {
        System.out.println("\n" + "=".repeat(60));
        System.out.println("     STUDENT MANAGEMENT SYSTEM");
        System.out.println("=".repeat(60));
        System.out.println("1. Add New Student");
        System.out.println("2. View All Students");
        System.out.println("3. Find Student by ID");
        System.out.println("4. Update Student");
        System.out.println("5. Delete Student");
        System.out.println("6. Exit");
        System.out.println("=".repeat(60));
    }

    private void createStudent() {
        System.out.println("\n--- Add New Student ---");

        System.out.print("Enter Name: ");
        String name = scanner.nextLine();

        System.out.print("Enter Email: ");
        String email = scanner.nextLine();

        System.out.print("Enter Phone: ");
        String phone = scanner.nextLine();

        System.out.print("Enter Gender (MALE/FEMALE/OTHER): ");
        Gender gender = Gender.valueOf(scanner.nextLine().toUpperCase().trim());

        System.out.print("Enter Department (e.g. COMPUTER_SCIENCE, ELECTRONICS): ");
        Department department = Department.valueOf(scanner.nextLine().toUpperCase().trim());

        System.out.print("Enter Date of Birth (YYYY-MM-DD): ");
        LocalDate dob = LocalDate.parse(scanner.nextLine().trim(), formatter);

        System.out.print("Enter Admission Date (YYYY-MM-DD): ");
        LocalDate admissionDate = LocalDate.parse(scanner.nextLine().trim(), formatter);

        // address
        System.out.println("Enter Address:");
        System.out.print("  Street: ");
        String street = scanner.nextLine();
        System.out.print("  City: ");
        String city = scanner.nextLine();
        System.out.print("  State: ");
        String state = scanner.nextLine();
        System.out.print("  Pincode: ");
        String pincode = scanner.nextLine();

        Address address = new Address(street, city, state, pincode);

        Student student = new Student(name, email, phone, gender, department, dob, admissionDate, address);
        service.createStudent(student);
    }

    private void viewAllStudents() {
        System.out.println("\n--- All Students ---");
        List<Student> students = service.getAllStudents();
        if (students.isEmpty()) {
            System.out.println("No students found.");
        } else {
            students.forEach(System.out::println);
        }
    }

    private void findStudentById() {
        Long id = getLongInput("Enter Student ID: ");
        Student student = service.findStudent(id);
        System.out.println(student != null ? "Found: " + student : "Student not found!");
    }

    private void updateStudent() {
        Long id = getLongInput("Enter Student ID to update: ");
        Student student = service.findStudent(id);

        if (student == null) {
            System.out.println("Student not found!");
            return;
        }

        System.out.println("Current Details: " + student);

        System.out.print("New Name (Enter to skip): ");
        String name = scanner.nextLine().trim();
        if (!name.isEmpty()) student.setName(name);

        System.out.print("New Email (Enter to skip): ");
        String email = scanner.nextLine().trim();
        if (!email.isEmpty()) student.setEmail(email);

        System.out.print("New Phone (Enter to skip): ");
        String phone = scanner.nextLine().trim();
        if (!phone.isEmpty()) student.setPhone(phone);

        service.updateStudent(student);
    }

    private void deleteStudent() {
        Long id = getLongInput("Enter Student ID to delete: ");
        service.deleteStudent(id);
    }

    private int getIntInput(String message) {
        while (true) {
            try {
                System.out.print(message);
                return Integer.parseInt(scanner.nextLine().trim());
            } catch (Exception e) {
                System.out.println("Please enter a valid number!");
            }
        }
    }

    private Long getLongInput(String message) {
        while (true) {
            try {
                System.out.print(message);
                return Long.parseLong(scanner.nextLine().trim());
            } catch (Exception e) {
                System.out.println("Please enter a valid number!");
            }
        }
    }
}
