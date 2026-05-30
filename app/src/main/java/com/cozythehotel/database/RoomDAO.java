package com.cozythehotel.database;

import com.cozythehotel.model.Room;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class RoomDAO {
    public List<Room> ambilSemuaKamar() {
        List<Room> daftar = new ArrayList<>();
        String kueri = "SELECT * FROM rooms";
        try (Connection koneksi = DBConnection.getConnection();
             Statement pernyataan = koneksi.createStatement();
             ResultSet hasil = pernyataan.executeQuery(kueri)) {
            while (hasil.next()) {
                daftar.add(new Room(
                        hasil.getInt("id"),
                        hasil.getString("room_number"),
                        hasil.getString("room_type"),
                        hasil.getString("status"),
                        hasil.getString("customer_name")
                ));
            }
        } catch (SQLException galat) {
            galat.printStackTrace();
        }
        return daftar;
    }

    public List<Room> getStatusKamarBerdasarkanTanggal(String tanggal) {
        List<Room> daftar = new ArrayList<>();
        String kueri = "SELECT r.*, res.customer_name as tamu_saat_ini " +
                       "FROM rooms r " +
                       "LEFT JOIN (SELECT reservations.*, customers.name as customer_name " +
                       "           FROM reservations " +
                       "           JOIN customers ON reservations.customer_id = customers.id " +
                       "           WHERE check_in <= ? AND check_out >= ? AND status = 'Belum Checkout') res " +
                       "ON r.id = res.room_id";
        try (Connection koneksi = DBConnection.getConnection();
             PreparedStatement pernyataan = koneksi.prepareStatement(kueri)) {
            pernyataan.setString(1, tanggal);
            pernyataan.setString(2, tanggal);
            ResultSet hasil = pernyataan.executeQuery();
            while (hasil.next()) {
                String tamu = hasil.getString("tamu_saat_ini");
                daftar.add(new Room(
                        hasil.getInt("id"),
                        hasil.getString("room_number"),
                        hasil.getString("room_type"),
                        tamu != null ? "Occupied" : "Available",
                        tamu
                ));
            }
        } catch (SQLException galat) {
            galat.printStackTrace();
        }
        return daftar;
    }

    public int getJumlahTerisiBerdasarkanTanggal(String tanggal) {
        String kueri = "SELECT COUNT(DISTINCT room_id) FROM reservations WHERE check_in <= ? AND check_out >= ? AND status = 'Belum Checkout'";
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

    public int getJumlahTotalKamar() {
        String kueri = "SELECT COUNT(*) FROM rooms";
        try (Connection koneksi = DBConnection.getConnection();
             Statement pernyataan = koneksi.createStatement();
             ResultSet hasil = pernyataan.executeQuery(kueri)) {
            if (hasil.next()) return hasil.getInt(1);
        } catch (SQLException galat) {
            galat.printStackTrace();
        }
        return 0;
    }

    public void perbaruiStatusKamar(int id, String status, String namaPelanggan) {
        String kueri = "UPDATE rooms SET status = ?, customer_name = ? WHERE id = ?";
        try (Connection koneksi = DBConnection.getConnection();
             PreparedStatement pernyataan = koneksi.prepareStatement(kueri)) {
            pernyataan.setString(1, status);
            pernyataan.setString(2, namaPelanggan);
            pernyataan.setInt(3, id);
            pernyataan.executeUpdate();
        } catch (SQLException galat) {
            galat.printStackTrace();
        }
    }

    public int getJumlahBerdasarkanStatus(String status) {
        String kueri = "SELECT COUNT(*) FROM rooms WHERE status = ?";
        try (Connection koneksi = DBConnection.getConnection();
             PreparedStatement pernyataan = koneksi.prepareStatement(kueri)) {
            pernyataan.setString(1, status);
            ResultSet hasil = pernyataan.executeQuery();
            if (hasil.next()) return hasil.getInt(1);
        } catch (SQLException galat) {
            galat.printStackTrace();
        }
        return 0;
    }
}