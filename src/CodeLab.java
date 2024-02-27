import java.time.LocalDate;
import java.time.Period;
import  java.util.Scanner;

public class CodeLab {
    public static void main(String[] args) {
        String Nama;
        String JenisKelamin;
        String Tanggal;
        Scanner objinput = new Scanner(System.in);

        System.out.print("Nama : ");
        Nama = objinput.nextLine();
        System.out.print("Jenis Kelamin (L/P) : ");
        JenisKelamin = objinput.nextLine();
        System.out.print("Tanggal (yyyy/mm/dd) : ");
        Tanggal = objinput.nextLine();

        LocalDate birthdate = LocalDate.parse(Tanggal);

        LocalDate today = LocalDate.now();

        Period period = Period.between(birthdate, today);
        int years = period.getYears();
        int mounth = period.getMonths();

        System.out.println("Nama : " + Nama);
        System.out.println("Jenis Kelamin : " + JenisKelamin);
        System.out.println("Umur Anda : " + years + " Tahun" + mounth + " Bulan");

    }
}




