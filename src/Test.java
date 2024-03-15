import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

record Student(String nama, long nim, String jurusan, String fakultas) {}

public class Test {
    private static final ArrayList<Student> daftarMahasiswa = new ArrayList<>();
    private static final ArrayList<String> daftarBuku = new ArrayList<>();
    private static final HashMap<Long, ArrayList<String>> peminjamanBuku = new HashMap<>();
    private static final String ADMIN_USERNAME = "Firefly";
    private static final String ADMIN_PASSWORD = "waifudonat";
    private static final String[] MAHASISWA_NIM = {"202310370311037"};
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        System.out.println("===== Library System =====");

        System.out.print("1. Login as Student\n");
        System.out.print("2. Login as Admin\n");
        System.out.print("3. Exit\n");
        System.out.print("Choose (1-3) : ");

        String userType = scanner.nextLine();

        switch (userType) {
            case "1":
                mahasiswaLogin();
                break;
            case "2":
                adminLogin();
                break;
            case "3":
                exitLogin();
                break;
            default:
                System.out.println("Option Not Found.");
                break;
        }
    }

    public static void adminLogin() {
        System.out.print("Enter Admin Username: ");
        String username = scanner.nextLine();

        System.out.print("Enter Admin Password: ");
        String password = scanner.nextLine();

        if (username.equals(ADMIN_USERNAME) && password.equals(ADMIN_PASSWORD)) {
            System.out.println("Successful Login as Admin.");
            menuAdmin();
        } else {
            System.out.println("User not found.");
        }
    }

    public static void mahasiswaLogin() {
        long nim = getLoggedInStudentNIM();

        if (nim != -1) {
            System.out.println("Successful Login as Student.");
            studentMenu();
        } else {
            System.out.println("User not found.");
        }
    }

    public static void exitLogin() {
        System.out.println("Goodbye!");
    }

    public static void studentMenu() {
        int choice;

        do {
            System.out.println("===== Student Menu =====");
            System.out.println("1. Buku Terpinjam");
            System.out.println("2. Pinjam Buku");
            System.out.println("3. Logout");
            System.out.print("Enter your choice: ");
            choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    displayBooks();
                    break;
                case 2:
                    borrowBooks();
                    break;
                case 3:
                    logout();
                    break;
                default:
                    System.out.println("Invalid choice. Please enter again.");
            }
        } while (choice != 3);
    }

    public static void displayBooks() {
        long nim = getLoggedInStudentNIM();
        if (peminjamanBuku.containsKey(nim)) {
            ArrayList<String> borrowedBooks = peminjamanBuku.get(nim);
            if (borrowedBooks.isEmpty()) {
                System.out.println("Anda belum meminjam buku apapun.");
            } else {
                System.out.println("===== List Buku Yang Telah Dipinjam =====");
                System.out.println("ID\tJudul Buku\tAuthor\tStock");
                for (String book : borrowedBooks) {
                    System.out.println(book);
                }
            }
        } else {
            System.out.println("Anda belum meminjam buku apapun.");
        }
    }

    public static void borrowBooks() {
        System.out.println("===== List Buku =====");
        System.out.println("ID\t     Judul\t    Author\tStock");
        System.out.println("1031\t Regulus\tIchika\t10");
        System.out.println("1032\t Flos\t    Emu\t    11");
        System.out.println("1033\t Override\tTeto\t11\n");

        System.out.print("Masukkan ID buku yang ingin dipinjam: ");
        String idBuku = scanner.nextLine();

        long nim = getLoggedInStudentNIM();

        if (!peminjamanBuku.containsKey(nim)) {
            peminjamanBuku.put(nim, new ArrayList<>());
        }

        ArrayList<String> borrowedBooks = peminjamanBuku.get(nim);
        if (borrowedBooks.contains(idBuku)) {
            System.out.println("Anda sudah meminjam buku dengan ID: " + idBuku);
        } else {
            borrowedBooks.add(idBuku);
            System.out.println("Anda berhasil meminjam buku dengan ID: " + idBuku);
        }
    }

    public static void logout() {
        System.out.println("Logging out...");
    }

    private static void menuAdmin() {
        int choice;

        do {
            System.out.println("===== Admin Menu =====");
            System.out.println("1. Add Student");
            System.out.println("2. Display Students");
            System.out.println("3. Logout");
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
                    logout();
                    break;
                default:
                    System.out.println("Invalid choice. Please enter again.");
            }
        } while (choice != 3);
    }

    public static void addStudent() {
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

        Student mahasiswa = new Student(nama, nim, jurusan, fakultas);
        daftarMahasiswa.add(mahasiswa);

        System.out.println("Data mahasiswa berhasil ditambahkan.");
    }

    public static void displayStudents() {
        System.out.println("===== List of Students =====");
        for (Student student : daftarMahasiswa) {
            System.out.println("  Nama: " + student.nama() + "\n  NIM: " + student.nim() + "\n  Fakultas: " + student.fakultas() + "\n  Jurusan: " + student.jurusan());
            System.out.println("\n");
        }
    }

    public static long getLoggedInStudentNIM() {
        long nim = -1;
        while (nim == -1) {
            System.out.print("Enter Your NIM: ");
            String nimStr = scanner.nextLine();

            for (String validNim : MAHASISWA_NIM) {
                if (validNim.equals(nimStr)) {
                    nim = Long.parseLong(nimStr);
                    break;
                }
            }
            if (nim == -1) {
                System.out.println("User not found. Please try again.");
            }
        }
        return nim;
    }
}
