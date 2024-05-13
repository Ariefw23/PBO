import java.util.ArrayList;
import java.util.Scanner;

record Siswa(String nama, long nim, String fakultas, String jurusan) {}

public class Student extends User {

private static final String[][] bookData = {
            {"1031", "Regulus", "Ichika", "Sejarah", "10"},
            {"1032", "Flos", "Minori", "Cerita", "11"},
            {"1033", "Bug", "KairikiBear", "Novel", "11"}
    };
    private final ArrayList<String[]> borrowedBooks = new ArrayList<>();
    private static final int MAXIMUM_BORROW_DAYS = 14;

    public void displayBooks() {
        System.out.println("===== List of Books =====");
        System.out.printf("%-4s %-20s %-15s %-15s %s\n", "ID", "Judul", "Author", "Kategori", "Stock");
        for (String[] book : bookData) {
            System.out.printf("%-4s %-20s %-15s %-15s %s\n", book[0], book[1], book[2], book[3], book[4]);
        }

        if (!borrowedBooks.isEmpty()) {
            System.out.println("\n===== List of Borrowed Books =====");
            System.out.printf("%-4s %-20s %-15s %-15s %s\n", "ID", "Judul", "Author", "Kategori", "Stock");
            for (String[] book : borrowedBooks) {
                System.out.printf("%-4s %-20s %-15s %-15s %s\n", book[0], book[1], book[2], book[3], book[4]);
            }
        } else {
            System.out.println("\nYou haven't borrowed any books yet.");
        }
    }

    public void borrowBook() {
        Scanner scanner = new Scanner(System.in);

        displayBooks();

        System.out.println("Input Id buku yang ingin dipinjam (input 99 untuk back)");
        System.out.print("Input: ");
        String bookId = scanner.nextLine();

        if (bookId.equals("99")) {
            return;
        }

        String[] selectedBook = null;
        for (String[] book : bookData) {
            if (book[0].equals(bookId)) {
                selectedBook = book;
                break;
            }
        }

        if (selectedBook == null) {
            System.out.println("Buku dengan ID tersebut tidak ditemukan.");
            return;
        }

        int stock = Integer.parseInt(selectedBook[4]);
        if (stock == 0) {
            System.out.println("Stock buku kosong! Silahkan pilih yang lain.");
            return;
        }

        System.out.println("Berapa lama buku akan dipinjam? (maksimal 14 hari)");
        System.out.print("Input lama (hari): ");
        int borrowDays = scanner.nextInt();
        scanner.nextLine(); // consume newline

        if (borrowDays > MAXIMUM_BORROW_DAYS) {
            System.out.println("Maksimum peminjaman adalah 14 hari.");
            return;
        }

        stock--; // decrease the stock
        selectedBook[4] = String.valueOf(stock); // update the stock

        borrowedBooks.add(selectedBook);
        System.out.println("Anda telah berhasil meminjam buku dengan ID: " + selectedBook[0]);
    }

    public void returnBook() {
        Scanner scanner = new Scanner(System.in);

        if (borrowedBooks.isEmpty()) {
            System.out.println("You haven't borrowed any books yet.");
            return;
        }

        System.out.println("===== List of Borrowed Books =====");
        System.out.printf("%-4s %-20s %-15s %-15s %s\n", "ID", "Judul", "Author", "Kategori", "Stock");
        for (String[] book : borrowedBooks) {
            System.out.printf("%-4s %-20s %-15s %-15s %s\n", book[0], book[1], book[2], book[3], book[4]);
        }

        System.out.print("Enter the ID of the book you want to return: ");
        String returnId = scanner.nextLine();

        boolean found = false;
        for (String[] book : borrowedBooks) {
            if (book[0].equals(returnId)) {
                int stock = Integer.parseInt(book[4]);
                stock++;
                book[4] = String.valueOf(stock);
                borrowedBooks.remove(book);
                found = true;
                System.out.println("Book with ID " + returnId + " has been returned successfully.");
                break;
            }
        }

        if (!found) {
            System.out.println("Book with ID " + returnId + " is not found in your borrowed books.");
        }
    }
    public void logout() {
        System.out.println("Logging out...");
    }

    public void menu() {
        Scanner scanner = new Scanner(System.in);
        int choice;

        do {
            System.out.println("===== Student Menu =====");
            System.out.println("1. Display Books");
            System.out.println("2. Borrow Book");
            System.out.println("3. Return Book");
            System.out.println("4. Logout");
            System.out.print("Enter your choice: ");
            choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    displayBooks();
                    break;
                case 2:
                    borrowBook();
                    break;
                case 3:
                    returnBook();
                    break;
                case 4:
                    logout();
                    break;
                default:
                    System.out.println("Invalid choice. Please enter again.");
            }
        } while (choice != 4);
    }
}
