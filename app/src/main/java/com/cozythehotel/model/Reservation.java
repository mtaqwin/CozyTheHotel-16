package com.cozythehotel.model;

public class Reservation {
    private int id;
    private int idPelanggan;
    private int idKamar;
    private String masuk;
    private String keluar;
    private String metodePembayaran;
    private String status;
    
    //UI
    private String namaPelanggan;
    private String teleponPelanggan;
    private String nomorKamar;
    private String tipeKamar;

    public Reservation(int id, int idPelanggan, int idKamar, String masuk, String keluar, String metodePembayaran, String status) {
        this.id = id;
        this.idPelanggan = idPelanggan;
        this.idKamar = idKamar;
        this.masuk = masuk;
        this.keluar = keluar;
        this.metodePembayaran = metodePembayaran;
        this.status = status;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public int getIdPelanggan() { return idPelanggan; }
    public void setIdPelanggan(int idPelanggan) { this.idPelanggan = idPelanggan; }

    public int getIdKamar() { return idKamar; }
    public void setIdKamar(int idKamar) { this.idKamar = idKamar; }

    public String getMasuk() { return masuk; }
    public void setMasuk(String masuk) { this.masuk = masuk; }

    public String getKeluar() { return keluar; }
    public void setKeluar(String keluar) { this.keluar = keluar; }

    public String getMetodePembayaran() { return metodePembayaran; }
    public void setMetodePembayaran(String metodePembayaran) { this.metodePembayaran = metodePembayaran; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    public String getNamaPelanggan() { return namaPelanggan; }
    public void setNamaPelanggan(String namaPelanggan) { this.namaPelanggan = namaPelanggan; }

    public String getTeleponPelanggan() { return teleponPelanggan; }
    public void setTeleponPelanggan(String teleponPelanggan) { this.teleponPelanggan = teleponPelanggan; }

    public String getNomorKamar() { return nomorKamar; }
    public void setNomorKamar(String nomorKamar) { this.nomorKamar = nomorKamar; }

    public String getTipeKamar() { return tipeKamar; }
    public void setTipeKamar(String tipeKamar) { this.tipeKamar = tipeKamar; }
}
