import java.util.ArrayList;
import java.util.Scanner;

class Student {
    // Encapsulation: private data members
    private int rollNo;
    private String name;
    private ArrayList<Integer> marks;

    // Constructor
    Student(int rollNo, String name, ArrayList<Integer> marks) {
        this.rollNo = rollNo;
        this.name = name;
        this.marks = new ArrayList<>(marks);
    }

    // Getter methods
    public int getRollNo() {
        return rollNo;
    }

    public String getName() {
        return name;
    }

    public ArrayList<Integer> getMarks() {
        return new ArrayList<>(marks);
    }

    // Calculate total marks
    public int calculateTotal() {
        int total = 0;
        for (int mark : marks) {
            total += mark;
        }
        return total;
    }

    // Calculate percentage
    public double calculatePercentage() {
        return calculateTotal() / (double) marks.size();
    }

    // Check pass or fail
    public String getResult() {
        for (int mark : marks) {
            if (mark < 35) {
                return "FAIL";
            }
        }
        return "PASS";
    }

    // Calculate grade
    public String getGrade() {
        if (getResult().equals("FAIL")) {
            return "F";
        }

        double percentage = calculatePercentage();

        if (percentage >= 90) return "A+";
        else if (percentage >= 80) return "A";
        else if (percentage >= 70) return "B";
        else if (percentage >= 60) return "C";
        else if (percentage >= 50) return "D";
        else return "E";
    }

    // Display mark statement
    public void displayResult() {
        System.out.println("\n------ MARK STATEMENT ------");
        System.out.println("Roll Number : " + rollNo);
        System.out.println("Student Name: " + name);
        System.out.println("Subject Marks: " + marks);
        System.out.println("Total Marks : " + calculateTotal()
                + " / " + (marks.size() * 100));
        System.out.printf("Percentage  : %.2f%%\n",
                calculatePercentage());
        System.out.println("Grade       : " + getGrade());
        System.out.println("Result      : " + getResult());
        System.out.println("----------------------------");
    }
}

public class ExaminationResultSystem {

    // Exception handling for invalid integer input
    static int readInt(Scanner sc, String message) {
        while (true) {
            try {
                System.out.print(message);
                int value = Integer.parseInt(sc.nextLine());

                if (value < 0) {
                    System.out.println("Enter a positive number.");
                    continue;
                }

                return value;
            } catch (NumberFormatException e) {
                System.out.println("Invalid input! Enter a number.");
            }
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // Collections: store multiple student objects
        ArrayList<Student> students = new ArrayList<>();

        int choice;

        do {
            System.out.println("\n=== EXAMINATION RESULT SYSTEM ===");
            System.out.println("1. Add Student");
            System.out.println("2. Display All Results");
            System.out.println("3. Exit");

            choice = readInt(sc, "Enter your choice: ");

            switch (choice) {
                case 1:
                    int rollNo = readInt(sc, "Enter roll number: ");

                    System.out.print("Enter student name: ");
                    String name = sc.nextLine();

                    int n = readInt(sc,
                            "Enter number of subjects: ");

                    if (n == 0) {
                        System.out.println(
                                "Number of subjects must be greater than zero.");
                        break;
                    }

                    ArrayList<Integer> marks = new ArrayList<>();

                    for (int i = 1; i <= n; i++) {
                        int mark;

                        while (true) {
                            mark = readInt(sc,
                                    "Enter marks for subject " + i + ": ");

                            if (mark <= 100) {
                                break;
                            }

                            System.out.println(
                                    "Marks must be between 0 and 100.");
                        }

                        marks.add(mark);
                    }

                    Student s = new Student(rollNo, name, marks);
                    students.add(s);

                    System.out.println(
                            "Student details added successfully!");
                    break;

                case 2:
                    if (students.isEmpty()) {
                        System.out.println("No student records found.");
                    } else {
                        for (Student student : students) {
                            student.displayResult();
                        }
                    }
                    break;

                case 3:
                    System.out.println("Exiting the system...");
                    break;

                default:
                    System.out.println("Invalid choice! Try again.");
            }

        } while (choice != 3);

        sc.close();
    }
}