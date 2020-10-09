package ui;

import closet.Closet;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.GridPane;

public class wishlistPageController extends Controller {

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
    private ScrollPane scrollPane;

    @FXML
    private GridPane gridPane;

    private Closet closet;


    @FXML
    public void init(Closet closet) {
        this.closet = closet;
        wishlistLabel.setUnderline(true);
    }

    @FXML
    public void homeButtonPressed(ActionEvent event) {
        switchScene("home", event, closet);
    }

    @FXML
    public void closetButtonPressed(ActionEvent event) {
        switchScene("closet", event, closet);
    }

    @FXML
    public void wishlistButtonPressed(ActionEvent event) {
        // do nothing
    }

    @FXML
    public void profileButtonPressed(ActionEvent event) {
        switchScene("profile", event, closet);
    }

}
