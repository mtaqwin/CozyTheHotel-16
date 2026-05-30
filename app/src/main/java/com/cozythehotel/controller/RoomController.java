package com.cozythehotel.controller;

import com.cozythehotel.database.RoomDAO;
import com.cozythehotel.view.RoomView;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class RoomController {
    private Stage panggung;
    private RoomView tampilan;
    private RoomDAO daoKamar;

    public RoomController(Stage panggung) {
        this.panggung = panggung;
        this.tampilan = new RoomView();
        this.daoKamar = new RoomDAO();
        
        inisialisasiHandler();
        muatDataKamar(java.time.LocalDate.now().toString());
    }

    private void inisialisasiHandler() {
        tampilan.getTombolKembali().setOnAction(e -> new DashboardController(panggung).tampilkan());
        
        tampilan.getPemilihTanggal().setOnAction(e -> {
            if (tampilan.getPemilihTanggal().getValue() != null) {
                muatDataKamar(tampilan.getPemilihTanggal().getValue().toString());
            }
        });
    }

    private void muatDataKamar(String tanggal) {
        tampilan.tampilkanKamar(daoKamar.getStatusKamarBerdasarkanTanggal(tanggal));
    }

    public void tampilkan() {
        Scene adegan = new Scene(tampilan.getAkar(), 1100, 750);
        panggung.setScene(adegan);
        panggung.setTitle("Manajemen Kamar - CozyTheHotel");
        panggung.setMaximized(true);
        panggung.show();
    }
}
