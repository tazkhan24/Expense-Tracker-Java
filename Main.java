import model.Transaction;
import service.TransactionManager;
import util.FileHandler;

import java.time.LocalDate;
import java.time.YearMonth;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class Main {

    private static final Scanner scanner = new Scanner(System.in);
    private static final TransactionManager transactionManager = new TransactionManager();

    public static void main(String[] args) {
        while (true) {
            try {
                System.out.println("\n--- Expense Tracker Menu ---");
                System.out.println("1. Add Income");
                System.out.println("2. Add Expense");
                System.out.println("3. View Monthly Summary");
                System.out.println("4. Load Transactions from File");
                System.out.println("5. Save Transactions to File");
                System.out.println("6. Exit");
                System.out.print("Choose an option: ");

                String input = scanner.nextLine();
                int choice = Integer.parseInt(input);

                switch (choice) {
                    case 1 -> handleAddTransaction("INCOME");
                    case 2 -> handleAddTransaction("EXPENSE");
                    case 3 -> handleSummary();
                    case 4 -> handleLoad();
                    case 5 -> handleSave();
                    case 6 -> {
                        System.out.println("Exiting... Goodbye!");
                        return;
                    }
                    default -> System.out.println("Invalid choice. Please select from 1 to 6.");
                }

            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a number.");
            }
        }
    }

    private static void handleAddTransaction(String type) {
        try {
            System.out.print("Enter date (YYYY-MM-DD): ");
            String dateStr = scanner.nextLine();
            LocalDate date = LocalDate.parse(dateStr);

            System.out.print("Enter " + type.toLowerCase() + " category: ");
            String category = scanner.nextLine();

            System.out.print("Enter amount: ");
            double amount = Double.parseDouble(scanner.nextLine());

            if (amount < 0) {
                System.out.println("Amount cannot be negative.");
                return;
            }

            Transaction t = new Transaction(date, type, category, amount);
            transactionManager.addTransaction(t);
            System.out.println(type + " transaction added successfully.");
        } catch (Exception e) {
            System.out.println("Error adding transaction: " + e.getMessage());
        }
    }

    private static void handleSummary() {
        try {
            System.out.print("Enter year and month (YYYY-MM): ");
            String input = scanner.nextLine();
            YearMonth ym = YearMonth.parse(input);
            transactionManager.showMonthlySummary(ym);
        } catch (Exception e) {
            System.out.println("Invalid format. Please enter in YYYY-MM format.");
        }
    }

    private static void handleLoad() {
        System.out.print("Enter file path to load: ");
        String path = scanner.nextLine();
        List<Transaction> list = FileHandler.loadTransactions(path);
        transactionManager.addAll(list);
    }

    private static void handleSave() {
        System.out.print("Enter file path to save: ");
        String path = scanner.nextLine();
        FileHandler.saveTransactions(path, transactionManager.getTransactions());
    }
}