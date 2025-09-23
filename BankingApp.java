import java.util.Scanner;

class Account {
    private int AccountNumber;
    private String AccountHolderName;
    private double Balance;
    private String Email;
    private String PhoneNumber;

    private static int BaseAccountNumber = 700000;

    Account(String HolderName, double Deposit, String Email, String PhoneNumber) {
        this.AccountNumber = ++BaseAccountNumber;
        this.AccountHolderName = HolderName;
        this.Balance = Deposit;
        this.Email = Email;
        this.PhoneNumber = PhoneNumber;
        System.out.println("Account created successfully for: " + AccountHolderName + ", with Account Number: " + this.AccountNumber);
    }

    public int GetAccountNumber() {
        return AccountNumber;
    }

    public boolean Deposit(double Amount) {
        if (Amount <= 0) {
            System.out.println("Invalid! Deposit amount must be positive!");
            return false;
        }
        Balance += Amount;
        System.out.println("Deposit successful! New Balance: " + Balance);
        return true;
    }

    public boolean Withdraw(double Amount) {
        if (Amount <= 0) {
            System.out.println("Invalid! Withdrawal amount must be positive.");
            return false;
        }
        if (Amount > Balance) {
            System.out.println("Insufficient Balance! Current Balance: " + Balance);
            return false;
        }
        Balance -= Amount;
        System.out.println("Withdrawal successful! New Balance: " + Balance);
        return true;
    }

    public void UpdateContactDetails(String Email, String PhoneNumber) {
        this.Email = Email;
        this.PhoneNumber = PhoneNumber;
        System.out.println("Contact details updated for Account No: " + AccountNumber);
    }

    public void DisplayAccountDetails() {
        System.out.println("--------------------------------------------------");
        System.out.println("Account Number : " + AccountNumber);
        System.out.println("Account Holder : " + AccountHolderName);
        System.out.println("Balance        : " + Balance);
        System.out.println("Email          : " + Email);
        System.out.println("Phone Number   : " + PhoneNumber);
        System.out.println("--------------------------------------------------");
    }
}

public class BankingApp {
    private Account[] Accounts;
    private int Count;
    private Scanner Input;

    public BankingApp() {
        Accounts = new Account[100];
        Count = 0;
        Input = new Scanner(System.in);
    }

    public void MainMenu() {
        System.out.println("Welcome to the Banking Application!");
        while (true) {
            System.out.println();
            System.out.println("1. Create a new account");
            System.out.println("2. Deposit money");
            System.out.println("3. Withdraw money");
            System.out.println("4. View account details");
            System.out.println("5. Update contact details");
            System.out.println("6. Exit");
            System.out.println();
            System.out.print("ENTER YOUR CHOICE: ");

            int Choice = Input.nextInt();
            switch (Choice) {
                case 1:
                    CreateAccount();
                    break;
                case 2:
                    PerformDeposit();
                    break;
                case 3:
                    PerformWithdrawal();
                    break;
                case 4:
                    ShowAccountDetails();
                    break;
                case 5:
                    UpdateContact();
                    break;
                case 6:
                    System.out.println("Exiting application. Goodbye!");
                    Input.close();
                    return;
                default:
                    System.out.println("Invalid choice! Please choose between 1 to 6!");
            }
        }
    }

    private void CreateAccount() {
        System.out.println("----- Create New Account -----");
        Input.nextLine();
        System.out.print("Enter account holder name: ");
        String Name = Input.nextLine();
        System.out.print("Enter Initial deposit amount: ");
        double InitialDeposit = Input.nextDouble();
        if (InitialDeposit <= 0) {
            System.out.println("Invalid! Deposit amount must be positive!");
            return;
        }
        Input.nextLine();
        System.out.print("Enter email address: ");
        String Email = Input.nextLine();
        System.out.print("Enter contact number: ");
        String Phone = Input.nextLine();

        EnsureCapacity();
        Accounts[Count++] = new Account(Name, InitialDeposit, Email, Phone);
    }

    private void PerformDeposit() {
        System.out.println("----- Deposit -----");
        System.out.print("Enter account number: ");
        int AccNo = Input.nextInt();
        int Idx = FindIndexByAccountNumber(AccNo);
        if (Idx == -1) {
            System.out.println("Account not found.");
            return;
        }
        System.out.print("Enter amount to deposit: ");
        double Amount = Input.nextDouble();
        Accounts[Idx].Deposit(Amount);
    }

    private void PerformWithdrawal() {
        System.out.println("----- Withdraw -----");
        System.out.print("Enter account number: ");
        int AccNo = Input.nextInt();
        int Idx = FindIndexByAccountNumber(AccNo);
        if (Idx == -1) {
            System.out.println("Account not found.");
            return;
        }
        System.out.print("Enter amount to withdraw: ");
        double Amount = Input.nextDouble();
        Accounts[Idx].Withdraw(Amount);
    }

    private void ShowAccountDetails() {
        System.out.println("----- View Account Details -----");
        System.out.print("Enter account number: ");
        int AccNo = Input.nextInt();
        int Idx = FindIndexByAccountNumber(AccNo);
        if (Idx == -1) {
            System.out.println("Account not found.");
            return;
        }
        Accounts[Idx].DisplayAccountDetails();
    }

    private void UpdateContact() {
        System.out.println("----- Update Contact Details -----");
        System.out.print("Enter account number: ");
        int AccNo = Input.nextInt();
        int Idx = FindIndexByAccountNumber(AccNo);
        if (Idx == -1) {
            System.out.println("Account not found.");
            return;
        }
        Input.nextLine();
        System.out.print("Enter new Email address: ");
        String Email = Input.nextLine();
        System.out.print("Enter new contact number: ");
        String Phone = Input.nextLine();
        Accounts[Idx].UpdateContactDetails(Email, Phone);
    }

    private int FindIndexByAccountNumber(long AccNo) {
        for (int i = 0; i < Count; i++) {
            if (Accounts[i].GetAccountNumber() == AccNo) {
                return i;
            }
        }
        return -1;
    }

    private void EnsureCapacity() {
        if (Count >= Accounts.length) {
            Account[] NewArr = new Account[Accounts.length * 2];
            for (int i = 0; i < Accounts.length; i++) {
                NewArr[i] = Accounts[i];
            }
            Accounts = NewArr;
        }
    }

    public static void main(String[] args) {
        BankingApp App = new BankingApp();
        App.MainMenu();
    }
}