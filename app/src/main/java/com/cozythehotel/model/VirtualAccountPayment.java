package com.cozythehotel.model;

public class VirtualAccountPayment extends Payment {
    public VirtualAccountPayment(double jumlah) {
        super(jumlah);
    }

    @Override
    public String prosesPembayaran() {
        return "Pembayaran via Virtual Account berhasil diproses.";
    }
}
