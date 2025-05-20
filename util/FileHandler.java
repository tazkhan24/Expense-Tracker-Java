package util;

import model.Transaction;

import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class FileHandler {

    public static List<Transaction> loadTransactions(String filePath) {
        List<Transaction> list = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            int count = 0;

            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length != 4) {
                    System.out.println("Skipping invalid line: " + line);
                    continue;
                }

                try {
                    LocalDate date = LocalDate.parse(parts[0].trim());
                    String type = parts[1].trim().toUpperCase();
                    String category = parts[2].trim();
                    double amount = Double.parseDouble(parts[3].trim());

                    list.add(new Transaction(date, type, category, amount));
                    count++;
                } catch (Exception e) {
                    System.out.println("Error parsing line: " + line + " → " + e.getMessage());
                }
            }

            System.out.println("Loaded " + count + " valid transactions.");

        } catch (FileNotFoundException e) {
            System.out.println("File not found: " + filePath);
        } catch (IOException e) {
            System.out.println("Error reading file: " + e.getMessage());
        }

        return list;
    }

    public static void saveTransactions(String filePath, List<Transaction> transactions) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(filePath))) {
            for (Transaction t : transactions) {
                writer.println(t);
            }
            System.out.println("Transactions saved successfully.");
        } catch (IOException e) {
            System.out.println("Error writing to file: " + e.getMessage());
        }
    }
}