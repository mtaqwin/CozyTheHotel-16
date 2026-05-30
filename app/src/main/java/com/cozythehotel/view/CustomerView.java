package com.cozythehotel.view;

import java.util.List;

import com.cozythehotel.model.Reservation;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.effect.DropShadow;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

public class CustomerView {

    private VBox akar;
    private TableView<Reservation> tabel;
    private DatePicker pemilihTanggal;
    private Button tombolKembali;

    public CustomerView() {
        siapkanUI();
    }

    private void siapkanUI() {
        akar = new VBox();
        akar.setStyle("-fx-background-color: #ede7dc;");

        VBox headerContainer = new VBox();
        HBox topHeader = new HBox(20);
        topHeader.setStyle("-fx-background-color: #041f42; -fx-padding: 15 40;");
        topHeader.setAlignment(Pos.CENTER_LEFT);

        Label lblJudul = new Label("Basis Data Tamu Hotel");
        lblJudul.setStyle("-fx-text-fill: #dfb448; -fx-font-family: 'Georgia', serif; -fx-font-size: 28px;");

        Region spasi = new Region();
        HBox.setHgrow(spasi, Priority.ALWAYS);

        Label lblTgl = new Label("Lihat Tanggal");
        lblTgl.setStyle("-fx-text-fill: white; -fx-font-family: 'Segoe UI', sans-serif; -fx-font-size: 16px;");

        pemilihTanggal = new DatePicker(java.time.LocalDate.now());
        pemilihTanggal.setPrefWidth(250);
        pemilihTanggal.setPrefHeight(35);
        pemilihTanggal.setStyle("-fx-background-color: white; -fx-background-radius: 8; -fx-border-radius: 8;");

        topHeader.getChildren().addAll(lblJudul, spasi, lblTgl, pemilihTanggal);

        Region garisEmas = new Region();
        garisEmas.setStyle("-fx-background-color: #dfb448;");
        garisEmas.setMinHeight(5);
        headerContainer.getChildren().addAll(topHeader, garisEmas);

        tabel = new TableView<>();
        tabel.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        tabel.setStyle("-fx-background-color: white; -fx-background-radius: 15; -fx-border-color: transparent; -fx-padding: 10; -fx-font-family: 'Segoe UI';");

        TableColumn<Reservation, String> colNama = new TableColumn<>("NAMA TAMU");
        colNama.setCellValueFactory(new PropertyValueFactory<>("namaPelanggan"));
        colNama.setStyle("-fx-alignment: CENTER-LEFT; -fx-padding: 0 0 0 20;");

        TableColumn<Reservation, String> colTelp = new TableColumn<>("TELEPON");
        colTelp.setCellValueFactory(new PropertyValueFactory<>("teleponPelanggan"));
        colTelp.setStyle("-fx-alignment: CENTER;");

        TableColumn<Reservation, String> colMasuk = new TableColumn<>("CHECK-IN");
        colMasuk.setCellValueFactory(new PropertyValueFactory<>("masuk"));
        colMasuk.setStyle("-fx-alignment: CENTER;");

        TableColumn<Reservation, String> colKeluar = new TableColumn<>("CHECK-OUT");
        colKeluar.setCellValueFactory(new PropertyValueFactory<>("keluar"));
        colKeluar.setStyle("-fx-alignment: CENTER;");

        TableColumn<Reservation, String> colTipe = new TableColumn<>("TIPE");
        colTipe.setCellValueFactory(new PropertyValueFactory<>("tipeKamar"));
        colTipe.setStyle("-fx-alignment: CENTER;");

        TableColumn<Reservation, String> colNo = new TableColumn<>("ROOM ID");
        colNo.setCellValueFactory(new PropertyValueFactory<>("nomorKamar"));
        colNo.setStyle("-fx-alignment: CENTER;");

        TableColumn<Reservation, String> colBayar = new TableColumn<>("PEMBAYARAN");
        colBayar.setCellValueFactory(new PropertyValueFactory<>("metodePembayaran"));
        colBayar.setStyle("-fx-alignment: CENTER;");

        tabel.getColumns().addAll(colNama, colTelp, colMasuk, colKeluar, colTipe, colNo, colBayar);
        VBox.setVgrow(tabel, Priority.ALWAYS);

        VBox kartuPutih = new VBox(tabel);
        kartuPutih.setStyle("-fx-background-color: white; -fx-background-radius: 15; -fx-padding: 10;");
        VBox.setVgrow(kartuPutih, Priority.ALWAYS);
        VBox.setMargin(kartuPutih, new Insets(30, 40, 20, 40)); 
        
        DropShadow bayangan = new DropShadow();
        bayangan.setColor(Color.rgb(0, 0, 0, 0.05));
        bayangan.setRadius(15);
        bayangan.setOffsetY(5);
        kartuPutih.setEffect(bayangan);

        HBox kotakFooter = new HBox();
        kotakFooter.setAlignment(Pos.CENTER_RIGHT);
        kotakFooter.setPadding(new Insets(0, 40, 30, 0));

        tombolKembali = new Button("Kembali Ke Dashboard");
        tombolKembali.setStyle("-fx-background-color: #041f42; -fx-text-fill: white; -fx-font-family: 'Segoe UI', sans-serif; -fx-font-weight: 800; -fx-font-size: 13px; -fx-background-radius: 10; -fx-padding: 10 25; -fx-cursor: hand;");
        kotakFooter.getChildren().add(tombolKembali);

        akar.getChildren().addAll(headerContainer, kartuPutih, kotakFooter);
    }

    public void setPelanggan(List<Reservation> daftar) {
        tabel.getItems().setAll(daftar);
    }

    public VBox getAkar() { return akar; }
    public Button getTombolKembali() { return tombolKembali; }
    public DatePicker getPemilihTanggal() { return pemilihTanggal; }
}
