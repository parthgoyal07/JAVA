import java.util.InputMismatchException;
import java.util.Scanner;

public class StudentResultManagementSystem {

    private static int MAX_STUDENTS = 100;
    private Student[] Students = new Student[MAX_STUDENTS];
    private int Count = 0;
    Scanner Input = new Scanner(System.in);
    private static final int PASS_MARK = 33;

    public void AddStudent() throws InvalidMarksException {
        System.out.print("Enter Roll Number: ");
        int Roll = Input.nextInt();
        Input.nextLine();

        if (FindIndexByRoll(Roll) != -1) {
            System.out.println("Error: Student with roll " + Roll + " already exists. Returning to main menu...");
            return;
        }

        System.out.print("Enter Student Name: ");
        String Name = Input.nextLine();

        int[] Marks = new int[3];
        for (int i = 0; i < 3; i++) {
            System.out.print("Enter marks for subject " + (i + 1) + ": ");
            Marks[i] = Input.nextInt();
        }
        Input.nextLine();

        Student S = new Student(Roll, Name, Marks);
        S.ValidateMarks();
        if (Count < MAX_STUDENTS) {
            Students[Count++] = S;
            System.out.println("Student added successfully. Returning to main menu...");
        }
        else {
            System.out.println("Storage full. Cannot add more Students.");
        }
    }

    public void ShowStudentDetails() {
        System.out.print("Enter Roll Number to search: ");
        int Roll = Input.nextInt();
        Input.nextLine();

        int Idx = FindIndexByRoll(Roll);
        if (Idx == -1) {
            System.out.println("Student with roll " + Roll + " not found. Returning to main menu...");
        }
        else {
            Students[Idx].DisplayResult();
            System.out.println("Search completed.");
        }
    }

    private int FindIndexByRoll(int Roll) {
        for (int i = 0; i < Count; i++) {
            if (Students[i] != null && Students[i].GetRoll() == Roll) {
                return i;
            }
        }
        return -1;
    }

    public void Menu() {
        boolean Running = true;
        try {
            while (Running) {
                System.out.println();
                System.out.println("===== Student Result Management System =====");
                System.out.println("1. Add Student");
                System.out.println("2. Show Student Details");
                System.out.println("3. Exit");
                System.out.print("Enter your choice: ");

                int Choice;
                try {
                    Choice = Input.nextInt();
                    Input.nextLine();
                }
                catch (InputMismatchException IME) {
                    System.out.println("Invalid input. Please enter a number (1-3). Returning to main menu...");
                    Input.nextLine();
                    continue;
                }

                switch (Choice) {
                    case 1:
                        try {
                            AddStudent();
                        }
                        catch (InvalidMarksException IME) {
                            System.out.println("Error: " + IME.getMessage() + " Returning to main menu...");
                        }
                        catch (InputMismatchException IME2) {
                            System.out.println("Input error: enter numeric marks only. Returning to main menu...");
                            Input.nextLine();
                        }
                        break;
                    case 2:
                        try {
                            ShowStudentDetails();
                        }
                        catch (InputMismatchException IME) {
                            System.out.println("Input error: roll number must be numeric. Returning to main menu...");
                            Input.nextLine();
                        }
                        break;
                    case 3:
                        System.out.println("Exiting program. Thank you!");
                        Running = false;
                        break;
                    default:
                        System.out.println("Invalid choice. Please select (1-3)!");
                }
            }
        }
        finally {
            if (Input != null) {
                Input.close();
            }
        }
    }

    public static void main(String[] args) {
        StudentResultManagementSystem System = new StudentResultManagementSystem();
        System.Menu();
    }

    static class Student {
        private int Roll;
        private String Name;
        private int[] Marks;

        public Student(int Roll, String Name, int[] Marks) {
            this.Roll = Roll;
            this.Name = Name;
            this.Marks = Marks;
        }

        public int GetRoll() {
            return Roll;
        }

        public void ValidateMarks() throws InvalidMarksException {
            if (Marks == null || Marks.length != 3) {
                throw new InvalidMarksException("Marks are missing or incomplete for roll " + Roll);
            }
            for (int i = 0; i < Marks.length; i++) {
                if (Marks[i] < 0 || Marks[i] > 100) {
                    throw new InvalidMarksException("Invalid marks for subject " + (i + 1) + ": " + Marks[i]);
                }
            }
        }

        public double CalculateAverage() {
            double Sum = 0;
            for (int M : Marks) {
                Sum += M;
            }
            return Sum / Marks.length;
        }

        public void DisplayResult() {
            System.out.println("Roll Number: " + Roll);
            System.out.println("Student Name: " + Name);
            System.out.print("Marks: ");
            for (int M : Marks) {
                System.out.print(M + " ");
            }
            System.out.println();
            double AVG = CalculateAverage();
            System.out.println("Average: " + AVG);

            boolean Pass = true;
            for (int M : Marks) {
                if (M < PASS_MARK) {
                    Pass = false;
                    break;
                }
            }
            System.out.println("Result: " + (Pass ? "Pass" : "Fail"));
        }
    }

    static class InvalidMarksException extends Exception {
        public InvalidMarksException(String Message) {
            super(Message);
        }
    }
}