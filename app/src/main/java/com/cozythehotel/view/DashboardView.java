package com.cozythehotel.view;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.effect.DropShadow;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.TextAlignment;

public class DashboardView {

    private VBox akar;
    private Button tombolTambahReservasi, tombolKelolaKamar, tombolDataPelanggan, tombolCheckout;
    private Label labelTersedia, labelTerisi, labelTamuAktif;
    private DatePicker pemilihTanggal;

    public DashboardView() {
        siapkanUI();
    }

    private void siapkanUI() {
        String sansSerif = "-fx-font-family: 'Segoe UI', Arial, sans-serif; ";

        akar = new VBox();
        akar.setStyle("-fx-background-color: #f3ece4;"); 
        
        HBox tataLetakUtama = new HBox();
        VBox.setVgrow(tataLetakUtama, Priority.ALWAYS);

        // --- SIDEBAR KIRI ---
        VBox sidebar = new VBox();
        sidebar.setPrefWidth(260); 
        sidebar.setMinWidth(260);
        sidebar.setStyle("-fx-background-color: #0c2340; -fx-padding: 30 20;");

        Label lblLogo = new Label("Cozy The Hotel");
        lblLogo.setStyle("-fx-text-fill: #e0b85a; -fx-font-family: 'Georgia', serif; -fx-font-size: 26px; -fx-font-weight: bold;");
        Label lblSubtitle = new Label("Staff System");
        lblSubtitle.setStyle("-fx-text-fill: white; " + sansSerif + "-fx-font-size: 15px;");
        
        VBox logoBox = new VBox(5, lblLogo, lblSubtitle);
        logoBox.setAlignment(Pos.CENTER);

        Region pemisah = new Region();
        pemisah.setStyle("-fx-background-color: #e0b85a;");
        pemisah.setMinHeight(4);
        pemisah.setMaxWidth(200);
        VBox.setMargin(pemisah, new Insets(15, 0, 30, 0));

        Label lblSummaryHeader = new Label("RINGKASAN");
        lblSummaryHeader.setStyle("-fx-text-fill: #8198ae; " + sansSerif + "-fx-font-weight: 900; -fx-font-size: 18px;");
        VBox.setMargin(lblSummaryHeader, new Insets(0, 0, 15, 0));

        labelTersedia = new Label("0");
        labelTerisi = new Label("0");
        labelTamuAktif = new Label("0");

        sidebar.getChildren().addAll(
            logoBox, pemisah, lblSummaryHeader,
            buatBlokStatistik("Kamar Tersedia", labelTersedia, "#1cc867"),
            buatBlokStatistik("Kamar Terisi", labelTerisi, "#e32636"),
            buatBlokStatistik("Tamu Aktif", labelTamuAktif, "#3a9bf8")
        );

        // --- KONTEN UTAMA ---
        VBox konten = new VBox(25);
        HBox.setHgrow(konten, Priority.ALWAYS);
        konten.setStyle("-fx-background-color: #f3ece4; -fx-padding: 35 45;");

        HBox kotakHeader = new HBox(15);
        kotakHeader.setAlignment(Pos.CENTER_LEFT);
        
        Label lblJudulHalaman = new Label("Akses Statistik Tanggal");
        lblJudulHalaman.setStyle("-fx-text-fill: #0c2340; " + sansSerif + "-fx-font-weight: 900; -fx-font-size: 24px;");
        
        Region spasi = new Region();
        HBox.setHgrow(spasi, Priority.ALWAYS);
        
        Label lblPilih = new Label("Pilih Tanggal");
        lblPilih.setStyle("-fx-text-fill: #44566c; " + sansSerif + "-fx-font-size: 16px;");
        
        pemilihTanggal = new DatePicker(java.time.LocalDate.now()); 
        pemilihTanggal.setPrefWidth(180);
        pemilihTanggal.setPrefHeight(35);
        pemilihTanggal.setStyle("-fx-background-color: white; -fx-background-radius: 5; -fx-border-color: #d1e2f3; -fx-border-radius: 5; -fx-border-width: 2;");
        
        kotakHeader.getChildren().addAll(lblJudulHalaman, spasi, lblPilih, pemilihTanggal);

        VBox kartuInfo = new VBox(12);
        kartuInfo.setStyle("-fx-background-color: white; -fx-background-radius: 12; -fx-padding: 25 30;");
        berikanBayangan(kartuInfo);

        Label lblWelcome = new Label("Selamat Datang di Dashboard Staff!");
        lblWelcome.setStyle(sansSerif + "-fx-font-weight: 800; -fx-font-size: 18px; -fx-text-fill: #000000;");
        
        Label txtDeskripsi = new Label(
            "Halaman ini memudahkan Anda memantau ringkasan status kamar pada panel kiri, meliputi jumlah kamar yang tersedia, sedang ditempati, dan jumlah tamu aktif. Gunakan menu cepat di bawah untuk manajemen operasional hotel."
        );
        txtDeskripsi.setWrapText(true);
        txtDeskripsi.setMaxWidth(800);
        txtDeskripsi.setStyle(sansSerif + "-fx-text-fill: #5b708b; -fx-font-size: 14px; -fx-line-spacing: 5;");
        
        kartuInfo.getChildren().addAll(lblWelcome, txtDeskripsi);

        HBox barisAksi = new HBox(20);
        barisAksi.setAlignment(Pos.CENTER_LEFT);
        
        tombolTambahReservasi = buatTombolAksi("🏨", "TAMBAH\nRESERVASI", "DAFTAR TAMU BARU");
        tombolKelolaKamar = buatTombolAksi("🔑", "MANAJEMEN\nKAMAR", "PANTAU OKUPANSI");
        tombolDataPelanggan = buatTombolAksi("👥", "DATA\nCUSTOMER", "RIWAYAT TAMU");
        tombolCheckout = buatTombolAksi("💳", "CHECK OUT\nCUSTOMER", "PROSES KELUAR");

        barisAksi.getChildren().addAll(tombolTambahReservasi, tombolKelolaKamar, tombolDataPelanggan, tombolCheckout);

        konten.getChildren().addAll(kotakHeader, kartuInfo, barisAksi);

        tataLetakUtama.getChildren().addAll(sidebar, konten);
        akar.getChildren().add(tataLetakUtama);
    }

