import java.util.ArrayList;
import java.util.Scanner;

record Siswa(String nama, long nim, String fakultas, String jurusan) {}

public class Tugas_mod2 {
    private static final String ADMIN_USERNAME = "Elysia";
    private static final String ADMIN_PASSWORD = "istriku";
    private static final String[] MAHASISWA_NIM = {"202310370311037"};

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("===== Library System =====");

        System.out.print("1. Login as Student\n");
        System.out.print("2. Login as Admin\n");
        System.out.print("3. Exit\n");
        System.out.print("Choose (1-3) : ");

        String userType = scanner.nextLine();

        switch (userType) {
            case "1":
                mahasiswaLogin(scanner);
                break;
            case "2":
                adminLogin(scanner);
                break;
            case "3":
                ExitLogin();
                break;
            default:
                System.out.println("Option Not Found.");
                break;
        }
    }

    private static void adminLogin(Scanner scanner) {
        System.out.print("Enter Admin Username: ");
        String username = scanner.nextLine();

        System.out.print("Enter Admin Password: ");
        String password = scanner.nextLine();

        if (username.equals(ADMIN_USERNAME) && password.equals(ADMIN_PASSWORD)) {
            System.out.println("Successful Login as Admin.");
            Admin admin = new Admin();
            admin.menuAdmin();
        } else {
            System.out.println("User Not Found.");
        }
    }

    private static void mahasiswaLogin(Scanner scanner) {
        System.out.print("Enter Your NIM: ");
        String nim = scanner.nextLine();

        if (nim.length() == 15) {
            boolean isValidNim = false;
            for (String validNim : MAHASISWA_NIM) {
                if (validNim.equals(nim)) {
                    isValidNim = true;
                    break;
                }
            }
            if (isValidNim) {
                System.out.println("Successful Login as Student.");
                Student student = new Student();
                student.menuStudent();
            } else {
                System.out.println("User Not Found.");
            }
        } else {
            System.out.println("NIM must be 15 characters long.");
        }
    }

    private static void ExitLogin() {
        System.out.print("Goodbye!");
    }
}

class Student {
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

    public void menuStudent() {
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

class Admin {
    private static final Scanner scanner = new Scanner(System.in);
    private static final ArrayList<Siswa> daftarSiswa = new ArrayList<>();
    private static final ArrayList<String> bookList = new ArrayList<>(); // Deklarasi arraylist bookList

    public Admin() {
        bookList.add("1031\t\tRegulus\t\tIchika\t\t\t\t10");
        bookList.add("1032\t\tFlos\t\tMinori\t\t\t\t11");
        bookList.add("1033\t\tBug\t\t\tKairikiBear\t\t\t11");
    }

    public void addBook() {
        System.out.print("Enter book ID: ");
        String bookId = scanner.nextLine();

        System.out.print("Enter book title: ");
        String title = scanner.nextLine();

        System.out.print("Enter book author: ");
        String author = scanner.nextLine();

        System.out.print("Enter book stock: ");
        int stock = scanner.nextInt();
        scanner.nextLine(); // Membuang karakter newline dari input sebelumnya

        String bookInfo = String.format("%s\t\t%s\t\t%s\t\t\t\t%d", bookId, title, author, stock);
        bookList.add(bookInfo);

        System.out.println("Book added successfully.");
    }

    public void displayAvailableBooks() {
        System.out.println("===== Available Books =====");
        System.out.printf("%-10s %-30s %-20s %-10s\n", "ID", "Title", "Author", "Stock");
        for (String book : bookList) {
            String[] bookInfo = book.split("\t\t");
            System.out.printf("%-10s %-30s %-20s %-10s\n", bookInfo[0], bookInfo[1], bookInfo[2], bookInfo[3]);
        }
    }

    public void addStudent() {
        System.out.print("Nama Mahasiswa: ");
        String nama = scanner.nextLine();

        long nim;
        while (true) {
            try {
                System.out.print("NIM Mahasiswa (maksimal 15 angka): ");
                nim = Long.parseLong(scanner.nextLine());
                if (String.valueOf(nim).length() == 15 && nim > 0) {
                    break;
                } else {
                    System.out.println("NIM harus terdiri dari 15 angka.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Masukkan angka yang valid.");
            }
        }

        System.out.print("Fakultas Mahasiswa: ");
        String fakultas = scanner.nextLine();

        System.out.print("Jurusan Mahasiswa: ");
        String jurusan = scanner.nextLine();

        Siswa siswa = new Siswa(nama, nim, fakultas, jurusan);
        daftarSiswa.add(siswa);

        System.out.println("Data mahasiswa berhasil ditambahkan.");
    }

    public void displayStudents() {
        System.out.println("===== List of Students =====");
        if (daftarSiswa.isEmpty()) {
            System.out.println("No students registered.");
        } else {
            System.out.printf("%-20s %-15s %-20s %-20s\n", "Nama", "NIM", "Fakultas", "Jurusan");
            for (Siswa siswa : daftarSiswa) {
                System.out.printf("%-20s %-15s %-20s %-20s\n", siswa.nama(), siswa.nim(), siswa.fakultas(), siswa.jurusan());
            }
        }
    }


    public void menuAdmin() {
        int choice;

        do {
            System.out.println("===== Admin Menu =====");
            System.out.println("1. Add Student");
            System.out.println("2. Display Students");
            System.out.println("3. Add Book");
            System.out.println("4. Display Available Book");
            System.out.println("5. Logout");
            System.out.print("Enter your choice: ");
            choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    addStudent();
                    break;
                case 2:
                    displayStudents();
                    break;
                case 3:
                    addBook();
                    break;
                case 4:
                    displayAvailableBooks();
                    break;
                case 5:
                    System.out.println("Logging out...");
                    break;
                default:
                    System.out.println("Invalid choice. Please enter again.");
            }
        } while (choice != 5);
    }
}