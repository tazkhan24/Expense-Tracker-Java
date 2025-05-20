# Expense-Tracker-Java

## Overview
The Expense Tracker is a Java-based console application designed to help users manage their personal finances. It allows users to add income and expense transactions, view monthly summaries, and save or load transactions from files. The application is built with modularity in mind, separating concerns like data representation, business logic, and file handling.

## Features
- **Add Income**: Record income transactions with date, category, and amount.
- **Add Expense**: Record expense transactions with date, category, and amount.
- **View Monthly Summary**: Display a summary of income and expenses for a specific month.
- **Load Transactions**: Load transactions from a file.
- **Save Transactions**: Save all transactions to a file.

## Technologies Used
- **Programming Language**: Java
- **Libraries**:
    - `java.time` for date and time handling.
    - `java.util` for collections and input handling.
- **Custom Classes**:
    - `Transaction` for representing individual transactions.
    - `TransactionManager` for managing transactions and summaries.
    - `FileHandler` for file operations.

## File Structure
1. **`src/Main.java`**
    - **Purpose**: Entry point of the application.
    - **Key Components**:
        - Menu-driven console interface for user interaction.
        - Methods for handling transactions, summaries, and file operations:
            - `handleAddTransaction(String type)`: Adds income or expense transactions.
            - `handleSummary()`: Displays a monthly summary of transactions.
            - `handleLoad()`: Loads transactions from a file.
            - `handleSave()`: Saves transactions to a file.

2. **`model/Transaction.java`**
    - **Purpose**: Represents a financial transaction.
    - **Key Components**:
        - Fields: `LocalDate date`, `String type`, `String category`, `double amount`.
        - Constructor: Initializes transaction details.
        - Getters/Setters: Access and modify transaction properties.

3. **`service/TransactionManager.java`**
    - **Purpose**: Manages the list of transactions and provides summary functionality.
    - **Key Components**:
        - Fields: `List<Transaction> transactions`.
        - Methods:
            - `addTransaction(Transaction t)`: Adds a transaction to the list.
            - `addAll(List<Transaction> list)`: Adds multiple transactions.
            - `showMonthlySummary(YearMonth ym)`: Displays income and expense summary for a specific month.
            - `getTransactions()`: Returns the list of transactions.

4. **`util/FileHandler.java`**
    - **Purpose**: Handles file operations for saving and loading transactions.
    - **Key Components**:
        - Methods:
            - `loadTransactions(String path)`: Reads transactions from a file and returns a list.
            - `saveTransactions(String path, List<Transaction> transactions)`: Writes transactions to a file.

## How to Run
1. Clone the repository:
   ```bash
   git clone https://github.com/your-username/Expense-Tracker-Java.git