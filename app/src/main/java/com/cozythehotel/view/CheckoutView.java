package com.cozythehotel.view;

import com.cozythehotel.model.Reservation;
import javafx.collections.FXCollections;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.control.cell.ComboBoxTableCell;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.effect.DropShadow;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import java.util.List;

public class CheckoutView {

    private VBox akar;
    private TableView<Reservation> tabel;
    private DatePicker pemilihTanggal;
    private Button tombolKembali;

    public CheckoutView() {
        siapkanUI();
    }

    private void siapkanUI() {
        akar = new VBox();
        akar.setStyle("-fx-background-color: #eee8de;");

        VBox headerContainer = new VBox();
        HBox topHeader = new HBox(20);
        topHeader.setStyle("-fx-background-color: #062145; -fx-padding: 15 35;");
        topHeader.setAlignment(Pos.CENTER_LEFT);

        Label lblJudul = new Label("Sistem CHECK-OUT Tamu");
        lblJudul.setStyle("-fx-text-fill: #deb653; -fx-font-family: 'Georgia', serif; -fx-font-size: 28px;");

        Region spasi = new Region();
        HBox.setHgrow(spasi, Priority.ALWAYS);

        Label lblTgl = new Label("Lihat Tanggal");
        lblTgl.setStyle("-fx-text-fill: white; -fx-font-family: 'Segoe UI', sans-serif; -fx-font-size: 16px;");

        pemilihTanggal = new DatePicker(java.time.LocalDate.now());
        pemilihTanggal.setPrefWidth(220);
        pemilihTanggal.setStyle("-fx-background-color: white; -fx-background-radius: 8; -fx-border-radius: 8;");

        topHeader.getChildren().addAll(lblJudul, spasi, lblTgl, pemilihTanggal);

        Region garisEmas = new Region();
        garisEmas.setStyle("-fx-background-color: #deb653;");
        garisEmas.setMinHeight(5);
        headerContainer.getChildren().addAll(topHeader, garisEmas);

        VBox kontainerKonten = new VBox();
        kontainerKonten.setPadding(new Insets(30, 40, 20, 40));
        VBox.setVgrow(kontainerKonten, Priority.ALWAYS);

        tabel = new TableView<>();
        tabel.setEditable(true);
        tabel.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        tabel.setStyle("-fx-background-color: white; -fx-background-radius: 15; -fx-border-color: transparent; -fx-padding: 10; -fx-font-family: 'Segoe UI';");

        TableColumn<Reservation, String> colNama = new TableColumn<>("NAMA TAMU");
        colNama.setCellValueFactory(new PropertyValueFactory<>("namaPelanggan"));
        colNama.setStyle("-fx-alignment: CENTER-LEFT; -fx-padding: 0 0 0 20;");

        TableColumn<Reservation, String> colNo = new TableColumn<>("ROOM ID");
        colNo.setCellValueFactory(new PropertyValueFactory<>("nomorKamar"));
        colNo.setStyle("-fx-alignment: CENTER;");

        TableColumn<Reservation, String> colTipe = new TableColumn<>("TIPE");
        colTipe.setCellValueFactory(new PropertyValueFactory<>("tipeKamar"));
        colTipe.setStyle("-fx-alignment: CENTER;");

        TableColumn<Reservation, String> colStatus = new TableColumn<>("AKSI STATUS");
        colStatus.setCellValueFactory(new PropertyValueFactory<>("status"));
        colStatus.setCellFactory(kolom -> {
            ComboBoxTableCell<Reservation, String> sel = (ComboBoxTableCell<Reservation, String>) ComboBoxTableCell.<Reservation, String>forTableColumn(
                FXCollections.observableArrayList("Belum CHECK-OUT", "Sudah CHECK-OUT")
            ).call(kolom);
            
            sel.itemProperty().addListener((obs, lama, baru) -> {
                if (baru != null) {
                    if (baru.equals("Sudah CHECK-OUT")) {
                        sel.setStyle("-fx-text-fill: #27ae60; -fx-font-weight: bold; -fx-alignment: CENTER;");
                    } else {
                        sel.setStyle("-fx-text-fill: #e67e22; -fx-font-weight: bold; -fx-alignment: CENTER;");
                    }
                }
            });
            return sel;
        });
        colStatus.setMinWidth(180);

        tabel.getColumns().addAll(colNama, colNo, colTipe, colStatus);
        VBox.setVgrow(tabel, Priority.ALWAYS);

        VBox kartuPutih = new VBox(tabel);
        kartuPutih.setStyle("-fx-background-color: white; -fx-background-radius: 15; -fx-padding: 10;");
        VBox.setVgrow(kartuPutih, Priority.ALWAYS);
        
        DropShadow bayangan = new DropShadow();
        bayangan.setColor(Color.rgb(0, 0, 0, 0.05));
        bayangan.setRadius(15);
        bayangan.setOffsetY(5);
        kartuPutih.setEffect(bayangan);

        kontainerKonten.getChildren().add(kartuPutih);

        HBox kotakFooter = new HBox();
        kotakFooter.setAlignment(Pos.CENTER_RIGHT);
        kotakFooter.setPadding(new Insets(0, 40, 30, 0));

        tombolKembali = new Button("Kembali Ke Dashboard");
        tombolKembali.setStyle("-fx-background-color: #062145; -fx-text-fill: white; -fx-font-family: 'Segoe UI', sans-serif; -fx-font-weight: bold; -fx-font-size: 13px; -fx-background-radius: 8; -fx-padding: 10 20; -fx-cursor: hand;");
        kotakFooter.getChildren().add(tombolKembali);

        akar.getChildren().addAll(headerContainer, kontainerKonten, kotakFooter);
    }

    public void setReservasi(List<Reservation> daftar) {
        tabel.getItems().setAll(daftar);
    }

    public TableView<Reservation> getTabel() { return tabel; }
    public VBox getAkar() { return akar; }
    public Button getTombolKembali() { return tombolKembali; }
    public DatePicker getPemilihTanggal() { return pemilihTanggal; }
}
