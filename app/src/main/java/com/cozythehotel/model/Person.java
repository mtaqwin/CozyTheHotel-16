package com.cozythehotel.model;

public abstract class Person {
    private int id;
    private String nama;
    private String telepon;

    public Person(int id, String nama, String telepon) {
        this.id = id;
        this.nama = nama;
        this.telepon = telepon;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getNama() { return nama; }
    public void setNama(String nama) { this.nama = nama; }

    public String getTelepon() { return telepon; }
    public void setTelepon(String telepon) { this.telepon = telepon; }
}
