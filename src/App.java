package org.example;

import javafx.application.Application;
import javafx.beans.binding.Bindings;
import javafx.beans.binding.BooleanBinding;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.util.Optional;

/**
 * JavaFX App
 */
public class App extends Application {
    ButtonType yes;
    ButtonType no;
    Button submit;
    Alert alert;
    int height;
    int width;
    int count;
    boolean check;
    Stage window;

    public static void main(String[] args) {
        launch();
    }

    @Override
    public void start(Stage stage) throws Exception{
        window = stage;
        alert = new Alert(Alert.AlertType.NONE);
        alert.setTitle("Dimensions");
        alert.setContentText("Would you like to input the dimensions?");
        yes = new ButtonType("Yes");
        no = new ButtonType("Randomize");
        alert.getButtonTypes().setAll(yes, no);

        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == yes){
            Height();
            Width();
        } else if (result.get() == no) {
            height = (int) (Math.random()*50)+5;
            width = (int) (Math.random()*50)+5;
        }
        count = (int) ((Math.random()*(height*width-(height*width)/5)) + (height*width)/10);



        MainView mainView = new MainView(height, width, count);
        Scene scene = new Scene(mainView, 640, 480);
        window.setScene(scene);
        window.show();

        mainView.draw();
    }

    private void Width() {
        TextInputDialog tid = new TextInputDialog("");
        tid.setTitle("Height");
        tid.setHeaderText(null);
        tid.setContentText("Please enter the height(positive number):");
        Optional<String> result = tid.showAndWait();
        result.ifPresent(name -> ErrorChecking(tid.getEditor().getText()));
        Button okButton = (Button) tid.getDialogPane().lookupButton(ButtonType.OK);
        TextField inputField = tid.getEditor();
        BooleanBinding isInvalid = Bindings.createBooleanBinding(() -> ErrorChecking(inputField.getText()), inputField.textProperty());
        okButton.disableProperty().bind(isInvalid);

        width = Integer.parseInt(tid.getEditor().getText());
    }

    private boolean ErrorChecking(String l) {
        int num;
        try {
            num = Integer.parseInt(l);
            return true;
        } catch (NumberFormatException ne) {
            return false;
        }
       /* if (num <= 0) {
            return false;
        }*/
    }

    public void Height(){

        TextInputDialog tid = new TextInputDialog("");
        tid.setTitle("Width");
        tid.setHeaderText(null);
        tid.setContentText("Please enter the width(positive number):");
        Optional<String> result = tid.showAndWait();
        result.ifPresent(name -> ErrorChecking(tid.getEditor().getText()));
        Button okButton = (Button) tid.getDialogPane().lookupButton(ButtonType.OK);
        TextField inputField = tid.getEditor();
        BooleanBinding isInvalid = Bindings.createBooleanBinding(() -> ErrorChecking(inputField.getText()), inputField.textProperty());
        okButton.disableProperty().bind(isInvalid);

        height = Integer.parseInt(tid.getEditor().getText());
    }
}
