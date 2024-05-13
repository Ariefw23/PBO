import java.util.ArrayList;
import java.util.Scanner;

public class Admin extends User {

private static final Scanner scanner = new Scanner(System.in);
    private static final ArrayList<Siswa> daftarSiswa = new ArrayList<>();
    private static final ArrayList<String> bookList = new ArrayList<>();

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
        scanner.nextLine();

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

    @Override
    public void menu() {
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
