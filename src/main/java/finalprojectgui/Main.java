package finalprojectgui;

import javafx.application.Application;
import java.io.File;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import java.io.IOException;

public class Main extends Application {

    private AlertBox alert;
    private Window mainWindow, searchWindow, addWindow;
    private Scene main, search, add;
    private TextArea textOutput;
    private File instructorFile, departmentFile;
    private InputHandler inputHandler;
    private ImageView logo;
    private Button importInstructor, importDepartment;
    private HBox mainSceneButtons;
    private String employeeName, employeeID, employeeDepartment;
    private String[] instructorArray, departmentArray;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        mainScene(primaryStage);
        searchScene(primaryStage);
        addScene(primaryStage);
        primaryStage.getIcons().add(new Image(FilePaths.INSIGNIA));
        primaryStage.setResizable(false);
        primaryStage.setTitle("MC Instructor Database Tool");
        primaryStage.setScene(main);
        primaryStage.show();
    }

    private void mainScene(Stage primaryStage) {
        mainWindow = new Window();
        mainWindow.setLabelText("Instructor Database");
        mainWindow.b1.setGraphic(new ImageView(new Image(FilePaths.SEARCH, 32.0,
                32.0, false, false)));
        mainWindow.b1.setDisable(true);
        mainWindow.b2.setGraphic(new ImageView(new Image(FilePaths.ADD, 32.0,
                32.0, false, false)));
        mainWindow.b2.setDisable(true);
        mainWindow.setSpacing(30.0);

        mainWindow.b1.setOnAction(x -> {
            primaryStage.setScene(search);
        });
        mainWindow.b2.setOnAction(x -> {
            primaryStage.setScene(add);
        });

        logo = new ImageView(new Image(FilePaths.LOGO,
                250, 250, false, false));
        mainWindow.vbox.getChildren().add(logo);

        alert = new AlertBox();
        alert.setTitle("Success");
        alert.setMessage("File imported!");
        alert.setButtonMessage("Dismiss");

        mainSceneButtons = new HBox(mainWindow.getSpacing());
        importInstructor = new Button("Import Instructors");
        importDepartment = new Button("Import Departments");
        importDepartment.setDisable(true);
        importInstructor.setOnAction(x -> {
            FileChooser fileChooser = new FileChooser();
            fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("TXT files (*.txt)", "*.txt"));
            instructorFile = fileChooser.showOpenDialog(primaryStage);
            if (instructorFile != null) {
                alert.display();
                importDepartment.setDisable(false);
                importInstructor.setDisable(true);
            }
        });
        importDepartment.setOnAction(x -> {
            FileChooser fileChooser = new FileChooser();
            fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("TXT files (*.txt)", "*.txt"));
            departmentFile = fileChooser.showOpenDialog(primaryStage);

            if (departmentFile != null) {
                try {
                    inputHandler = new InputHandler(instructorFile, departmentFile);
                    mainWindow.b1.setDisable(false);
                    mainWindow.b2.setDisable(false);
                    importDepartment.setDisable(true);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                alert.display();
            }
        });
        mainSceneButtons.getChildren().addAll(importInstructor, importDepartment);
        mainSceneButtons.setAlignment(Pos.CENTER);
        mainWindow.vbox.getChildren().add(mainSceneButtons);

        main = mainWindow.initScene();
    }

    private void searchScene(Stage primaryStage) throws Exception {
        searchWindow = new Window();
        searchWindow.setLabelText("Search Instructor ID");
        searchWindow.setButtonOneLabel("Search");
        searchWindow.setButtonTwoLabel("Main Menu");
        searchWindow.setSpacing(30.0);

        TextField textInputID = new TextField();
        textInputID.setScaleShape(true);
        textInputID.setAlignment(Pos.BASELINE_LEFT);
        textInputID.setPrefWidth(90.0);
        textInputID.setMaxWidth(180.0);
        textInputID.setPromptText("ID");
        searchWindow.vbox.getChildren().add(textInputID);

        textOutput = new TextArea();
        textOutput.setScaleShape(true);
        textOutput.setMaxWidth(480.0);
        textOutput.setMaxHeight(720.0);
        textOutput.setPromptText("Output");
        textOutput.setEditable(false);
        searchWindow.vbox.getChildren().add(textOutput);

        searchWindow.b1.setOnAction(x -> {
            // Alert Box init
            alert = new AlertBox();
            alert.setButtonMessage("Ok");
            alert.setTitle("Success!");
            employeeID = InputHandler.cleanse(textInputID.getText());
            instructorArray = new String[3];
            if (employeeID.equals("")) {
                alert.setTitle("ERROR!");
                alert.setMessage("Field is empty!");
                alert.setHeight(40.0);
                alert.setHeight(100.0);
                alert.display();
            } else {
                searchEmployee();
                if (instructorArray == null) {
                    alert.setTitle("ERROR");
                    alert.setMessage("ID: '" + employeeID + "' is not in database!");
                    alert.display();
                } else {
                    textOutput.setText(String.format("Instructor Name:\t%s\n"
                            + "Department:\t\t%s\n"
                            + "Location:\t\t\t%s\n",
                            instructorArray[0], instructorArray[1], instructorArray[2]));
                }
            }
            textInputID.clear();
        });
        searchWindow.b2.setOnAction(x -> {
            primaryStage.setScene(main);
        });

        search = searchWindow.initScene();
    }

    private void searchEmployee() {
        try {
            inputHandler = new InputHandler(instructorFile, departmentFile);
            instructorArray = inputHandler.getInstructorDetails(employeeID);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void addScene(Stage primaryStage) {
        addWindow = new Window();
        addWindow.setLabelText("Add Instructor");
        addWindow.setButtonOneLabel("Add");
        addWindow.setButtonTwoLabel("Main Menu");
        addWindow.setSpacing(30.0);

        TextField textInputID = new TextField();
        textInputID.setScaleShape(true);
        textInputID.setAlignment(Pos.BASELINE_LEFT);
        textInputID.setPrefWidth(90.0);
        textInputID.setMaxWidth(180.0);
        textInputID.setPromptText("ID");
        addWindow.vbox.getChildren().add(textInputID);

        TextField textInputName = new TextField();
        textInputName.setScaleShape(true);
        textInputName.setAlignment(Pos.BASELINE_LEFT);
        textInputName.setPrefWidth(90.0);
        textInputName.setMaxWidth(180.0);
        textInputName.setPromptText("Name");
        addWindow.vbox.getChildren().add(textInputName);

        TextField textInputDepartment = new TextField();
        textInputDepartment.setScaleShape(true);
        textInputDepartment.setAlignment(Pos.BASELINE_LEFT);
        textInputDepartment.setPrefWidth(90.0);
        textInputDepartment.setMaxWidth(180.0);
        textInputDepartment.setPromptText("Department");
        addWindow.vbox.getChildren().add(textInputDepartment);

        addWindow.b1.setOnAction(x -> {
            employeeID = InputHandler.cleanse(textInputID.getText());
            employeeName = InputHandler.cleanse(textInputName.getText());
            employeeDepartment = InputHandler.cleanse(textInputDepartment.getText());
            addEmployee();
            employeeID = "";
            employeeName = "";
            employeeDepartment = "";
            textInputID.clear();
            textInputName.clear();
            textInputDepartment.clear();
        });
        addWindow.b2.setOnAction(x -> {
            primaryStage.setScene(main);
        });
        add = addWindow.initScene();
    }

    private void addEmployee() { // Alert Box init alert = new AlertBox();
        alert.setButtonMessage("Ok");
        alert.setTitle("ERROR");

        if (employeeID.equals("") || employeeName.equals("") || employeeDepartment.equals("")) {
            alert.setMessage("Field(s) cannot be left empty!");
            alert.display();
            return;
        }
        try {
            inputHandler = new InputHandler(instructorFile, departmentFile);
            if (inputHandler.containsID(employeeID)) {
                alert.setMessage("ID: '" + employeeID + "' ALREADY EXISTS IN DATABASE!");
                alert.display();
                return;
            }
            if (!inputHandler.containsDepartment(employeeDepartment)) {
                alert.setMessage("DEPARTMENT: '" + employeeDepartment + "' DOES NOT EXIST IN DATABASE");
                alert.display();
                return;
            }
            inputHandler.write(employeeID + "," + employeeName + "," + employeeDepartment + System.lineSeparator());
            alert.setTitle("SUCCESS");
            alert.setMessage("ID: '" + employeeID + "'added!");
            alert.display();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
