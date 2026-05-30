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

    
    public List<Reservation> getReservasiAktif() {
        List<Reservation> daftar = new ArrayList<>();
        String kueri = "SELECT r.*, c.name, c.phone, rm.room_number, rm.room_type " +
                       "FROM reservations r " +
                       "JOIN customers c ON r.customer_id = c.id " +
                       "JOIN rooms rm ON r.room_id = rm.id " +
                       "WHERE r.status = 'Belum Checkout'";
        try (Connection koneksi = DBConnection.getConnection();
             Statement pernyataan = koneksi.createStatement();
             ResultSet hasil = pernyataan.executeQuery(kueri)) {
            while (hasil.next()) {
                Reservation res = new Reservation(
                        hasil.getInt("id"),
                        hasil.getInt("customer_id"),
                        hasil.getInt("room_id"),
                        hasil.getString("check_in"),
                        hasil.getString("check_out"),
                        hasil.getString("payment_method"),
                        hasil.getString("status")
                );
                res.setNamaPelanggan(hasil.getString("name"));
                res.setTeleponPelanggan(hasil.getString("phone"));
                res.setNomorKamar(hasil.getString("room_number"));
                res.setTipeKamar(hasil.getString("room_type"));
                daftar.add(res);
            }
        } catch (SQLException galat) {
            galat.printStackTrace();
        }
        return daftar;
    }

    public List<Reservation> getReservasiBerdasarkanTanggal(String tanggal) {
        List<Reservation> daftar = new ArrayList<>();
        String kueri = "SELECT r.*, c.name, c.phone, rm.room_number, rm.room_type " +
                       "FROM reservations r " +
                       "JOIN customers c ON r.customer_id = c.id " +
                       "JOIN rooms rm ON r.room_id = rm.id " +
                       "WHERE r.check_in <= ? AND r.check_out >= ?";
        try (Connection koneksi = DBConnection.getConnection();
             PreparedStatement pernyataan = koneksi.prepareStatement(kueri)) {
            pernyataan.setString(1, tanggal);
            pernyataan.setString(2, tanggal);
            ResultSet hasil = pernyataan.executeQuery();
            while (hasil.next()) {
                Reservation res = new Reservation(
                        hasil.getInt("id"),
                        hasil.getInt("customer_id"),
                        hasil.getInt("room_id"),
                        hasil.getString("check_in"),
                        hasil.getString("check_out"),
                        hasil.getString("payment_method"),
                        hasil.getString("status")
                );
                res.setNamaPelanggan(hasil.getString("name"));
                res.setTeleponPelanggan(hasil.getString("phone"));
                res.setNomorKamar(hasil.getString("room_number"));
                res.setTipeKamar(hasil.getString("room_type"));
                daftar.add(res);
            }
        } catch (SQLException galat) {
            galat.printStackTrace();
        }
        return daftar;
    }

        public void perbaruiStatusReservasi(int id, String status) {
        String kueri = "UPDATE reservations SET status = ? WHERE id = ?";
        try (Connection koneksi = DBConnection.getConnection();
             PreparedStatement pernyataan = koneksi.prepareStatement(kueri)) {
            pernyataan.setString(1, status);
            pernyataan.setInt(2, id);
            pernyataan.executeUpdate();
        } catch (SQLException galat) {
            galat.printStackTrace();
        }
    }

    public void hapusReservasiBerdasarkanIdKamar(int idKamar) {
        String kueri = "DELETE FROM reservations WHERE room_id = ?";
        try (Connection koneksi = DBConnection.getConnection();
             PreparedStatement pernyataan = koneksi.prepareStatement(kueri)) {
            pernyataan.setInt(1, idKamar);
            pernyataan.executeUpdate();
        } catch (SQLException galat) {
            galat.printStackTrace();
        }
    }
}
