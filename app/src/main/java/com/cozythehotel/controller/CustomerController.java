package com.cozythehotel.controller;

import com.cozythehotel.database.ReservationDAO;
import com.cozythehotel.view.CustomerView;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class CustomerController {
    private Stage panggung;
    private CustomerView tampilan;
    private ReservationDAO daoReservasi;

    public CustomerController(Stage panggung) {
        this.panggung = panggung;
        this.tampilan = new CustomerView();
        this.daoReservasi = new ReservationDAO();
        
        inisialisasiHandler();
        muatDataBerdasarkanTanggal(java.time.LocalDate.now().toString());
    }

    private void inisialisasiHandler() {
        tampilan.getTombolKembali().setOnAction(e -> new DashboardController(panggung).tampilkan());
        
        tampilan.getPemilihTanggal().setOnAction(e -> {
            if (tampilan.getPemilihTanggal().getValue() != null) {
                muatDataBerdasarkanTanggal(tampilan.getPemilihTanggal().getValue().toString());
            }
        });
    }

    private void muatDataBerdasarkanTanggal(String tanggal) {
        tampilan.setPelanggan(daoReservasi.getReservasiBerdasarkanTanggal(tanggal));
    }

    public void tampilkan() {
        Scene adegan = new Scene(tampilan.getAkar(), 1100, 750);
        panggung.setScene(adegan);
        panggung.setTitle("Data Customer - CozyTheHotel");
        panggung.setMaximized(true);
        panggung.show();
    }
}
