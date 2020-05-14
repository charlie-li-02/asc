package ui;

import closet.Closet;
import exceptions.invalidCategory;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class closetPageController {

    private ObservableList<String> categoryList = FXCollections.observableArrayList("ACCESSORIES", "BAGS",
            "DRESSES", "OUTERWEARS", "PANTS", "SHIRTS", "SHOES", "SHORTS", "SKIRTS", "SWEATERS", "SWEATSHIRTS", "TOPS");

    // begin choice box -------------------------------------------
    @FXML
    private ChoiceBox<String> choiceBox;
    // end choice box ---------------------------------------------

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

    @FXML
    private Label chooseCatLabel;

    @FXML
    private Label chooseNameLabel;

    @FXML
    private Label chooseBrandLabel;

    @FXML
    private Label chooseSizeLabel;

    @FXML
    private Label choosePriceLabel;

    @FXML
    private Label chooseImageLabel;

    @FXML
    private Label imagePreviewLabel;

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
    private Button addButton;

    @FXML
    private Button doneButton;

    @FXML
    private Button backButton;

    @FXML
    private Button chooseImageButton;
    // end buttons -----------------------------------------------

    // begin text fields -----------------------------------------

    @FXML
    private TextField nameBox;

    @FXML
    private TextField brandBox;

    @FXML
    private TextField sizeBox;

    @FXML
    private TextField priceBox;

    // end text fields -------------------------------------------

    // begin image views -----------------------------------------
    @FXML
    private ImageView imagePreview;

    private BufferedImage image;
    // end image views -------------------------------------------


    private Closet closet;


    @FXML
    public void initialize() {
        closetLabel.setUnderline(true);
        root.getChildren().addAll(accessories, bags, dresses, outerwears, pants, shirts, shoes, shorts, skirts, sweaters, sweatshirts, tops);
        treeView.setRoot(root);
        choiceBox.setItems(categoryList);
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
        doneButton.setVisible(false);
        backButton.setVisible(false);
        chooseCatLabel.setVisible(false);
        chooseNameLabel.setVisible(false);
        chooseBrandLabel.setVisible(false);
        chooseSizeLabel.setVisible(false);
        choosePriceLabel.setVisible(false);
        chooseImageLabel.setVisible(false);
        imagePreviewLabel.setVisible(false);
        choiceBox.setVisible(false);
        nameBox.setVisible(false);
        priceBox.setVisible(false);
        sizeBox.setVisible(false);
        brandBox.setVisible(false);
        chooseImageButton.setVisible(false);
        imagePreview.setVisible(false);
    }

    @FXML
    public void mouseClick(MouseEvent event) {
        TreeItem<String> item = treeView.getSelectionModel().getSelectedItem();
        if (categoryList.contains(item.getValue())) {
            System.out.println("clicked category");
            return;
        }
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("itemView.fxml"));
            Parent root = loader.load();
            itemViewPageController controller = loader.getController();
            controller.initItemView(item, closet);
            Scene newScene = new Scene(root);
            newScene.getStylesheets().add(getClass().getResource("cssFiles/itemView.css").toExternalForm());
            Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
            window.setScene(newScene);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void addButtonClicked(ActionEvent event) {
        toggleAddItemLayout();
    }

    @FXML
    public void doneButtonClicked(ActionEvent event) {
        String nameEntered = nameBox.getText();
        String brandEntered = brandBox.getText();
        String priceEntered = priceBox.getText();
        String sizeEntered = sizeBox.getText();
        String categoryEntered = choiceBox.getValue();
        switch (categoryEntered) {
            case "ACCESSORIES":
                closet.processNewItem(accessories, nameEntered, categoryEntered, brandEntered, sizeEntered, priceEntered);
                break;
            case "BAGS":
                closet.processNewItem(bags, nameEntered, categoryEntered, brandEntered, sizeEntered, priceEntered);
                break;
            case "DRESSES":
                closet.processNewItem(dresses, nameEntered, categoryEntered, brandEntered, sizeEntered, priceEntered);
                break;
            case "OUTERWEARS":
                closet.processNewItem(outerwears, nameEntered, categoryEntered, brandEntered, sizeEntered, priceEntered);
                break;
            case "PANTS":
                closet.processNewItem(pants, nameEntered, categoryEntered, brandEntered, sizeEntered, priceEntered);
                break;
            case "SHIRTS":
                closet.processNewItem(shirts, nameEntered, categoryEntered, brandEntered, sizeEntered, priceEntered);
                break;
            case "SHOES":
                closet.processNewItem(shoes, nameEntered, categoryEntered, brandEntered, sizeEntered, priceEntered);
                break;
            case "SHORTS":
                closet.processNewItem(shorts, nameEntered, categoryEntered, brandEntered, sizeEntered, priceEntered);
                break;
            case "SKIRTS":
                closet.processNewItem(skirts, nameEntered, categoryEntered, brandEntered, sizeEntered, priceEntered);
                break;
            case "SWEATERS":
                closet.processNewItem(sweaters, nameEntered, categoryEntered, brandEntered, sizeEntered, priceEntered);
                break;
            case "SWEATSHIRTS":
                closet.processNewItem(sweatshirts, nameEntered, categoryEntered, brandEntered, sizeEntered, priceEntered);
                break;
            case "TOPS":
                closet.processNewItem(tops, nameEntered, categoryEntered, brandEntered, sizeEntered, priceEntered);
                break;
            default:
                System.out.println("invalid category while getting data from user");
        }
        try {
            closet.save();
            File out = new File("src/closet/data/images/" + nameEntered + ".png");
            ImageIO.write(image, "png", out);
        } catch (Exception e) {
            e.printStackTrace();
        }
        toggleAddItemLayout();
    }

    @FXML
    public void backButtonClicked(ActionEvent event) {
        toggleAddItemLayout();
    }

    @FXML
    public void chooseImageButtonClicked(ActionEvent event) {
        FileChooser fc = new FileChooser();
        fc.getExtensionFilters().addAll (new FileChooser.ExtensionFilter("Image Files", "*.png", "*.jpg", "*.jpeg"));
        File selectedFile = fc.showOpenDialog(null);
        if (selectedFile != null) {
            try {
                BufferedImage image = ImageIO.read(selectedFile);
                imagePreview.setImage(SwingFXUtils.toFXImage(image, null));
                centerImage();
                imagePreviewLabel.setVisible(true);
                imagePreview.setVisible(true);
                this.image = image;
            } catch (Exception e) {
                e.printStackTrace();
            }
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


    private void toggleAddItemLayout() {
        if (addButton.isVisible() ) {
            addButton.setVisible(false);
            backButton.setVisible(true);
            doneButton.setVisible(true);
            treeView.setVisible(false);

            chooseCatLabel.setVisible(true);
            chooseNameLabel.setVisible(true);
            chooseBrandLabel.setVisible(true);
            chooseSizeLabel.setVisible(true);
            choosePriceLabel.setVisible(true);
            chooseImageLabel.setVisible(true);
            imagePreviewLabel.setVisible(false);
            choiceBox.setVisible(true);
            nameBox.setVisible(true);
            priceBox.setVisible(true);
            sizeBox.setVisible(true);
            brandBox.setVisible(true);
            chooseImageButton.setVisible(true);
            imagePreview.setVisible(false);
        } else {
            addButton.setVisible(true);
            backButton.setVisible(false);
            doneButton.setVisible(false);
            treeView.setVisible(true);

            chooseCatLabel.setVisible(false);
            chooseNameLabel.setVisible(false);
            chooseBrandLabel.setVisible(false);
            chooseSizeLabel.setVisible(false);
            choosePriceLabel.setVisible(false);
            chooseImageLabel.setVisible(false);
            imagePreviewLabel.setVisible(false);
            choiceBox.setVisible(false);
            nameBox.setVisible(false);
            priceBox.setVisible(false);
            sizeBox.setVisible(false);
            brandBox.setVisible(false);
            chooseImageButton.setVisible(false);
            choiceBox.getSelectionModel().clearSelection();
            imagePreview.setImage(null);
            nameBox.setText("");
            brandBox.setText("");
            sizeBox.setText("");
            priceBox.setText("");
        }
    }

    private void switchScene(String sceneName, Event event) {
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

    private void centerImage() {
        Image img = imagePreview.getImage();
        if (img != null) {
            double w = 0;
            double h = 0;

            double ratioX = imagePreview.getFitWidth() / img.getWidth();
            double ratioY = imagePreview.getFitHeight() / img.getHeight();

            double reducCoeff = 0;
            if(ratioX >= ratioY) {
                reducCoeff = ratioY;
            } else {
                reducCoeff = ratioX;
            }

            w = img.getWidth() * reducCoeff;
            h = img.getHeight() * reducCoeff;

            imagePreview.setX((imagePreview.getFitWidth() - w) / 2);
            imagePreview.setY((imagePreview.getFitHeight() - h) / 2);

        }
    }
}
