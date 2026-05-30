package com.cozythehotel.view;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.effect.DropShadow;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

public class ReservationView {

    private VBox akar;
    private TextField txtNama, txtTelepon;
    private DatePicker dpMasuk, dpKeluar;
    private ComboBox<String> cbTipe, cbNomorKamar, cbPembayaran;
    private Button tombolSimpan, tombolBatal;

    public ReservationView() {
        siapkanUI();
    }

    private void siapkanUI() {
        akar = new VBox();
        akar.setStyle("-fx-background-color: #f2e9dc;");

        HBox header = new HBox();
        header.setStyle("-fx-background-color: #072247; -fx-border-color: #d4af37; -fx-border-width: 0 0 4 0; -fx-padding: 20 40;");
        
        Label lblJudul = new Label("Reservasi Customer");
        lblJudul.setStyle("-fx-text-fill: #e5b94c; -fx-font-family: 'Georgia', serif; -fx-font-size: 28px;");
        header.getChildren().add(lblJudul);

        VBox kartuForm = new VBox();
        kartuForm.setStyle("-fx-background-color: white; -fx-background-radius: 15; -fx-padding: 40;");
        
        DropShadow bayangan = new DropShadow();
        bayangan.setColor(Color.rgb(0, 0, 0, 0.05));
        bayangan.setRadius(15);
        bayangan.setOffsetY(5);
        kartuForm.setEffect(bayangan);
        
        VBox.setMargin(kartuForm, new Insets(40, 60, 40, 60));

        GridPane grid = new GridPane();
        grid.setVgap(15);
        grid.setHgap(30);
        grid.setAlignment(Pos.CENTER);

        ColumnConstraints col1 = new ColumnConstraints();
        col1.setPrefWidth(130);
        ColumnConstraints col2 = new ColumnConstraints();
        col2.setHgrow(Priority.ALWAYS);
        col2.setPrefWidth(450);
        grid.getColumnConstraints().addAll(col1, col2);

        txtNama = new TextField();
        txtTelepon = new TextField();
        dpMasuk = new DatePicker();
        dpKeluar = new DatePicker();
        cbTipe = new ComboBox<>();
        cbNomorKamar = new ComboBox<>();
        cbPembayaran = new ComboBox<>();
        
        cbTipe.getItems().addAll("Standard", "Deluxe", "Suite");
        cbPembayaran.getItems().addAll("Transfer", "Virtual Account");

        tombolBatal = new Button("Batal");
        tombolSimpan = new Button("Simpan Reservasi");

        String gayaInput = "-fx-background-color: white; -fx-border-color: #d1dce6; -fx-border-radius: 5; -fx-background-radius: 5; -fx-padding: 8; -fx-font-size: 14px;";
        String gayaLabel = "-fx-text-fill: #5b708b; -fx-font-size: 14px;";

        tambahBarisForm(grid, "Nama Tamu", txtNama, gayaLabel, gayaInput, 0);
        tambahBarisForm(grid, "Nomor Telepon", txtTelepon, gayaLabel, gayaInput, 1);
        tambahBarisForm(grid, "Tanggal Masuk", dpMasuk, gayaLabel, gayaInput, 2);
        tambahBarisForm(grid, "Tanggal Keluar", dpKeluar, gayaLabel, gayaInput, 3);
        tambahBarisForm(grid, "Tipe Kamar", cbTipe, gayaLabel, gayaInput, 4);
        tambahBarisForm(grid, "Nomor Kamar", cbNomorKamar, gayaLabel, gayaInput, 5);
        tambahBarisForm(grid, "Pembayaran", cbPembayaran, gayaLabel, gayaInput, 6);

        HBox kotakTombol = new HBox(15);
        kotakTombol.setAlignment(Pos.CENTER);
        kotakTombol.setPadding(new Insets(30, 0, 0, 0));

        tombolBatal.setStyle("-fx-background-color: white; -fx-text-fill: #072247; -fx-border-color: #d1dce6; -fx-border-radius: 8; -fx-background-radius: 8; -fx-padding: 10 30; -fx-font-weight: bold; -fx-cursor: hand;");
        tombolSimpan.setStyle("-fx-background-color: #072247; -fx-text-fill: white; -fx-background-radius: 8; -fx-padding: 10 30; -fx-font-weight: bold; -fx-cursor: hand;");

        kotakTombol.getChildren().addAll(tombolBatal, tombolSimpan);
        kartuForm.getChildren().addAll(grid, kotakTombol);
        akar.getChildren().addAll(header, kartuForm);
    }

    private void tambahBarisForm(GridPane grid, String label, javafx.scene.control.Control kontrol, String gayaL, String gayaI, int baris) {
        Label lbl = new Label(label);
        lbl.setStyle(gayaL);
        kontrol.setStyle(gayaI);
        kontrol.setMaxWidth(Double.MAX_VALUE);
        grid.add(lbl, 0, baris);
        grid.add(kontrol, 1, baris);
    }

    public VBox getAkar() { return akar; }
    public TextField getTxtNama() { return txtNama; }
    public TextField getTxtTelepon() { return txtTelepon; }
    public DatePicker getDpMasuk() { return dpMasuk; }
    public DatePicker getDpKeluar() { return dpKeluar; }
    public ComboBox<String> getCbTipe() { return cbTipe; }
    public ComboBox<String> getCbNomorKamar() { return cbNomorKamar; }
    public ComboBox<String> getCbPembayaran() { return cbPembayaran; }
    public Button getTombolSimpan() { return tombolSimpan; }
    public Button getTombolBatal() { return tombolBatal; }
}
