import java.util.ArrayList;
import java.util.Scanner;

class Student {
    int rollNo;
    String name;
    String course;
    double feesPaid;
    double totalFees;

    Student(int rollNo, String name, String course, double totalFees) {
        this.rollNo = rollNo;
        this.name = name;
        this.course = course;
        this.totalFees = totalFees;
        this.feesPaid = 0;
    }

    void payFees(double amount) {
        if (amount > 0 && (feesPaid + amount) <= totalFees) {
            feesPaid += amount;
            System.out.println("Payment Successful! Total Paid: " + feesPaid);
        } else {
            System.out.println("Invalid Amount or Fees already paid fully!");
        }
    }

    void displayDetails() {
        System.out.println("Roll No: " + rollNo);
        System.out.println("Name: " + name);
        System.out.println("Course: " + course);
        System.out.println("Total Fees: " + totalFees);
        System.out.println("Fees Paid: " + feesPaid);
        System.out.println("Remaining Fees: " + (totalFees - feesPaid));
        System.out.println("----------------------------------");
    }
}

public class StudentManagementSystem {
    static ArrayList<Student> students = new ArrayList<>();
    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        int choice;
        do {
            System.out.println("\n===== Student Management System =====");
            System.out.println("1. Add New Student");
            System.out.println("2. View All Students");
            System.out.println("3. Search Student by Roll No");
            System.out.println("4. Pay Fees");
            System.out.println("5. Remove Student");
            System.out.println("6. Exit");
            System.out.print("Enter your choice: ");
            choice = sc.nextInt();

            switch (choice) {
                case 1:
                    addStudent();
                    break;
                case 2:
                    viewAllStudents();
                    break;
                case 3:
                    searchStudent();
                    break;
                case 4:
                    payFees();
                    break;
                case 5:
                    removeStudent();
                    break;
                case 6:
                    System.out.println("Exiting... Thank you!");
                    break;
                default:
                    System.out.println("Invalid choice! Try again.");
            }
        } while (choice != 6);
    }

    static void addStudent() {
        System.out.print("Enter Roll No: ");
        int roll = sc.nextInt();
        sc.nextLine(); // buffer clear

        System.out.print("Enter Name: ");
        String name = sc.nextLine();

        System.out.print("Enter Course Name: ");
        String course = sc.nextLine();

        System.out.print("Enter Total Fees: ");
        double total = sc.nextDouble();

        students.add(new Student(roll, name, course, total));
        System.out.println("Student Added Successfully!");
    }

    static void viewAllStudents() {
        if (students.isEmpty()) {
            System.out.println("No students found!");
        } else {
            System.out.println("\n---- Student List ----");
            for (Student s : students) {
                s.displayDetails();
            }
        }
    }

    static void searchStudent() {
        System.out.print("Enter Roll No to search: ");
        int roll = sc.nextInt();
        boolean found = false;

        for (Student s : students) {
            if (s.rollNo == roll) {
                s.displayDetails();
                found = true;
                break;
            }
        }

        if (!found)
            System.out.println("Student not found!");
    }

    static void payFees() {
        System.out.print("Enter Roll No for fee payment: ");
        int roll = sc.nextInt();
        boolean found = false;

        for (Student s : students) {
            if (s.rollNo == roll) {
                System.out.print("Enter amount to pay: ");
                double amt = sc.nextDouble();
                s.payFees(amt);
                found = true;
                break;
            }
        }

        if (!found)
            System.out.println("Student not found!");
    }

    static void removeStudent() {
        System.out.print("Enter Roll No to remove: ");
        int roll = sc.nextInt();
        boolean removed = students.removeIf(s -> s.rollNo == roll);

        if (removed)
            System.out.println("Student removed successfully!");
        else
            System.out.println("Student not found!");
    }
}
