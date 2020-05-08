package ui;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class profilePageController {

    // begin labels -----------------------------------------------
    @FXML
    private Label homeLabel;

    @FXML
    private Label closetLabel;

    @FXML
    private Label wishlistLabel;

    @FXML
    private Label profileLabel;

    @FXML
    private Label usernameLabel;
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

    @FXML
    private Button editUsernameButton;
    // end buttons -----------------------------------------------

    // begin text fields -----------------------------------------

    @FXML
    private TextField usernameBox;




    private String username;
    private String usernamePath = "src/user/username.txt";

    @FXML
    public void initialize() {
        profileLabel.setUnderline(true);
        usernameBox.setVisible(false);
        try {
            loadUsername();
        } catch (Exception e) {
            e.printStackTrace();
        }
        usernameLabel.setText(username);
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
        switchScene("wishlist", event);
    }

    @FXML
    public void profileButtonPressed(ActionEvent event) {
        // do nothing
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

    @FXML
    public void editUsernameButtonPressed(ActionEvent event) {
        usernameBox.setVisible(true);
        usernameLabel.setVisible(false);
        editUsernameButton.setVisible(false);
    }

    @FXML
    public void onEnter(ActionEvent event) {
        username = usernameBox.getText();
        try {
            saveUsername();
        } catch (IOException e) {
            e.printStackTrace();
        }
        usernameLabel.setText(username);
        usernameBox.setVisible(false);
        usernameBox.setVisible(false);
        editUsernameButton.setVisible(true);
        usernameLabel.setVisible(true);
    }

    private void loadUsername() throws IOException {
        List<String> sl = Files.readAllLines(Paths.get(usernamePath));
        if (sl.size() > 0) {
            username = sl.get(0);
        }
    }

    private void saveUsername() throws FileNotFoundException, UnsupportedEncodingException {
        PrintWriter clearer = new PrintWriter(usernamePath, "UTF-8");
        clearer.close();
        PrintWriter writer = new PrintWriter(usernamePath, "UTF-8");
        writer.println(username);
        writer.close();
    }
}
