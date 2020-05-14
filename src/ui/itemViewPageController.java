package ui;

import closet.Closet;
import closet.ClosetItem;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.TreeItem;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;

public class itemViewPageController {


    // begin text fields ---------------------------------------------------
    @FXML
    private TextField itemNameBox;
    @FXML
    private TextField itemBrandBox;
    @FXML
    private TextField itemSizeBox;
    @FXML
    private TextField itemPriceBox;

    // end text fields -----------------------------------------------------

    // begin labels --------------------------------------------------------
    @FXML
    private Label itemNameLabel;

    @FXML
    private Label itemBrandLabel;

    @FXML
    private Label itemSizeLabel;

    @FXML
    private Label itemPriceLabel;
    // end labels ----------------------------------------------------------

    // begin buttons -------------------------------------------------------
    @FXML
    private Button backButton;

    @FXML
    private Button deleteButton;

    @FXML
    private Button editButton;

    @FXML
    private Button doneButton;

    @FXML
    private Button chooseImageButton;

    // end buttons ---------------------------------------------------------

    // begin image view ----------------------------------------------------
    @FXML
    private ImageView imageView;
    // end image view ------------------------------------------------------

    @FXML
    private VBox textFields;


    private BufferedImage image;
    private Closet closet;
    private ClosetItem item;
    private String itemName;
    private String itemCategory;

    private HashMap<String, ClosetItem> retrieveItemMap() {
        switch (itemCategory) {
            case "ACCESSORIES":
                return closet.getAccessories();
            case "BAGS":
                return closet.getBags();
            case "DRESSES":
                return closet.getDresses();
            case "OUTERWEARS":
                return closet.getOuterwears();
            case "PANTS":
                return closet.getPants();
            case "SHIRTS":
                return closet.getShirts();
            case "SHOES":
                return closet.getShoes();
            case "SHORTS":
                return closet.getShorts();
            case "SKIRTS":
                return closet.getSkirts();
            case "SWEATERS":
                return closet.getSweaters();
            case "SWEATSHIRTS":
                return closet.getSweatshirts();
            case "TOPS":
                return closet.getTops();
            default:
                System.out.println("invalid category while switching to item view");
        }
        return null;
    }

    private void initHelper() {
        setText();
        textFields.setVisible(false);
        doneButton.setVisible(false);
        chooseImageButton.setVisible(false);
        loadImage();
    }

    public void initItemView(TreeItem<String> item, Closet closet) {
        itemName = item.getValue();
        itemCategory = item.getParent().getValue();
        this.closet = closet;
        this.item = retrieveItemMap().get(itemName);
        initHelper();
    }

    private void loadImage() {
        try {
            File imageFile = new File("src/closet/data/images/" + itemName + ".png");
            BufferedImage image = ImageIO.read(imageFile);
            this.image = image;
            this.imageView.setImage(SwingFXUtils.toFXImage(image, null));
        } catch (Exception e) {
            e.printStackTrace();
        }
        centerImage();
    }

    @FXML
    public void backButtonClicked(ActionEvent event) {
        try {
            switchToClosetView(event);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void switchToClosetView(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("closet.fxml"));
        Scene newScene = new Scene(root);
        newScene.getStylesheets().add(getClass().getResource("cssFiles/closet.css").toExternalForm());
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(newScene);
    }

    @FXML
    public void deleteButtonClicked(ActionEvent event) {
        HashMap<String, ClosetItem> map = retrieveItemMap();
        map.remove(itemName);
        File file = new File("src/closet/data/images/" + itemName + ".png");
        Path path = Paths.get(file.getAbsolutePath());
        try {
            Files.delete(path);
            closet.save();
            switchToClosetView(event);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void editButtonClicked(ActionEvent event) {
        itemNameBox.requestFocus();
        toggleLayout();
    }

    @FXML
    public void doneButtonClicked(ActionEvent event) {
        String name = itemNameBox.getText();
        String brand = itemBrandBox.getText();
        String size = itemSizeBox.getText();
        String price = itemPriceBox.getText();
        refreshImage(itemName, name);
        itemName = name;
        item.setName(name);
        item.setBrand(brand);
        item.setSize(size);
        item.setPrice(Double.parseDouble(price));
        setText();
        try {
        closet.save();
        } catch (Exception e) {
            e.printStackTrace();
        }
        toggleLayout();
    }

    @FXML
    public void chooseImageButtonClicked(ActionEvent event) {
        FileChooser fc = new FileChooser();
        fc.getExtensionFilters().addAll (new FileChooser.ExtensionFilter("Image Files", "*.png", "*.jpg", "*.jpeg"));
        File selectedFile = fc.showOpenDialog(null);
        if (selectedFile != null) {
            try {
                BufferedImage image = ImageIO.read(selectedFile);
                imageView.setImage(SwingFXUtils.toFXImage(image, null));
                centerImage();
                imageView.setVisible(true);
                this.image = image;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private void centerImage() {
        Image img = imageView.getImage();
        if (img != null) {
            double w = 0;
            double h = 0;

            double ratioX = imageView.getFitWidth() / img.getWidth();
            double ratioY = imageView.getFitHeight() / img.getHeight();

            double reducCoeff = 0;
            if(ratioX >= ratioY) {
                reducCoeff = ratioY;
            } else {
                reducCoeff = ratioX;
            }

            w = img.getWidth() * reducCoeff;
            h = img.getHeight() * reducCoeff;

            imageView.setX((imageView.getFitWidth() - w) / 2);
            imageView.setY((imageView.getFitHeight() - h) / 2);

        }
    }

    private void toggleLayout() {
        if (editButton.isVisible()) {
            editButton.setVisible(false);
            itemNameLabel.setVisible(false);
            itemPriceLabel.setVisible(false);
            itemSizeLabel.setVisible(false);
            itemBrandLabel.setVisible(false);
            textFields.setVisible(true);
            doneButton.setVisible(true);
            chooseImageButton.setVisible(true);
        } else if (!editButton.isVisible()) {
            editButton.setVisible(true);
            textFields.setVisible(false);
            itemNameLabel.setVisible(true);
            itemPriceLabel.setVisible(true);
            itemSizeLabel.setVisible(true);
            itemBrandLabel.setVisible(true);
            doneButton.setVisible(false);
            chooseImageButton.setVisible(false);
        }
    }

    private void setText() {
        itemNameLabel.setText(item.getName());
        itemBrandLabel.setText(item.getBrand());
        itemPriceLabel.setText("$"+ (int) item.getPrice() + " CAD");
        itemSizeLabel.setText(item.getSize());
        itemNameBox.setText(item.getName());
        itemBrandBox.setText(item.getBrand());
        itemPriceBox.setText(Double.toString(item.getPrice()));
        itemSizeBox.setText(item.getSize());
    }

    private void refreshImage(String oldName, String newName) {
        File file = new File("src/closet/data/images/" + oldName + ".png");
        Path path = Paths.get(file.getAbsolutePath());
        try {
            Files.delete(path);
        } catch (Exception e) {
            e.printStackTrace();
        }


        File out = new File("src/closet/data/images/" + newName + ".png");
        try {
            ImageIO.write(image, "png", out);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
