import java.util.ArrayList;
import java.util.Scanner;

record Mahasiswa(String nama, long nim, String jurusan) {
}
public class Codelab_Modul2 {
    private static final ArrayList<Mahasiswa> daftarMahasiswa = new ArrayList<>();
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        int pilihan;

        do {
            System.out.println("Menu:");
            System.out.println("1. Tambah Data Mahasiswa");
            System.out.println("2. Tampilkan Data Mahasiswa");
            System.out.println("3. Keluar");
            System.out.print("Pilih menu (1-3): ");
            pilihan = scanner.nextInt();
            scanner.nextLine();

            switch (pilihan) {
                case 1:
                    tambahDataMahasiswa();
                    break;
                case 2:
                    tampilDataMahasiswa();
                    break;
                case 3:
                    System.out.println("Terima kasih!");
                    break;
                default:
                    System.out.println("Pilihan tidak valid. Silakan pilih 1-3.");
                    break;
            }
        } while (pilihan != 3);
    }

    private static void tambahDataMahasiswa() {
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

        System.out.print("Jurusan Mahasiswa: ");
        String jurusan = scanner.nextLine();

        Mahasiswa mahasiswa = new Mahasiswa(nama, nim, jurusan);
        daftarMahasiswa.add(mahasiswa);

        System.out.println("Data mahasiswa berhasil ditambahkan.");
    }

    private static void tampilDataMahasiswa() {

        if (daftarMahasiswa.isEmpty()) {
            System.out.println("Belum ada data mahasiswa.");
        } else {
            System.out.println("Data Mahasiswa:");
            Universitas();
            String formatString = getString();
            System.out.printf(formatString, "Nama", "NIM", "Jurusan");

            for (Mahasiswa mahasiswa : daftarMahasiswa) {
                System.out.printf(formatString,
                        mahasiswa.nama(), mahasiswa.nim(), mahasiswa.jurusan());
            }
        }
    }

    private static void Universitas(){
        System.out.println("Universitas Muhammadiyah Malang");
    }
    private static String getString() {
        int maxNama = 0;
        int maxNIM = 0;
        int maxJurusan = 0;

        for (Mahasiswa mahasiswa : daftarMahasiswa) {
            maxNama = Math.max(maxNama, mahasiswa.nama().length());
            maxNIM = Math.max(maxNIM, String.valueOf(mahasiswa.nim()).length());
            maxJurusan = Math.max(maxJurusan, mahasiswa.jurusan().length());
        }

        return "%-" + (maxNama + 5) + "s%-"
                + (maxNIM + 5) + "s%-"
                + (maxJurusan + 5) + "s%n";
    }
}