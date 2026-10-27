package org.example;

import java.util.ArrayList;
import java.util.Scanner;

class Book {
    int id;
    String title;
    String author;
    boolean issued;

    Book(int id, String title, String author) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.issued = false;
    }
}

public class App {

    static ArrayList<Book> books = new ArrayList<>();
    static Scanner sc = new Scanner(System.in);
    static int nextId = 1;

    public static void main(String[] args) {
        books.add(new Book(nextId++, "Java Basics", "James Gosling"));
        books.add(new Book(nextId++, "Python Basics", "Guido van Rossum"));

        while (true) {
            System.out.println("\n===== LIBRARY MANAGEMENT SYSTEM =====");
            System.out.println("1. Add Book");
            System.out.println("2. View Books");
            System.out.println("3. Search Book");
            System.out.println("4. Issue Book");
            System.out.println("5. Return Book");
            System.out.println("6. Exit");
            System.out.print("Enter your choice: ");

            String input = sc.nextLine().trim();
            int choice;

            try {
                choice = Integer.parseInt(input);
            } catch (NumberFormatException e) {
                System.out.println("Please enter a number from 1 to 6.");
                continue;
            }

            switch (choice) {
                case 1:
                    addBook();
                    break;
                case 2:
                    viewBooks();
                    break;
                case 3:
                    searchBook();
                    break;
                case 4:
                    issueBook();
                    break;
                case 5:
                    returnBook();
                    break;
                case 6:
                    System.out.println("Thank you for using Library Management System!");
                    sc.close();
                    return;
                default:
                    System.out.println("Invalid choice. Enter 1 to 6.");
            }
        }
    }

    static void addBook() {
        System.out.print("Enter book title: ");
        String title = sc.nextLine().trim();

        System.out.print("Enter author name: ");
        String author = sc.nextLine().trim();

        if (title.isEmpty() || author.isEmpty()) {
            System.out.println("Title and author cannot be empty.");
            return;
        }

        books.add(new Book(nextId++, title, author));
        System.out.println("Book added successfully!");
    }

    static void viewBooks() {
        if (books.isEmpty()) {
            System.out.println("No books available.");
            return;
        }

        System.out.println("\n----- BOOK LIST -----");
        for (Book b : books) {
            System.out.println(
                "ID: " + b.id
                + " | Title: " + b.title
                + " | Author: " + b.author
                + " | Status: " + (b.issued ? "Issued" : "Available")
            );
        }
    }

    static void searchBook() {
        System.out.print("Enter book title to search: ");
        String title = sc.nextLine().trim();

        boolean found = false;

        for (Book b : books) {
            if (b.title.toLowerCase().contains(title.toLowerCase())) {
                System.out.println(
                    "ID: " + b.id
                    + " | Title: " + b.title
                    + " | Author: " + b.author
                    + " | Status: " + (b.issued ? "Issued" : "Available")
                );
                found = true;
            }
        }

        if (!found) {
            System.out.println("Book not found.");
        }
    }

    static void issueBook() {
        System.out.print("Enter book ID to issue: ");
        int id = readBookId();

        if (id == -1) {
            return;
        }

        for (Book b : books) {
            if (b.id == id) {
                if (b.issued) {
                    System.out.println("This book is already issued.");
                } else {
                    b.issued = true;
                    System.out.println("Book issued successfully!");
                }
                return;
            }
        }

        System.out.println("Book ID not found.");
    }

    static void returnBook() {
        System.out.print( "Enter book ID to return: ");
        int id = readBookId();

        if (id == -1) {
            return;
        }

        for (Book b : books) {
            if (b.id == id) {
                if (!b.issued) {
                    System.out.println("This book was not issued.");
                } else {
                    b.issued = false;
                    System.out.println("Book returned successfully!");
                }
                return;
            }
        }

        System.out.println("Book ID not found.");
    }

    static int readBookId() {
        String input = sc.nextLine().trim();

        try {
            int id = Integer.parseInt(input);
            if (id <= 0) {
                System.out.println("Please enter a valid positive ID.");
                return -1;
            }
            return id;
        } catch (NumberFormatException e) {
            System.out.println("Please enter a valid numeric ID.");
            return -1;
        }
 
    }
}