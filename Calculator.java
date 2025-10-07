import java.util.Scanner;

public class Calculator {
    // ADD METHODS
    public int Add(int a, int b) {
        return a + b;
    }
    public double Add(double a, double b) {
        return a + b;
    }
    public int Add(int a, int b, int c) {
        return a + b + c;
    }

    // SUBTRACT METHOD
    public int Subtract(int a, int b) {
        return a - b;
    }

    // MULTIPLY METHOD
    public double Multiply(double a, double b) {
        return a * b;
    }

    // DIVIDE METHOD
    public double Divide(int a, int b) {
        return (double) a / b;
    }

    // MAIN METHOD
    public static void main(String[] args) {
        Scanner Input = new Scanner(System.in);
        Calculator CalC = new Calculator();

        int CHOICE;
        do {
            System.out.println("\n===== SIMPLE CALCULATOR =====");
            System.out.println();
            System.out.println("1. ADDITION");
            System.out.println("2. SUBTRACTION");
            System.out.println("3. MULTIPLICATION");
            System.out.println("4. DIVISION");
            System.out.println("5. EXIT");
            System.out.print("PLEASE ENTER YOUR CHOICE: ");
            CHOICE = Input.nextInt();

            switch (CHOICE) {
                case 1:
                    System.out.println("\n--- ADDITION ---");
                    System.out.println("1. ADD TWO INTEGERS");
                    System.out.println("2. ADD TWO DOUBLES");
                    System.out.println("3. ADD THREE INTEGERS");
                    System.out.print("PLEASE SELECT: ");

                    int AddChoice = Input.nextInt();
                    if (AddChoice == 1) {
                        System.out.print("ENTER FIRST NUMBER: ");
                        int A = Input.nextInt();
                        System.out.print("ENTER SECOND NUMBER: ");
                        int B = Input.nextInt();
                        System.out.println("RESULT: " + CalC.Add(A,B));
                    }
                    else if (AddChoice == 2) {
                        System.out.print("ENTER FIRST NUMBER: ");
                        double A = Input.nextDouble();
                        System.out.print("ENTER SECOND NUMBER: ");
                        double B = Input.nextDouble();
                        System.out.println("RESULT: " + CalC.Add(A,B));
                    }
                    else if (AddChoice == 3) {
                        System.out.print("ENTER FIRST NUMBER: ");
                        int A = Input.nextInt();
                        System.out.print("ENTER SECOND NUMBER: ");
                        int B = Input.nextInt();
                        System.out.print("ENTER THIRD NUMBER: ");
                        int C = Input.nextInt();
                        System.out.println("RESULT: " + CalC.Add(A,B,C));
                    }
                    else {
                        System.out.println("INVALID CHOICE FOR ADDITION!");
                    }
                    break;

                case 2:
                    System.out.print("ENTER FIRST NUMBER: ");
                    int S1 = Input.nextInt();
                    System.out.print("ENTER SECOND NUMBER: ");
                    int S2 = Input.nextInt();
                    System.out.println("RESULT: " + CalC.Subtract(S1,S2));
                    break;

                case 3:
                    System.out.print("ENTER FIRST NUMBER: ");
                    double M1 = Input.nextDouble();
                    System.out.print("ENTER SECOND NUMBER: ");
                    double M2 = Input.nextDouble();
                    System.out.println("RESULT: " + CalC.Multiply(M1,M2));
                    break;

                case 4:
                    System.out.print("ENTER DIVIDEND: ");
                    int D1 = Input.nextInt();
                    System.out.print("ENTER DIVISOR: ");
                    int D2 = Input.nextInt();
                    if (D2==0) {
                        System.out.println("INVALID! CANNOT DIVIDE BY 0!");
                    }
                    else {
                        System.out.println("RESULT: " + CalC.Divide(D1, D2));
                    }
                    break;

                case 5:
                    System.out.println("EXITING.......THANK YOU!");
                    break;

                default:
                    System.out.println("INVALID CHOICE! PLEASE TRY AGAIN!");
            }
        }
        while (CHOICE != 5);
        Input.close();
    }
}