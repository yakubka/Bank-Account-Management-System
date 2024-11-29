#Bank-Account-Management-System


This project implements a simple Bank Account Management System using Java and Swing for the graphical user interface (GUI). 
It demonstrates the use of inheritance, method overriding, and exception handling in Java. 
The system manages three types of accounts: a generic account (Account), a savings account (SavingsAccount), and a checking account (CheckingAccount).

Features

    Account Types:

        Account: A base class representing a generic bank account.
        SavingsAccount: Inherits from Account and calculates interest based on a user-defined interest rate.
        CheckingAccount: Inherits from Account and applies a transaction fee for each debit or credit operation.

    Functionality:

        Deposit (Credit) money into the account.
        Withdraw (Debit) money, ensuring the account has sufficient balance.
        Calculate interest (for savings accounts only).
        Apply transaction fees (for checking accounts only).

    Graphical User Interface:

        Intuitive GUI for creating accounts, depositing/withdrawing money, and viewing account operations.
        User input fields for balance, interest rate, transaction amount, and fees.
        Real-time feedback displayed in a text area.

Installation and Usage

    Prerequisites

    Java Development Kit (JDK) (version 8 or above).
    Java Swing (part of JDK).
    An IDE such as Eclipse, IntelliJ IDEA, or NetBeans (optional).

    Steps to Run

    Clone or download this repository to your local machine.
    Open the project in your preferred IDE or text editor.
    Compile and run the BankGUI class.
    
    Interact with the GUI:
     
        Enter initial balance, interest rate, or transaction fees.
        Select account type (Savings or Checking).
        Perform credit, debit, or interest calculations.

Class Structure

1. Account

    Description: Base class for all account types.

    Attributes:
        balance (private): Tracks the account balance.
   
    Methods:
        credit(double amount): Adds money to the account balance.
        debit(double amount): Withdraws money, ensuring sufficient balance.
        getBalance(): Returns the current balance.

2. SavingsAccount

    Inherits: Account

    Additional Attribute:
        interestRate: Percentage interest rate.
    
    Additional Method:
        calculateInterest(): Returns the interest earned based on the current balance.

3. CheckingAccount

    Inherits: Account

    Additional Attribute:
        fee: Transaction fee applied on debit and credit.
   
    Overridden Methods:
        credit(double amount): Adds money and applies the fee.
        debit(double amount): Withdraws money and applies the fee if successful.

Graphical User Interface (GUI)

    Developed using Java Swing.

    Components:
    
        JTextField: Input fields for balance, amount, interest rate, and fees.
        JTextArea: Displays account operations and feedback.
        JButton: Buttons for creating accounts and performing transactions.
        JPanel: Organizes input fields and buttons in the GUI.

Example Usage

    Create a Savings Account:
       
	Enter an initial balance and interest rate.
       	Click "Create Savings Account."
   	
	Deposit Money:
        Enter the deposit amount and click "Credit."
    
	Withdraw Money:
        Enter the withdrawal amount and click "Debit."
    
	Calculate Interest (Savings Account only):
     	Click "Calculate Interest" to see the interest earned.

Error Handling


    Insufficient Funds: Throws an exception when trying to withdraw more than the available balance.
    Negative Initial Balance or Interest Rate: Throws an exception during account creation.


Future Enhancements


    Add data persistence to save account information.
    Implement user authentication.
    Expand functionality to include additional account types.


License

This project is open-source and available under the MIT License.

Enjoy using the Bank Account Management System)
