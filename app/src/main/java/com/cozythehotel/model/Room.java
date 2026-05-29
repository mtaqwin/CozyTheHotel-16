package com.cozythehotel.model;

public class Room {
    private int id;
    private String nomorKamar;
    private String tipeKamar;
    private String status; 
    private String namaPelanggan;

    public Room(int id, String nomorKamar, String tipeKamar, String status, String namaPelanggan) {
        this.id = id;
        this.nomorKamar = nomorKamar;
        this.tipeKamar = tipeKamar;
        this.status = status;
        this.namaPelanggan = namaPelanggan;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getNomorKamar() { return nomorKamar; }
    public void setNomorKamar(String nomorKamar) { this.nomorKamar = nomorKamar; }

    public String getTipeKamar() { return tipeKamar; }
    public void setTipeKamar(String tipeKamar) { this.tipeKamar = tipeKamar; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    public String getNamaPelanggan() { return namaPelanggan; }
    public void setNamaPelanggan(String namaPelanggan) { this.namaPelanggan = namaPelanggan; }
}
