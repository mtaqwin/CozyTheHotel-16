package com.cozythehotel;

import com.cozythehotel.controller.DashboardController;
import com.cozythehotel.database.DBConnection;

import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application {
    @Override
    public void start(Stage stage) {
        DBConnection.siapkanBasisData();

        DashboardController dashboard = new DashboardController(stage);
        dashboard.tampilkan();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
