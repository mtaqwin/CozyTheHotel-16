package com.cozythehotel.database;

import com.cozythehotel.model.Reservation;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ReservationDAO {
    public void tambahReservasi(int idPelanggan, int idKamar, String masuk, String keluar, String metodePembayaran) {
        String kueri = "INSERT INTO reservations (customer_id, room_id, check_in, check_out, payment_method, status) VALUES (?, ?, ?, ?, ?, ?)";
        try (Connection koneksi = DBConnection.getConnection();
             PreparedStatement pernyataan = koneksi.prepareStatement(kueri)) {
            pernyataan.setInt(1, idPelanggan);
            pernyataan.setInt(2, idKamar);
            pernyataan.setString(3, masuk);
            pernyataan.setString(4, keluar);
            pernyataan.setString(5, metodePembayaran);
            pernyataan.setString(6, "Belum Checkout");
            pernyataan.executeUpdate();
        } catch (SQLException galat) {
            galat.printStackTrace();
        }
    }
}