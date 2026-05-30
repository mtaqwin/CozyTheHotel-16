package com.cozythehotel.controller;

import com.cozythehotel.database.ReservationDAO;
import com.cozythehotel.database.RoomDAO;
import com.cozythehotel.model.Reservation;
import com.cozythehotel.view.CheckoutView;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.stage.Stage;

public class CheckoutController {
    private Stage panggung;
    private CheckoutView tampilan;
    private ReservationDAO daoReservasi;
    private RoomDAO daoKamar;

    public CheckoutController(Stage panggung) {
        this.panggung = panggung;
        this.tampilan = new CheckoutView();
        this.daoReservasi = new ReservationDAO();
        this.daoKamar = new RoomDAO();
        
        inisialisasiHandler();
        muatData();
    }

    private void inisialisasiHandler() {
        tampilan.getTombolKembali().setOnAction(e -> new DashboardController(panggung).tampilkan());
        
        tampilan.getPemilihTanggal().setOnAction(e -> muatData());

        @SuppressWarnings("unchecked")
        TableColumn<Reservation, String> kolomStatus = (TableColumn<Reservation, String>) tampilan.getTabel().getColumns().get(3);
        kolomStatus.setOnEditCommit(event -> {
            Reservation res = event.getRowValue();
            String statusBaru = event.getNewValue();
            
            if (statusBaru.equals("Sudah Checkout")) {
                prosesCheckout(res);
            } else {
                daoReservasi.perbaruiStatusReservasi(res.getId(), statusBaru);
                res.setStatus(statusBaru);
            }
        });
    }

    private void muatData() {
        String tanggal = tampilan.getPemilihTanggal().getValue().toString();
        tampilan.setReservasi(daoReservasi.getReservasiBerdasarkanTanggal(tanggal));
    }

    private void prosesCheckout(Reservation terpilih) {
        daoReservasi.perbaruiStatusReservasi(terpilih.getId(), "Sudah Checkout");
        daoKamar.perbaruiStatusKamar(terpilih.getIdKamar(), "Available", null);
        
        tampilkanPeringatan("Success", "Tamu " + terpilih.getNamaPelanggan() + " berhasil checkout. Kamar " + terpilih.getNomorKamar() + " sekarang Available.");
        muatData();
    }

    private void tampilkanPeringatan(String judul, String isi) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(judul);
        alert.setHeaderText(null);
        alert.setContentText(isi);
        alert.showAndWait();
    }

    public void tampilkan() {
        Scene adegan = new Scene(tampilan.getAkar(), 1100, 750);
        panggung.setScene(adegan);
        panggung.setTitle("Checkout Customer - CozyTheHotel");
        panggung.setMaximized(true);
        panggung.show();
    }
}

