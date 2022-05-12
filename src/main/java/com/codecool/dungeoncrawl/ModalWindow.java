package com.codecool.dungeoncrawl;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextInputDialog;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;


import java.util.Collection;
import java.util.Optional;

public class ModalWindow {
    private boolean answer;
    private int slot;

    public ModalWindow() {
        this.answer = false;
        this.slot = -1;
    }

    public boolean display(String title, String message) {
        Stage window = new Stage();
        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle(title);
        window.setMinWidth(350);
        window.setMinHeight(350);
        Label label = new Label();
        label.setText(message);
        Button yesButton = new Button("Save");
        Button noButton = new Button("Cancel");

        yesButton.setOnAction(e -> {
            answer = true;
            window.close();
        });
        noButton.setOnAction(e -> {
            answer = false;
            window.close();
        });

        VBox layout = new VBox(10);
        layout.getChildren().addAll(label, yesButton, noButton);
        layout.setAlignment(Pos.CENTER);
        Scene scene = new Scene(layout);
        window.setScene(scene);
        window.showAndWait();
        return answer;
    }

    public String gameName(){
        TextInputDialog dialog = new TextInputDialog();
        dialog.setTitle("SAVE GAME");
        dialog.getDialogPane().setContentText("TYPE SAVING NAME");
        Optional<String> result = dialog.showAndWait();
        return result.isPresent()? result.get(): "";
    }
}
