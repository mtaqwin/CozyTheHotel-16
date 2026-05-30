package com.cozythehotel.controller;

import com.cozythehotel.database.CustomerDAO;
import com.cozythehotel.database.RoomDAO;
import com.cozythehotel.view.DashboardView;

import javafx.scene.Scene;
import javafx.stage.Stage;

public class DashboardController {
    private Stage panggung;
    private DashboardView tampilan;
    private RoomDAO daoKamar;
    private CustomerDAO daoPelanggan;

    public DashboardController(Stage panggung) {
        this.panggung = panggung;
        this.tampilan = new DashboardView();
        this.daoKamar = new RoomDAO();
        this.daoPelanggan = new CustomerDAO();
        
        inisialisasiHandler();
        perbaruiStatistik();
    }

    private void inisialisasiHandler() {
        tampilan.getTombolTambahReservasi().setOnAction(e -> new ReservationController(panggung).tampilkan());
        tampilan.getTombolKelolaKamar().setOnAction(e -> new RoomController(panggung).tampilkan());
        tampilan.getTombolDataPelanggan().setOnAction(e -> new CustomerController(panggung).tampilkan());
        tampilan.getTombolCheckout().setOnAction(e -> new CheckoutController(panggung).tampilkan());
        
        tampilan.getPemilihTanggal().setOnAction(e -> perbaruiStatistik());
    }

    private void perbaruiStatistik() {
        String tanggal = tampilan.getPemilihTanggal().getValue().toString();
        
        int terisi = daoKamar.getJumlahTerisiBerdasarkanTanggal(tanggal);
        int total = daoKamar.getJumlahTotalKamar();
        int aktif = daoPelanggan.getJumlahTamuAktifBerdasarkanTanggal(tanggal);
        
        tampilan.setStatistik(total - terisi, terisi, aktif);
    }

    public void tampilkan() {
        Scene adegan = new Scene(tampilan.getAkar(), 1100, 750);
        panggung.setScene(adegan);
        panggung.setTitle("CozyTheHotel – Dashboard Staff");
        panggung.setMaximized(true);
        panggung.show();
    }
}
