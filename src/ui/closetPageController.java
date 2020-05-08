package ui;

import closet.Closet;
import exceptions.invalidCategory;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;

public class closetPageController {

    // begin scroll pane ------------------------------------------
    @FXML
    private ScrollPane scrollPane;
    // end scroll pane --------------------------------------------


    // begin tree view --------------------------------------------
    @FXML
    private TreeView<String> treeView;

    TreeItem<String> root = new TreeItem<>("Root");
    TreeItem<String> accessories = new TreeItem<>("ACCESSORIES");
    TreeItem<String> bags = new TreeItem<>("BAGS");
    TreeItem<String> dresses = new TreeItem<>("DRESSES");
    TreeItem<String> outerwears = new TreeItem<>("OUTERWEARS");
    TreeItem<String> pants = new TreeItem<>("PANTS");
    TreeItem<String> shirts = new TreeItem<>("SHIRTS");
    TreeItem<String> shoes = new TreeItem<>("SHOES");
    TreeItem<String> shorts = new TreeItem<>("SHORTS");
    TreeItem<String> skirts = new TreeItem<>("SKIRTS");
    TreeItem<String> sweaters = new TreeItem<>("SWEATERS");
    TreeItem<String> sweatshirts = new TreeItem<>("SWEATSHIRTS");
    TreeItem<String> tops = new TreeItem<>("TOPS");

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

    private Closet closet;


    @FXML
    public void initialize() {
        closetLabel.setUnderline(true);
        root.getChildren().addAll(accessories, bags, dresses, outerwears, pants, shirts, shoes, shorts, skirts, sweaters, sweatshirts, tops);
        treeView.setRoot(root);
        closet = new Closet();
        closet.initCloset();
        try {
            closet.display(accessories);
            closet.display(bags);
            closet.display(dresses);
            closet.display(outerwears);
            closet.display(pants);
            closet.display(shirts);
            closet.display(shoes);
            closet.display(shorts);
            closet.display(skirts);
            closet.display(sweaters);
            closet.display(sweatshirts);
            closet.display(tops);
        } catch (invalidCategory e) {
            e.printStackTrace();
        }

    }

    @FXML
    public void homeButtonPressed(ActionEvent event) {
        try {
            closet.save();
        } catch (Exception e) {
            e.printStackTrace();
        }
        switchScene("home", event);
    }

    @FXML
    public void closetButtonPressed(ActionEvent event) {
        // do nothing
    }

    @FXML
    public void wishlistButtonPressed(ActionEvent event) {
        try {
            closet.save();
        } catch (Exception e) {
            e.printStackTrace();
        }
        switchScene("wishlist", event);
    }

    @FXML
    public void profileButtonPressed(ActionEvent event) {
        try {
            closet.save();
        } catch (Exception e) {
            e.printStackTrace();
        }
        switchScene("profile", event);
    }

    private void switchScene(String sceneName, ActionEvent event) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource(sceneName + ".fxml"));
            Scene newScene = new Scene(root);
            newScene.getStylesheets().add(getClass().getResource("cssFiles/" + sceneName + ".css").toExternalForm());
            Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
            window.setScene(newScene);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
