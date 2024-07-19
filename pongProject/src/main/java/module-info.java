module ie.mtu.pongproject {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    opens pong to javafx.fxml;
    exports pong;
}