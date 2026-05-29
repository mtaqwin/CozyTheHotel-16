package com.cozythehotel.model;

public abstract class Payment {
    protected double jumlah;

    public Payment(double jumlah) {
        this.jumlah = jumlah;
    }

    public abstract String prosesPembayaran();

    public double getJumlah() { return jumlah; }
    public void setJumlah(double jumlah) { this.jumlah = jumlah; }
}
