package finalprojectgui;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class AlertBox {

    private final Stage stage;
    private final Scene scene;
    private final VBox layout;
    private final Label label;
    private final Button button;
    private String title, message, buttonMessage;
    private double spacing, width, height;
    private Pos alignment;
    private boolean isError = false;

    public AlertBox(String title, String message, String buttonMessage,
            double width, double height, double spacing, Pos alignment,
            boolean isError) {
        this.title = title;
        this.message = message;
        this.buttonMessage = buttonMessage;
        this.spacing = spacing;
        this.width = width;
        this.height = height;
        this.alignment = alignment;
        this.isError = isError;

        stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setAlwaysOnTop(true);
        stage.setResizable(false);
        stage.setTitle(this.title);

        label = new Label();
        label.setText(this.message);

        button = new Button();
        button.setText(this.buttonMessage);
        button.setOnAction(x -> {
            stage.close();
        });

        layout = new VBox(this.spacing);
        layout.getChildren().addAll(label, button);
        layout.setAlignment(this.alignment);

        scene = new Scene(layout, width, height);
    }

    public AlertBox() {
        this("", "", "", 360, 120, 50.0, Pos.CENTER, false);
    }

    public AlertBox(boolean isError) {
        this("", "", "", 360, 120, 50.0, Pos.CENTER, isError);
    }

    public void display() {
        if (isError) {
            setErrorIcon();
        } else {
            setDefaultIcon();
        }

        stage.setScene(scene);
        stage.showAndWait();
    }

    // Setters
    public void setTitle(String title) {
        this.title = title;
        stage.setTitle(this.title);
    }

    public void setMessage(String message) {
        this.message = message;
        label.setText(this.message);
    }

    public void setButtonMessage(String buttonMessage) {
        this.buttonMessage = buttonMessage;
        button.setText(this.buttonMessage);
    }

    public void setWidth(double width) {
        this.width = width;
        stage.setWidth(this.width);
    }

    public void setHeight(double height) {
        this.height = height;
        stage.setHeight(this.height);
    }

    public void setSpacing(double spacing) {
        this.spacing = spacing;
        layout.setSpacing(this.spacing);
    }

    public void setAlignment(Pos alignment) {
        this.alignment = alignment;
        layout.setAlignment(this.alignment);
    }

    public void setErrorMessage(boolean isError) {
        this.isError = isError;
    }

    // Getters
    public String getTitle() {
        return title;
    }

    public String getMessage() {
        return message;
    }

    public String getButtonMessage() {
        return buttonMessage;
    }

    public double getSpacing() {
        return spacing;
    }

    public Pos getAlignment() {
        return alignment;
    }

    // Misc.
    private void setErrorIcon() {
        stage.getIcons().add(new Image(FilePaths.ERROR));
    }

    private void setDefaultIcon() {
        stage.getIcons().add(new Image(FilePaths.INSIGNIA));
    }
}
