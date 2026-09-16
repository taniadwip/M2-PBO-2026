package model;

public class Karyawan {
    // Static counter untuk generate ID otomatis
    private static int counterId = 1;

    // Atribut privat: disembunyikan total dari package lain
    private String idKaryawan;
    private String nama;
    private String jabatan;
    private double gajiPokok;

    // Konstruktor publik agar bisa diinstansiasi dari package service/app
    public Karyawan(String nama, String jabatan, double gajiPokok) {
        // Format ID otomatis: K-001, K-002, dst.
        this.idKaryawan = String.format("K-%03d", counterId++);
        setNama(nama);
        setJabatan(jabatan);
        setGajiPokok(gajiPokok);
    }

    // --- GETTER (Accessor) ---
    // idKaryawan hanya memiliki getter (bersifat Read-Only dari luar package)
    public String getIdKaryawan() {
        return idKaryawan;
    }

    public String getNama() {
        return nama;
    }

    public String getJabatan() {
        return jabatan;
    }

    public double getGajiPokok() {
        return gajiPokok;
    }

    // --- SETTER (Mutator dengan Validasi Enkapsulasi) ---
    public void setNama(String nama) {
        if (nama != null && !nama.trim().isEmpty()) {
            this.nama = nama;
        } else {
            this.nama = "Anonim";
        }
    }

    public void setJabatan(String jabatan) {
        if (jabatan != null && !jabatan.trim().isEmpty()) {
            this.jabatan = jabatan;
        } else {
            this.jabatan = "Staff";
        }
    }

    public boolean setGajiPokok(double gajiPokok) {
        if (gajiPokok >= 0.0) {
            this.gajiPokok = gajiPokok;
            return true;
        } else {
            System.out.println("[Error Enkapsulasi] Gaji pokok tidak boleh bernilai negatif!");
            this.gajiPokok = 0.0;
            return false;
        }
    }

    // Business Method: Perhitungan dan pencetakan slip gaji resmi
    public void cetakSlipGaji(int jamKerja, int jamLembur, double persenBpjs) {
        final double TARIF_LEMBUR = 50000.0;
        double upahLembur = jamLembur * TARIF_LEMBUR;
        double potonganBpjs = this.gajiPokok * (persenBpjs / 100.0);
        double gajiBersih = (this.gajiPokok + upahLembur) - potonganBpjs;

        System.out.println("\n======================================================");
        System.out.println("                 SLIP GAJI KARYAWAN                   ");
        System.out.println("======================================================");
        System.out.printf("%-22s: %s%n", "ID Karyawan", idKaryawan);
        System.out.printf("%-22s: %s%n", "Nama Pegawai", nama);
        System.out.printf("%-22s: %s%n", "Jabatan", jabatan);
        System.out.printf("%-22s: %d jam%n", "Total Jam Kerja", jamKerja);
        System.out.println("------------------------------------------------------");
        System.out.printf("%-25s: Rp %,12.0f%n", "Gaji Pokok", gajiPokok);
        System.out.printf("%-25s: Rp %,12.0f%n", "Upah Lembur (" + jamLembur + " jam)", upahLembur);
        System.out.printf("%-25s: Rp %,12.0f%n", "Potongan BPJS (" + (int)persenBpjs + "%)", potonganBpjs);
        System.out.println("------------------------------------------------------");
        System.out.printf("%-25s: Rp %,12.0f%n", "TAKE HOME PAY (BERSIH)", gajiBersih);
        System.out.println("======================================================\n");
    }
}