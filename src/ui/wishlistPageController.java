package ui;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.IOException;

public class wishlistPageController {

    // begin labels -----------------------------------------------
    @FXML
    private Label homeLabel;

    @FXML
    private Label closetLabel;

    @FXML
    private Label wishlistLabel;

    @FXML
    private Label profileLabel;
    // end labels -------------------------------------------------

    // begin buttons ----------------------------------------------
    @FXML
    private Button homeButton;

    @FXML
    private Button closetButton;

    @FXML
    private Button wishlistButton;

    @FXML
    private Button profileButton;
    // end buttons -----------------------------------------------

    @FXML
    public void initialize() {
        wishlistLabel.setUnderline(true);
    }

    @FXML
    public void homeButtonPressed(ActionEvent event) {
        switchScene("home", event);
    }

    @FXML
    public void closetButtonPressed(ActionEvent event) {
        switchScene("closet", event);
    }

    @FXML
    public void wishlistButtonPressed(ActionEvent event) {
        // do nothing
    }

    @FXML
    public void profileButtonPressed(ActionEvent event) {
        switchScene("profile", event);
    }

    private void switchScene(String sceneName, ActionEvent event) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource(sceneName + ".fxml"));            Scene newScene = new Scene(root);
            newScene.getStylesheets().add(getClass().getResource("cssFiles/" + sceneName + ".css").toExternalForm());
            Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
            window.setScene(newScene);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
