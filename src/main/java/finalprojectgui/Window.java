package finalprojectgui;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;

public class Window {

    private String b1Label, b2Label, sceneLabel;
    private double spacing;
    private Scene scene;
    protected Button b1, b2;
    private Label label;
    protected VBox vbox;
    private HBox hbox;

    public Window(String b1Label, String b2Label, String sceneLabel,
            double spacing) {
        this.b1Label = b1Label;
        this.b2Label = b2Label;
        this.sceneLabel = sceneLabel;
        this.spacing = spacing;

        label = new Label(this.sceneLabel);
        label.setFont(new Font("Inconsolata", 48.0));
        b1 = new Button(this.b1Label);
        b2 = new Button(this.b2Label);

        hbox = new HBox(this.spacing);
        hbox.setAlignment(Pos.BOTTOM_CENTER);
        hbox.getChildren().addAll(b1, b2);

        vbox = new VBox(spacing);
        vbox.setAlignment(Pos.TOP_CENTER);
        vbox.getChildren().add(label);
        vbox.getChildren().add(hbox);
    }

    public Window() {
        this("", "", "", 50.0);
    }

    // Getters
    public String getButtonOneLabel() {
        return b1Label;
    }

    public String getButtonTwoLabel() {
        return b2Label;
    }

    public String getSceneLabel() {
        return sceneLabel;
    }

    public double getSpacing() {
        return spacing;
    }

    public VBox getVBox() {
        return vbox;
    }

    public HBox getHBox() {
        return hbox;
    }

    public Label getLabel() {
        return label;
    }

    public String getLabelText() {
        return sceneLabel;
    }

    public Button getButtonOne() {
        return b1;
    }

    public Button getButtonTwo() {
        return b2;
    }

    // Setters
    public void setButtonOneLabel(String b1Label) {
        this.b1Label = b1Label;
        b1.setText(this.b1Label);
    }

    public void setButtonTwoLabel(String b2Label) {
        this.b2Label = b2Label;
        b2.setText(this.b2Label);
    }

    public void setSceneLabel(String sceneLabel) {
        this.sceneLabel = sceneLabel;
        label.setText(this.sceneLabel);
    }

    public void setSpacing(double spacing) {
        this.spacing = spacing;
        vbox.setSpacing(this.spacing);
        hbox.setSpacing(this.spacing);
    }

    public void setVBox(VBox vbox) {
        this.vbox = vbox;
    }

    public void setHBox(HBox hbox) {
        this.hbox = hbox;
    }

    public void setLabel(Label label) {
        this.label = label;
    }

    public void setLabelText(String sceneLabel) {
        this.sceneLabel = sceneLabel;
        label.setText(this.sceneLabel);
    }

    public void setButtonOne(Button b1) {
        this.b1 = b1;
    }

    public void setButtonTwo(Button b2) {
        this.b2 = b2;
    }

    public Scene initScene() {
        scene = new Scene(vbox, 720, 480);
        return scene;
    }
}
