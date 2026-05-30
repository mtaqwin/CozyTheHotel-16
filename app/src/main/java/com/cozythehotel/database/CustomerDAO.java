package com.cozythehotel.database;

import com.cozythehotel.model.Customer;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

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
}