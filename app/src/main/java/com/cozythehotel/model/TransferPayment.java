package com.cozythehotel.model;

public class TransferPayment extends Payment {
    public TransferPayment(double jumlah) {
        super(jumlah);
    }

    @Override
    public String prosesPembayaran() {
        return "Pembayaran via Transfer Bank berhasil diproses.";
    }
}
