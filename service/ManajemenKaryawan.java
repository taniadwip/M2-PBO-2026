package service;

import model.Karyawan;
import java.util.ArrayList;
import java.util.List;

public class ManajemenKaryawan {
    // Koleksi objek dibungkus secara privat
    private List<Karyawan> daftarKaryawan;

    public ManajemenKaryawan() {
        this.daftarKaryawan = new ArrayList<>();
    }

    // Menambahkan objek karyawan ke daftar
    public void tambahKaryawan(Karyawan k) {
        daftarKaryawan.add(k);
        System.out.println("\n[Sukses] Karyawan berhasil didaftarkan dengan ID: " + k.getIdKaryawan());
    }

    // Menampilkan seluruh data karyawan
    public void tampilkanSemua() {
        if (daftarKaryawan.isEmpty()) {
            System.out.println("\n[Info] Belum ada data karyawan yang terdaftar.");
            return;
        }

        System.out.println("\n====================================================================");
        System.out.println("                       DAFTAR KARYAWAN AKTIF                        ");
        System.out.println("====================================================================");
        System.out.printf("%-10s %-25s %-18s %14s%n", "ID", "Nama", "Jabatan", "Gaji Pokok");
        System.out.println("--------------------------------------------------------------------");
        for (Karyawan k : daftarKaryawan) {
            System.out.printf("%-10s %-25s %-18s Rp %,11.0f%n",
                    k.getIdKaryawan(),
                    k.getNama(),
                    k.getJabatan(),
                    k.getGajiPokok());
        }
        System.out.println("====================================================================");
    }

    // Mencari objek Karyawan berdasarkan ID (Linear Search)
    public Karyawan cariKaryawanById(String id) {
        for (Karyawan k : daftarKaryawan) {
            if (k.getIdKaryawan().equalsIgnoreCase(id.trim())) {
                return k; // Ditemukan
            }
        }
        return null; // Tidak ditemukan
    }

    // Menghapus data karyawan berdasarkan ID
    public boolean hapusKaryawan(String id) {
        Karyawan k = cariKaryawanById(id);
        if (k != null) {
            daftarKaryawan.remove(k);
            return true;
        }
        return false;
    }
}