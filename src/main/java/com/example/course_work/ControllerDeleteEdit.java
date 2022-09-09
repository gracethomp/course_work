package com.example.course_work;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;

public class ControllerDeleteEdit {
    private static String name1;
    @FXML
    public ImageView editButton;
    @FXML
    private Text eventName;

    public static void getName(String name) {
        name1 = name;
    }

    public static String getName1() {
        return name1;
    }

    public void initialize() {
        eventName.setText(name1);
    }

    public void edit(MouseEvent mouseEvent) {
        FXMLLoader fxmlLoader = new FXMLLoader(Application.class.getResource("version2.2.1_LA.fxml"));
        Scene secondScene = null;
        try {
            secondScene = new Scene(fxmlLoader.load(), 400, 400);
        } catch (IOException e) {
            e.printStackTrace();
        }
        Stage newWindow = new Stage();
        newWindow.setTitle("Edit event");
        newWindow.setScene(secondScene);
        System.out.println(name1);
        newWindow.show();

    }
}
