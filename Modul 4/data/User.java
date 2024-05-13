package data;

import books.Book;
import books.HistoryBook;
import books.StoryBook;
import books.TextBook;

import java.util.Scanner;

public class User {
    public String id,title,author,category;
    int stock;

    private final Scanner scanner;

    public User() {
        scanner = new Scanner(System.in);
    }

    public static Book[] books = {
            new HistoryBook("1031", "Regulus", "Ichika", 10),
            new StoryBook("1032", "Flos", "Minori", 11),
            new TextBook("1033", "Bug", "KairikiBear", 11)
    };

    public void displayBooks() {
        System.out.println("===== List of Books =====");
        System.out.printf("%-4s %-20s %-15s %-15s %s\n", "ID", "Judul", "Author", "Kategori", "Stock");
        for (Book book : books) {
            System.out.printf("%-4s %-20s %-15s %-15s %d\n", book.getId(), book.getTitle(), book.getAuthor(), book.getCategory(), book.getStock());
        }
            System.out.printf("%-4s %-20s %-15s %-15s %d\n", id, title, author, category, stock);

    }

    public void addBook() {

        System.out.print("Enter book ID: ");
        id = scanner.nextLine();

        System.out.print("Enter book title: ");
        title = scanner.nextLine();

        System.out.print("Enter book author: ");
         author = scanner.nextLine();

        System.out.print("Enter book category: ");
        category = scanner.nextLine();

        System.out.print("Enter book stock: ");
         stock = scanner.nextInt();

        System.out.println("Book added successfully.");
    }
}
