import java.util.Scanner;

public class ArrayList {
    public static void main(String[] args) {
        MahasiswaList mahasiswaList = new MahasiswaList();
        Scanner scanner = new Scanner(System.in);

        System.out.println("Masukkan nama-nama mahasiswa. Ketik 'selesai' untuk mengakhiri input.");

        boolean isInputting = true;
        int i = 1;

        while (isInputting) {
            System.out.print("Nama ke-" + i + ": ");
            String nama = scanner.nextLine();

            try {
                if (nama.equalsIgnoreCase("selesai")) {
                    isInputting = false;
                } else {
                    mahasiswaList.tambahMahasiswa(nama);
                    i++;
                }
            } catch (IllegalArgumentException e) {
                System.out.println("Error: " + e.getMessage());
            }
        }

        System.out.println("\nDaftar Mahasiswa:");
        int j = 1;
        for (String nama : mahasiswaList.getDaftarMahasiswa()) {
            System.out.println((j++) + ". " + nama);
        }
    }
}
