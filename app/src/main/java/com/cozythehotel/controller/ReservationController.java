package com.cozythehotel.controller;

import com.cozythehotel.database.CustomerDAO;
import com.cozythehotel.database.ReservationDAO;
import com.cozythehotel.database.RoomDAO;
import com.cozythehotel.model.*;
import com.cozythehotel.view.ReservationView;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.stage.Stage;
import java.util.List;
import java.util.stream.Collectors;

public class ReservationController {
    private Stage panggung;
    private ReservationView tampilan;
    private RoomDAO daoKamar;
    private CustomerDAO daoPelanggan;
    private ReservationDAO daoReservasi;

    public ReservationController(Stage panggung) {
        this.panggung = panggung;
        this.tampilan = new ReservationView();
        this.daoKamar = new RoomDAO();
        this.daoPelanggan = new CustomerDAO();
        this.daoReservasi = new ReservationDAO();
        
        inisialisasiHandler();
    }

    private void inisialisasiHandler() {
        tampilan.getTombolBatal().setOnAction(e -> new DashboardController(panggung).tampilkan());

        tampilan.getCbTipe().getSelectionModel().selectedItemProperty().addListener((obs, lama, baru) -> {
            if (baru != null) {
                List<String> daftarNomor = daoKamar.ambilSemuaKamar().stream()
                        .filter(k -> k.getTipeKamar().equals(baru) && k.getStatus().equals("Available"))
                        .map(Room::getNomorKamar)
                        .collect(Collectors.toList());
                tampilan.getCbNomorKamar().getItems().setAll(daftarNomor);
            }
        });

        tampilan.getTombolSimpan().setOnAction(e -> simpanReservasi());
    }

    private void simpanReservasi() {
        String nama = tampilan.getTxtNama().getText();
        String telepon = tampilan.getTxtTelepon().getText();
        String masuk = tampilan.getDpMasuk().getValue() != null ? tampilan.getDpMasuk().getValue().toString() : "";
        String keluar = tampilan.getDpKeluar().getValue() != null ? tampilan.getDpKeluar().getValue().toString() : "";
        String tipe = tampilan.getCbTipe().getValue();
        String nomorKamar = tampilan.getCbNomorKamar().getValue();
        String metodePembayaran = tampilan.getCbPembayaran().getValue();

        if (nama.isEmpty() || telepon.isEmpty() || masuk.isEmpty() || keluar.isEmpty() || nomorKamar == null || metodePembayaran == null) {
            tampilkanPeringatan("Error", "Mohon lengkapi semua field!");
            return;
        }

        Payment pembayaran;
        if (metodePembayaran.equals("Transfer")) {
            pembayaran = new TransferPayment(0);
        } else {
            pembayaran = new VirtualAccountPayment(0);
        }

        Room kamar = daoKamar.ambilSemuaKamar().stream()
                .filter(k -> k.getNomorKamar().equals(nomorKamar))
                .findFirst().orElse(null);

        if (kamar != null) {
            int idPelanggan = daoPelanggan.tambahPelanggan(nama, telepon);
            daoReservasi.tambahReservasi(idPelanggan, kamar.getId(), masuk, keluar, metodePembayaran);
            daoKamar.perbaruiStatusKamar(kamar.getId(), "Occupied", nama);
            
            tampilkanPeringatan("Success", "Reservasi berhasil disimpan!\n" + pembayaran.prosesPembayaran());
            new DashboardController(panggung).tampilkan();
        }
    }

    private void tampilkanPeringatan(String judul, String isi) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(judul);
        alert.setHeaderText(null);
        alert.setContentText(isi);
        alert.showAndWait();
    }

    public void tampilkan() {
        Scene adegan = new Scene(tampilan.getAkar(), 1000, 700);
        panggung.setScene(adegan);
        panggung.setTitle("Tambah Reservasi - CozyTheHotel");
        panggung.setMaximized(true);
        panggung.show();
    }
}
