import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


class Account {

    private double balance; 

    public Account(double initialBalance) {

        if (initialBalance >= 0.0) {

            balance = initialBalance;

        } 

        else {

            throw new IllegalArgumentException("Initial balance must be > 0");
        }
    }

    public double getBalance() {
        return balance;
    }

    public void credit(double amount) {
        if (amount > 0) {
            balance += amount;
        }
    }

    public boolean debit(double amount) {

        if (amount > balance) {

            throw new IllegalArgumentException("Debit amount exceeded account balance");

        } 
        else {
            balance -= amount;
            return true;
        }
    }
}



class SavingsAccount extends Account {

    private double interestRate; 

    public SavingsAccount(double initialBalance, double interestRate) {

        super(initialBalance);

        if (interestRate >= 0.0) {

            this.interestRate = interestRate;

        } else {

            throw new IllegalArgumentException("Interest rate must be > 0");
        }
    }

    public double calculateInterest() {
        return getBalance() * interestRate;
    }
}


class CheckingAccount extends Account {

    private double fee; 

    public CheckingAccount(double initialBalance, double fee) {

        super(initialBalance);
        if (fee >= 0.0) {

            this.fee = fee;

        } else {
            throw new IllegalArgumentException("Fee must be > 0");
        }
    }

    @Override

    public void credit(double amount) {
        super.credit(amount);
        super.debit(fee); 
    }

    @Override

    public boolean debit(double amount) {

        if (super.debit(amount)) {

            super.debit(fee); 

            return true;
        }
        return false;
    }
}


public class BankGUI extends JFrame {

    private JTextField balanceField, amountField, interestField, feeField;
    private JTextArea outputArea;
    private JButton creditButton, debitButton, interestButton;
    private Account currentAccount;

    public BankGUI() {
        super("Bank Account System");

        
        JPanel inputPanel = new JPanel(new GridLayout(5, 2));

        
        JLabel initialBalance = new JLabel("Initial Balance:");
        initialBalance.setFont(new Font("Arial", Font.BOLD, 14)); 
        inputPanel.add(initialBalance);

        balanceField = new JTextField();
        inputPanel.add(balanceField);

        
        JLabel amount = new JLabel("Amount:");
        amount.setFont(new Font("Arial", Font.BOLD, 14)); 
        inputPanel.add(amount);

        amountField = new JTextField();
        inputPanel.add(amountField);

       
        JLabel interestRate = new JLabel("Interest Rate (Savings):");
        interestRate.setFont(new Font("Arial", Font.BOLD, 14));
        inputPanel.add(interestRate);

        interestField = new JTextField();
        inputPanel.add(interestField);

        
        JLabel fee = new JLabel("Fee (Checking):");
        fee.setFont(new Font("Arial", Font.BOLD, 14));
        inputPanel.add(fee);

        feeField = new JTextField();
        inputPanel.add(feeField);

        add(inputPanel, BorderLayout.NORTH);

    
        outputArea = new JTextArea(10, 30);
        outputArea.setEditable(false);
        add(new JScrollPane(outputArea), BorderLayout.CENTER);

       
        JPanel buttonPanel = new JPanel();
        creditButton = new JButton("Credit");
        debitButton = new JButton("Debit");
        interestButton = new JButton("Calculate Interest");

        creditButton.setBackground(Color.BLACK );
        creditButton.setForeground(Color.WHITE);

        debitButton.setBackground(Color.BLACK);
        debitButton.setForeground(Color.WHITE);

        interestButton.setBackground(Color.BLACK);
        interestButton.setForeground(Color.WHITE);

        buttonPanel.add(creditButton);
        buttonPanel.add(debitButton);
        buttonPanel.add(interestButton);

        add(buttonPanel, BorderLayout.SOUTH);

        
        creditButton.addActionListener(new ActionListener() {

            @Override

        public void actionPerformed(ActionEvent e) {

                try {

                    double amount = Double.parseDouble(amountField.getText());
                    currentAccount.credit(amount);
                    outputArea.append("Credited " + amount + ". New balance: " + currentAccount.getBalance() + "\n");
                } 
                
                catch (Exception ex) {
                    outputArea.append("Error: " + ex.getMessage() + "\n");
                }
            }
        });

        debitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                try {

                    double amount = Double.parseDouble(amountField.getText());
                    currentAccount.debit(amount);
                    outputArea.append("Debited " + amount + ". New balance: " + currentAccount.getBalance() + "\n");

                } 
                
                catch (Exception ex) {
                    outputArea.append("Error: " + ex.getMessage() + "\n");
                }
            }
        });

        interestButton.addActionListener(new ActionListener() {
            @Override
           
            public void actionPerformed(ActionEvent e) {

                if (currentAccount instanceof SavingsAccount) {

                    SavingsAccount savings = (SavingsAccount) currentAccount;
                    double interest = savings.calculateInterest();
                    outputArea.append("Calculated interest: " + interest + "\n");

                } 
                else {
                    outputArea.append("Interest calculation is only for SavingsAccount.\n");
                }
            }
        });

       
        JButton SavingsButton = new JButton("Create Savings Account");
        JButton CheckingButton = new JButton("Create Checking Account");

        SavingsButton.setBackground(Color.BLACK);
        SavingsButton.setForeground(Color.WHITE);

        CheckingButton.setBackground(Color.BLACK);
        CheckingButton.setForeground(Color.WHITE);
        
        buttonPanel.add(SavingsButton);
        buttonPanel.add(CheckingButton);

        SavingsButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

                try {
                    double balance = Double.parseDouble(balanceField.getText());
                    double interest = Double.parseDouble(interestField.getText());
                    currentAccount = new SavingsAccount(balance, interest);
                    outputArea.append("Savings Account created with balance: " + balance + " and interest rate: " + interest + "\n");
                } 
                catch (Exception ex) {
                    outputArea.append("Error: " + ex.getMessage() + "\n");
                }
            }
        });

        CheckingButton.addActionListener(new ActionListener() {
           
            @Override

            public void actionPerformed(ActionEvent e) {
                try {
                    double balance = Double.parseDouble(balanceField.getText());
                    double fee = Double.parseDouble(feeField.getText());
                    currentAccount = new CheckingAccount(balance, fee);
                    outputArea.append("Checking Account created with balance: " + balance + " and fee: " + fee + "\n");
                } 
                catch (Exception ex) {
                    outputArea.append("Error: " + ex.getMessage() + "\n");
                }
            }
        });
    }

 public static void main(String[] args) {
        BankGUI app = new BankGUI();
        app.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        app.setSize(800, 400);
        app.setResizable(false);
        app.setVisible(true);
    }
}
