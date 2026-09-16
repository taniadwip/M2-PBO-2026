package app;

import model.Karyawan;
import service.ManajemenKaryawan;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ManajemenKaryawan manajemen = new ManajemenKaryawan();

        // Data awal sebagai sampel
        manajemen.tambahKaryawan(new Karyawan("Ahmad Fauzi", "Tech Lead", 10000000));
        manajemen.tambahKaryawan(new Karyawan("Siti Rahma", "UI Designer", 7500000));

        int pilihan = 0;

        do {
            System.out.println("\n=========================================");
            System.out.println("       SISTEM PENGGAJIAN KANTOR (HR)     ");
            System.out.println("=========================================");
            System.out.println("1. Tambah Karyawan Baru");
            System.out.println("2. Lihat Daftar Seluruh Karyawan");
            System.out.println("3. Proses & Cetak Slip Gaji");
            System.out.println("4. Hapus Data Karyawan");
            System.out.println("5. Keluar");
            System.out.print("Pilih menu [1-5]: ");

            // Validasi input angka menu
            if (!scanner.hasNextInt()) {
                System.out.println("\n[Peringatan] Masukkan angka pilihan yang valid!");
                scanner.nextLine(); // Buang input yang salah
                continue;
            }

            pilihan = scanner.nextInt();
            scanner.nextLine(); // Membersihkan sisa newline

            switch (pilihan) {
                case 1:
                    System.out.println("\n--- TAMBAH KARYAWAN BARU ---");
                    System.out.print("Nama Pegawai   : ");
                    String nama = scanner.nextLine();
                    System.out.print("Jabatan        : ");
                    String jabatan = scanner.nextLine();
                    System.out.print("Gaji Pokok (Rp): ");

                    if (scanner.hasNextDouble()) {
                        double gaji = scanner.nextDouble();
                        scanner.nextLine();

                        if (gaji < 0) {
                            System.out.println("[Ditolak] Gaji tidak boleh bernilai negatif!");
                        } else {
                            Karyawan kBaru = new Karyawan(nama, jabatan, gaji);
                            manajemen.tambahKaryawan(kBaru);
                        }
                    } else {
                        System.out.println("[Error] Input nominal gaji harus berupa angka!");
                        scanner.nextLine();
                    }
                    break;

                case 2:
                    manajemen.tampilkanSemua();
                    break;

                case 3:
                    System.out.println("\n--- PROSES PENGGAJIAN ---");
                    System.out.print("Masukkan ID Karyawan (contoh: K-001): ");
                    String idCari = scanner.nextLine();

                    Karyawan k = manajemen.cariKaryawanById(idCari);
                    if (k == null) {
                        System.out.println("\n[Peringatan] Karyawan dengan ID \"" + idCari + "\" tidak ditemukan!");
                    } else {
                        System.out.println("Data Ditemukan: " + k.getNama() + " (" + k.getJabatan() + ")");
                        
                        System.out.print("Masukkan Total Jam Kerja Reguler : ");
                        int jamKerja = scanner.nextInt();
                        System.out.print("Masukkan Jumlah Jam Lembur       : ");
                        int jamLembur = scanner.nextInt();
                        System.out.print("Masukkan Persentase BPJS (%)     : ");
                        double bpjs = scanner.nextDouble();
                        scanner.nextLine();

                        // Validasi data operasional
                        if (jamLembur < 0 || bpjs < 0 || bpjs > 100) {
                            System.out.println("\n[Error] Jam lembur tidak boleh minus dan BPJS harus di antara 0-100%!");
                        } else {
                            k.cetakSlipGaji(jamKerja, jamLembur, bpjs);
                        }
                    }
                    break;

                case 4:
                    System.out.println("\n--- HAPUS DATA KARYAWAN ---");
                    System.out.print("Masukkan ID Karyawan yang akan dihapus: ");
                    String idHapus = scanner.nextLine();

                    if (manajemen.hapusKaryawan(idHapus)) {
                        System.out.println("\n[Sukses] Data karyawan dengan ID \"" + idHapus + "\" berhasil dihapus.");
                    } else {
                        System.out.println("\n[Gagal] Karyawan dengan ID \"" + idHapus + "\" tidak ditemukan!");
                    }
                    break;

                case 5:
                    System.out.println("\nKeluar dari program. Selesai.");
                    break;

                default:
                    System.out.println("\n[Pilihan Tidak Valid] Silakan pilih angka 1 sampai 5.");
                    break;
            }

        } while (pilihan != 5);

        scanner.close();
    }
}