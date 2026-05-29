package com.cozythehotel;

import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        // Ini sementara biar aplikasinya bisa jalan dulu tanpa error.
        // Nanti lu tinggal panggil View atau Dashboard lu di sini.
        primaryStage.setTitle("CozyThe Hotel - 16");
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}