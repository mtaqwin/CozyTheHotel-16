package com.cozythehotel.database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class CustomerDAO {
    
    public int tambahPelanggan(String nama, String telepon) {
        String kueri = "INSERT INTO customers (name, phone) VALUES (?, ?)";
        try (Connection koneksi = DBConnection.getConnection();
             PreparedStatement pernyataan = koneksi.prepareStatement(kueri, Statement.RETURN_GENERATED_KEYS)) {
            pernyataan.setString(1, nama);
            pernyataan.setString(2, telepon);
            pernyataan.executeUpdate();
            ResultSet hasil = pernyataan.getGeneratedKeys();
            if (hasil.next()) return hasil.getInt(1);
        } catch (SQLException galat) {
            galat.printStackTrace();
        }
        return -1;
    }

    // TAMBAHAN BARU: Hitung pelanggan aktif
    public int getJumlahPelangganAktif() {
        String kueri = "SELECT COUNT(DISTINCT customer_id) FROM reservations JOIN rooms ON reservations.room_id = rooms.id WHERE rooms.status = 'Occupied'";
        try (Connection koneksi = DBConnection.getConnection();
             Statement pernyataan = koneksi.createStatement();
             ResultSet hasil = pernyataan.executeQuery(kueri)) {
            if (hasil.next()) return hasil.getInt(1);
        } catch (SQLException galat) {
            galat.printStackTrace();
        }
        return 0;
    }

    // TAMBAHAN BARU: Hitung tamu aktif sesuai tipe kamar
    public int getJumlahTamuAktifBerdasarkanTanggal(String tanggal) {
        String kueri = "SELECT SUM(CASE " +
                       "    WHEN rooms.room_type = 'Standard' THEN 1 " +
                       "    WHEN rooms.room_type = 'Deluxe' THEN 2 " +
                       "    WHEN rooms.room_type = 'Suite' THEN 4 " +
                       "    ELSE 0 END) " +
                       "FROM reservations " +
                       "JOIN rooms ON reservations.room_id = rooms.id " +
                       "WHERE reservations.check_in <= ? AND reservations.check_out >= ? AND reservations.status = 'Belum Checkout'";
        try (Connection koneksi = DBConnection.getConnection();
             PreparedStatement pernyataan = koneksi.prepareStatement(kueri)) {
            pernyataan.setString(1, tanggal);
            pernyataan.setString(2, tanggal);
            ResultSet hasil = pernyataan.executeQuery();
            if (hasil.next()) return hasil.getInt(1);
        } catch (SQLException galat) {
            galat.printStackTrace();
        }
        return 0;
    }
}