package com.cozythehotel.view;

import com.cozythehotel.model.Room;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import java.util.List;

public class RoomView {

    private VBox akar;
    private GridPane gridStandard, gridDeluxe, gridSuite;
    private DatePicker pemilihTanggal;
    private Button tombolKembali;

    public RoomView() {
        siapkanUI();
    }

    private void siapkanUI() {
        akar = new VBox();
        akar.setStyle("-fx-background-color: #f4ebe1;");

        VBox headerContainer = new VBox();
        HBox topHeader = new HBox(20);
        topHeader.setStyle("-fx-background-color: #0c2340; -fx-padding: 15 40;");
        topHeader.setAlignment(Pos.CENTER_LEFT);

        Label lblJudul = new Label("Manajemen Status Kamar");
        lblJudul.setStyle("-fx-text-fill: #e0b85a; -fx-font-family: 'Georgia', serif; -fx-font-size: 26px;");

        Region spasi = new Region();
        HBox.setHgrow(spasi, Priority.ALWAYS);

        Label lblTgl = new Label("Lihat Tanggal");
        lblTgl.setStyle("-fx-text-fill: white; -fx-font-family: 'Segoe UI', sans-serif; -fx-font-size: 16px;");

        pemilihTanggal = new DatePicker(java.time.LocalDate.now());
        pemilihTanggal.setPrefWidth(200);
        pemilihTanggal.setStyle("-fx-background-color: white; -fx-background-radius: 8;");

        topHeader.getChildren().addAll(lblJudul, spasi, lblTgl, pemilihTanggal);

        Region garisEmas = new Region();
        garisEmas.setStyle("-fx-background-color: #e0b85a;");
        garisEmas.setMinHeight(5);
        headerContainer.getChildren().addAll(topHeader, garisEmas);

        ScrollPane scroll = new ScrollPane();
        scroll.setFitToWidth(true);
        scroll.setStyle("-fx-background-color: transparent; -fx-background: #f4ebe1; -fx-border-color: transparent;");
        VBox.setVgrow(scroll, Priority.ALWAYS);

        VBox contentBox = new VBox(40);
        contentBox.setStyle("-fx-padding: 40;");

        VBox standardSection = buatBagianTipe("STANDARD ROOM");
        gridStandard = (GridPane) standardSection.getChildren().get(1);

        VBox deluxeSection = buatBagianTipe("DELUXE ROOM");
        gridDeluxe = (GridPane) deluxeSection.getChildren().get(1);

        VBox suiteSection = buatBagianTipe("SUITE ROOM");
        gridSuite = (GridPane) suiteSection.getChildren().get(1);

        contentBox.getChildren().addAll(standardSection, deluxeSection, suiteSection);
        scroll.setContent(contentBox);

        HBox footer = new HBox();
        footer.setAlignment(Pos.CENTER);
        footer.setPadding(new Insets(20));
        tombolKembali = new Button("Kembali Dashboard");
        tombolKembali.setStyle("-fx-background-color: #0c2340; -fx-text-fill: white; -fx-font-weight: bold; -fx-background-radius: 10; -fx-cursor: hand;");
        tombolKembali.setPrefSize(250, 45);
        footer.getChildren().add(tombolKembali);

        akar.getChildren().addAll(headerContainer, scroll, footer);
    }

    private VBox buatBagianTipe(String judul) {
        VBox bagian = new VBox(20);
        Label lblKat = new Label(judul);
        lblKat.setStyle("-fx-text-fill: #1a2a3a; -fx-font-family: 'Segoe UI', sans-serif; -fx-font-weight: 900; -fx-font-size: 18px;");
        
        Region garisBawah = new Region();
        garisBawah.setStyle("-fx-background-color: #1a2a3a;");
        garisBawah.setMinHeight(3);
        garisBawah.setMaxWidth(160);
        
        GridPane grid = new GridPane();
        grid.setHgap(15);
        grid.setVgap(15);
        grid.setMaxWidth(Double.MAX_VALUE);
        for (int i = 0; i < 4; i++) {
            javafx.scene.layout.ColumnConstraints col = new javafx.scene.layout.ColumnConstraints();
            col.setPercentWidth(25);
            grid.getColumnConstraints().add(col);
        }
        
        bagian.getChildren().addAll(new VBox(5, lblKat, garisBawah), grid);
        return bagian;
    }

    public void tampilkanKamar(List<Room> daftarKamar) {
        gridStandard.getChildren().clear();
        gridDeluxe.getChildren().clear();
        gridSuite.getChildren().clear();

        int stdC = 0, dlxC = 0, steC = 0;
        for (Room kamar : daftarKamar) {
            boolean terisi = kamar.getStatus().equalsIgnoreCase("Occupied");
            VBox kartu = buatKartuKamar(kamar.getNomorKamar(), terisi ? "TERISI" : "TERSEDIA", terisi ? kamar.getNamaPelanggan() : "-");
            
            if (kamar.getTipeKamar().equalsIgnoreCase("Standard")) {
                gridStandard.add(kartu, stdC % 4, stdC / 4); stdC++;
            } else if (kamar.getTipeKamar().equalsIgnoreCase("Deluxe")) {
                gridDeluxe.add(kartu, dlxC % 4, dlxC / 4); dlxC++;
            } else if (kamar.getTipeKamar().equalsIgnoreCase("Suite")) {
                gridSuite.add(kartu, steC % 4, steC / 4); steC++;
            }
        }
    }

    private VBox buatKartuKamar(String idKamar, String status, String namaTamu) {
        VBox kartu = new VBox(2);
        kartu.setAlignment(Pos.CENTER);
        kartu.setMaxWidth(Double.MAX_VALUE);
        kartu.setPrefHeight(100);
        String warna = status.equals("TERISI") ? "#e9544c" : "#65ad60";
        kartu.setStyle("-fx-background-color: " + warna + "; -fx-background-radius: 12; -fx-border-color: white; -fx-border-width: 2; -fx-border-radius: 10; -fx-border-insets: 4; -fx-padding: 10;");

        Label lblId = new Label(idKamar);
        lblId.setStyle("-fx-text-fill: white; -fx-font-weight: bold; -fx-font-size: 15px;");
        Label lblSt = new Label(status);
        lblSt.setStyle("-fx-text-fill: white; -fx-font-weight: bold; -fx-font-size: 15px;");
        Region pemisah = new Region();
        pemisah.setStyle("-fx-background-color: white;");
        pemisah.setMinHeight(1.5);
        pemisah.setMaxWidth(100);
        Label lblTamu = new Label(namaTamu);
        lblTamu.setStyle("-fx-text-fill: white; -fx-font-size: 14px;");

        kartu.getChildren().addAll(lblId, lblSt, pemisah, lblTamu);
        return kartu;
    }

    public VBox getAkar() { return akar; }
    public Button getTombolKembali() { return tombolKembali; }
    public DatePicker getPemilihTanggal() { return pemilihTanggal; }
}

