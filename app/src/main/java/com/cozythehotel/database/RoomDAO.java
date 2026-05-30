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