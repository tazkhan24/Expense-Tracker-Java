package service;

import model.Transaction;

import java.time.YearMonth;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

// Manages all transaction operations like add, summary, etc.
public class TransactionManager {
    private final List<Transaction> transactions = new ArrayList<>();

    public void addTransaction(Transaction t) {
        transactions.add(t);
    }

    public void addAll(List<Transaction> list) {
        transactions.addAll(list);
    }

    public List<Transaction> getTransactions() {
        return transactions;
    }

    public void showMonthlySummary(YearMonth yearMonth) {
        double totalIncome = 0;
        double totalExpense = 0;

        Map<String, Double> incomeCategories = new HashMap<>();
        Map<String, Double> expenseCategories = new HashMap<>();

        for (Transaction t : transactions) {
            if (YearMonth.from(t.getDate()).equals(yearMonth)) {
                if (t.getType().equalsIgnoreCase("INCOME")) {
                    totalIncome += t.getAmount();
                    incomeCategories.merge(t.getCategory(), t.getAmount(), Double::sum);
                } else if (t.getType().equalsIgnoreCase("EXPENSE")) {
                    totalExpense += t.getAmount();
                    expenseCategories.merge(t.getCategory(), t.getAmount(), Double::sum);
                }
            }
        }

        System.out.println("\n--- Monthly Summary for " + yearMonth + " ---");
        System.out.println("Total Income: $" + totalIncome);
        incomeCategories.forEach((k, v) -> System.out.println("  " + k + ": $" + v));
        System.out.println("Total Expense: $" + totalExpense);
        expenseCategories.forEach((k, v) -> System.out.println("  " + k + ": $" + v));
        System.out.println("Net Savings: $" + (totalIncome - totalExpense));
    }
}