    private VBox buatBlokStatistik(String judul, Label labelNilai, String warnaNilai) {
        VBox kotak = new VBox(2);
        Label lblT = new Label(judul);
        lblT.setStyle("-fx-text-fill: white; -fx-font-family: 'Segoe UI'; -fx-font-weight: bold; -fx-font-size: 15px;");
        
        labelNilai.setStyle("-fx-text-fill: " + warnaNilai + "; -fx-font-family: 'Segoe UI'; -fx-font-weight: 900; -fx-font-size: 28px;");
        
        kotak.getChildren().addAll(lblT, labelNilai);
        VBox.setMargin(kotak, new Insets(0, 0, 20, 0));
        return kotak;
    }

    private Button buatTombolAksi(String ikonStr, String judul, String subjudul) {
        VBox kartu = new VBox(10);
        kartu.setAlignment(Pos.CENTER);
        kartu.setPadding(new Insets(20));
        kartu.setPrefSize(175, 190);
        kartu.setStyle("-fx-background-color: white; -fx-background-radius: 15; -fx-cursor: hand;");
        berikanBayangan(kartu);

        Label ikon = new Label(ikonStr);
        ikon.setStyle("-fx-font-size: 36px;");

        Label lblJ = new Label(judul);
        lblJ.setStyle("-fx-font-weight: 900; -fx-font-size: 13px; -fx-text-fill: #0c2340;");
        lblJ.setTextAlignment(TextAlignment.CENTER);

        Label lblS = new Label(subjudul);
        lblS.setStyle("-fx-font-size: 10px; -fx-text-fill: #7f8c8d;");
        lblS.setTextAlignment(TextAlignment.CENTER);

        kartu.getChildren().addAll(ikon, lblJ, lblS);

        Button btn = new Button();
        btn.setGraphic(kartu);
        btn.setStyle("-fx-background-color: transparent; -fx-padding: 0; -fx-cursor: hand;");
        
        btn.setOnMouseEntered(e -> {
            kartu.setStyle("-fx-background-color: #f8fbff; -fx-background-radius: 15; -fx-border-color: #e0b85a; -fx-border-radius: 15; -fx-border-width: 2;");
            kartu.setTranslateY(-8);
        });
        btn.setOnMouseExited(e -> {
            kartu.setStyle("-fx-background-color: white; -fx-background-radius: 15; -fx-border-color: transparent;");
            kartu.setTranslateY(0);
        });

        return btn;
    }

    private void berikanBayangan(Region wilayah) {
        DropShadow bayangan = new DropShadow();
        bayangan.setColor(Color.rgb(0, 0, 0, 0.1));
        bayangan.setRadius(15);
        bayangan.setOffsetY(5);
        wilayah.setEffect(bayangan);
    }

    public VBox getAkar() { return akar; }
    public Button getTombolTambahReservasi() { return tombolTambahReservasi; }
    public Button getTombolKelolaKamar() { return tombolKelolaKamar; }
    public Button getTombolDataPelanggan() { return tombolDataPelanggan; }
    public Button getTombolCheckout() { return tombolCheckout; }
    public DatePicker getPemilihTanggal() { return pemilihTanggal; }
    
    public void setStatistik(int tersedia, int terisi, int aktif) {
        labelTersedia.setText(String.valueOf(tersedia));
        labelTerisi.setText(String.valueOf(terisi));
        labelTamuAktif.setText(String.valueOf(aktif));
    }
}
