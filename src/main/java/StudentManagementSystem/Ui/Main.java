package StudentManagementSystem.Ui;

import StudentManagementSystem.Model.Gender;
import StudentManagementSystem.Model.Student;
import StudentManagementSystem.Service.StudentService;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Scanner;

public class Main {

    private static final StudentService service = new StudentService();
    private static final Scanner scanner = new Scanner(System.in);
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    public static void main(String[] args) {
        System.out.println("🎓 Welcome to Student Management System!");

        while (true) {
            printMenu();
            int choice = getIntInput("Enter your choice: ");

            switch (choice) {
                case 1 -> createStudent();
                case 2 -> viewAllStudents();
                case 3 -> findStudentById();
                case 4 -> updateStudent();
                case 5 -> deleteStudent();
                case 6 -> {
                    System.out.println("Thank you for using Student Management System!");
                    service.close();
                    scanner.close();
                    return;
                }
                default -> System.out.println("Invalid choice! Please try again.");
            }
        }
    }

    private static void printMenu() {
        System.out.println("\n" + "=".repeat(50));
        System.out.println("                STUDENT MANAGEMENT SYSTEM");
        System.out.println("=".repeat(50));
        System.out.println("1. Add New Student");
        System.out.println("2. View All Students");
        System.out.println("3. Find Student by ID");
        System.out.println("4. Update Student");
        System.out.println("5. Delete Student");
        System.out.println("6. Exit");
        System.out.println("=".repeat(50));
    }

    private static void createStudent() {
        System.out.println("\n--- Add New Student ---");

        System.out.print("Enter Name: ");
        String name = scanner.nextLine();

        System.out.print("Enter Email: ");
        String email = scanner.nextLine();

        System.out.print("Enter Phone: ");
        String phone = scanner.nextLine();

        System.out.print("Enter Gender (MALE/FEMALE/OTHER): ");
        Gender gender = Gender.valueOf(scanner.nextLine().toUpperCase());

        System.out.print("Enter Date of Birth (YYYY-MM-DD): ");
        LocalDate dob = LocalDate.parse(scanner.nextLine(), formatter);

        System.out.print("Enter Admission Date (YYYY-MM-DD): ");
        LocalDate admissionDate = LocalDate.parse(scanner.nextLine(), formatter);

        Student student = new Student(name, email, phone, gender, dob, admissionDate);
        service.createStudent(student);
    }

    private static void viewAllStudents() {
        System.out.println("\n--- All Students ---");
        List<Student> students = service.getAllStudents();

        if (students.isEmpty()) {
            System.out.println("No students found!");
        } else {
            students.forEach(System.out::println);
        }
    }

    private static void findStudentById() {
        Long id = getLongInput("Enter Student ID: ");
        Student student = service.findStudent(id);

        if (student != null) {
            System.out.println("Student Found: " + student);
        } else {
            System.out.println("Student not found with ID: " + id);
        }
    }

    private static void updateStudent() {
        Long id = getLongInput("Enter Student ID to update: ");
        Student student = service.findStudent(id);

        if (student == null) {
            System.out.println("Student not found!");
            return;
        }

        System.out.println("Current Details: " + student);

        System.out.print("Enter New Name (or press Enter to keep same): ");
        String name = scanner.nextLine();
        if (!name.trim().isEmpty()) student.setName(name);

        System.out.print("Enter New Email (or press Enter to keep same): ");
        String email = scanner.nextLine();
        if (!email.trim().isEmpty()) student.setEmail(email);

        System.out.print("Enter New Phone (or press Enter to keep same): ");
        String phone = scanner.nextLine();
        if (!phone.trim().isEmpty()) student.setPhone(phone);

        service.updateStudent(student);
    }

    private static void deleteStudent() {
        Long id = getLongInput("Enter Student ID to delete: ");
        service.deleteStudent(id);
    }

    // Helper Methods
    private static int getIntInput(String message) {
        while (true) {
            try {
                System.out.print(message);
                return Integer.parseInt(scanner.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.println("Please enter a valid number!");
            }
        }
    }

    private static Long getLongInput(String message) {
        while (true) {
            try {
                System.out.print(message);
                return Long.parseLong(scanner.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.println("Please enter a valid number!");
            }
        }
    }
}