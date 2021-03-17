module org.openjfx {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.rmi;
    exports com.sd.chat;
    exports com.sd.chat.rmi.interfaces;
    exports com.sd.chat.clients;
    exports com.sd.chat.controllers;
    opens com.sd.chat.controllers;
}