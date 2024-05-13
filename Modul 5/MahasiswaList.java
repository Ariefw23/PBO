import java.util.ArrayList;

public class MahasiswaList {
    private ArrayList<String> daftarMahasiswa;

    public MahasiswaList() {
        daftarMahasiswa = new ArrayList<>();
    }

    public void tambahMahasiswa(String nama) {
        if (nama.isEmpty()) {
            throw new IllegalArgumentException("Nama tidak boleh kosong");
        }
        daftarMahasiswa.add(nama);
    }

    public ArrayList<String> getDaftarMahasiswa() {
        return daftarMahasiswa;
    }
}
