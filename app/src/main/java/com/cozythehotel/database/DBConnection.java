package com.cozythehotel.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class DBConnection {
    private static final String URL = "jdbc:sqlite:cozy_hotel.db";

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL);
    }

    public static void siapkanBasisData() {
        try (Connection koneksi = getConnection();
             Statement pernyataan = koneksi.createStatement()) {
            
            //tabel pelanggan
            String buatTabelPelanggan = "CREATE TABLE IF NOT EXISTS customers (" +
                    "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                    "name TEXT NOT NULL," +
                    "phone TEXT NOT NULL" +
                    ");";
            pernyataan.execute(buatTabelPelanggan);

            //tabel kamar
            String buatTabelKamar = "CREATE TABLE IF NOT EXISTS rooms (" +
                    "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                    "room_number TEXT UNIQUE NOT NULL," +
                    "room_type TEXT NOT NULL," +
                    "status TEXT DEFAULT 'Available'," +
                    "customer_name TEXT" +
                    ");";
            pernyataan.execute(buatTabelKamar);

            //tabel reservasi
            String buatTabelReservasi = "CREATE TABLE IF NOT EXISTS reservations (" +
                    "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                    "customer_id INTEGER NOT NULL," +
                    "room_id INTEGER NOT NULL," +
                    "check_in TEXT NOT NULL," +
                    "check_out TEXT NOT NULL," +
                    "payment_method TEXT NOT NULL," +
                    "status TEXT DEFAULT 'Belum Checkout'," +
                    "FOREIGN KEY (customer_id) REFERENCES customers(id)," +
                    "FOREIGN KEY (room_id) REFERENCES rooms(id)" +
                    ");";
            pernyataan.execute(buatTabelReservasi);

            try {
                pernyataan.execute("ALTER TABLE reservations ADD COLUMN status TEXT DEFAULT 'Belum Checkout'");
            } catch (SQLException galat) {
                // Kolom mungkin sudah ada, abaikan
            }

            //data kamar default
            String tambahKamar = "INSERT OR IGNORE INTO rooms (room_number, room_type) VALUES " +
                    "('ST01', 'Standard'), ('ST02', 'Standard'), ('ST03', 'Standard'), ('ST04', 'Standard'), " +
                    "('ST05', 'Standard'), ('ST06', 'Standard'), ('ST07', 'Standard'), ('ST08', 'Standard'), " +
                    "('D01', 'Deluxe'), ('D02', 'Deluxe'), ('D03', 'Deluxe'), ('D04', 'Deluxe'), " +
                    "('D05', 'Deluxe'), ('D06', 'Deluxe'), ('D07', 'Deluxe'), ('D08', 'Deluxe'), " +
                    "('S01', 'Suite'), ('S02', 'Suite'), ('S03', 'Suite'), ('S04', 'Suite'), " +
                    "('S05', 'Suite'), ('S06', 'Suite'), ('S07', 'Suite'), ('S08', 'Suite');";
            pernyataan.execute(tambahKamar);

        } catch (SQLException galat) {
            galat.printStackTrace();
        }
    }
}
