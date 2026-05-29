package com.cozythehotel.model;

public class SuiteRoom extends Room {
    public SuiteRoom(int id, String roomNumber, String status, String customerName) {
        super(id, roomNumber, "Suite", status, customerName);
    }
}
