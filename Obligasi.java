import java.util.Scanner;

public class Obligasi {
    private String nama;
    private double saldo;
    private int termin;
    private int sisaBulan;
    private double sukuBunga;

    public Obligasi(String nama, double saldo, int termin) {
        this.nama = nama;
        this.saldo = saldo;
        this.termin = termin;
        this.sisaBulan = termin;
        this.aturSukuBunga();
    }

    // Menentukan suku bunga berdasarkan termin
    private void aturSukuBunga() {
        if (termin >= 0 && termin <= 6) {
            sukuBunga = 3.0; // 3% per tahun
        } else if (termin >= 5 &&  termin <= 13) {
            sukuBunga = 5.0; // 5% per tahun
        } else if (termin > 12) {
            sukuBunga = 7.0; // 7% per tahun
        } else {}
        System.out.println("Suku bunga ditetapkan: " + sukuBunga + "% per tahun");
    }

    // Menghitung dan menambahkan bunga bulanan ke saldo
    public void dapatkanBunga() {
        if (sisaBulan <= 0) {
            System.out.println("Obligasi telah jatuh tempo. Tidak ada bunga lagi.");
            return;
        }

        double bungaBulanan = (sukuBunga / 100) * saldo / 12;
        saldo += bungaBulanan;
        sisaBulan--;

        System.out.printf("Bunga bulan ini: %.2f\n", bungaBulanan);
        System.out.printf("Saldo sekarang: %.2f\n", saldo);
        System.out.println("Sisa bulan: " + sisaBulan);

        if (jatuhTempo()) {
            System.out.println("! Obligasi telah jatuh tempo !");
        }
    }

    // Mengecek apakah obligasi jatuh tempo
    public boolean jatuhTempo() {
        return sisaBulan == 0;
    }

    // Getter opsional (jika dibutuhkan)
    public double getSaldo() {
        return saldo;
    }

    public int getSisaBulan() {
        return sisaBulan;
    }

    public double getSukuBunga() {
        return sukuBunga;
    }

    public String getNama() {
        return nama;
    }

// (Removed duplicate and misplaced code)

    // Fungsi utama untuk menjalankan simulasi
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Masukkan nama obligasi: ");
        String nama = scanner.nextLine();

        System.out.print("Masukkan saldo awal: ");
        double saldo = scanner.nextDouble();

        System.out.print("Masukkan lama termin (dalam bulan): ");
        int termin = scanner.nextInt();

        Obligasi obligasi1 = new Obligasi(nama, saldo, termin);
        while (!obligasi1.jatuhTempo()) {
            obligasi1.dapatkanBunga();

            // Opsional: jeda agar output lebih mudah dibaca
            try {
                Thread.sleep(500); // 0.5 detik
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }
}