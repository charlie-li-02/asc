package ui;

import closet.Closet;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class homePageController extends Controller {


    String usernamePath = "src/user/username.txt";
    String username = "";

    // begin text fields -------------------------------------------
    @FXML
    private TextField enterUserName;
    // end text fields ---------------------------------------------

    // begin labels ------------------------------------------------
    @FXML
    private Label enterUsernameLabel;

    @FXML
    private Label usernameLabel;

    @FXML
    private ImageView weatherIcon;

    @FXML
    private Label tempLabel;

    @FXML
    private Label locationLabel;

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

    Closet closet;


    @FXML
    public void init(Closet closet) {
        this.closet = closet;
        enterUserName.setVisible(false);
        enterUsernameLabel.setVisible(false);
        homeLabel.setUnderline(true);
        try {
            loadUsername();
            displayWeather();
        } catch (IOException e) {
            e.printStackTrace();
        }
        usernameLabel.setText(username);
        tempLabel.setVisible(true);
        locationLabel.setVisible(true);
        weatherIcon.setVisible(true);
    }

    @FXML
    public void onEnter(ActionEvent event) {
        username = enterUserName.getText();
        try {
            saveUsername();
            usernameLabel.setText(username);
            enterUserName.setVisible(false);
            enterUsernameLabel.setVisible(false);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void homeButtonPressed(ActionEvent event) {
        // do nothing
    }

    @FXML
    public void closetButtonPressed(ActionEvent event) {
        switchScene("closet", event, closet);
    }

    @FXML
    public void wishlistButtonPressed(ActionEvent event) {
        hideWeather();
        switchScene("wishlist", event, closet);
    }

    @FXML
    public void profileButtonPressed(ActionEvent event) {
        hideWeather();
        switchScene("profile", event, closet);
    }

    private void displayWeather() {
        tempLabel.setText(loadTemp() + "°C");
        weatherIcon.setImage(SwingFXUtils.toFXImage(loadIcon(), null));
    }

    private void hideWeather() {
        weatherIcon.setVisible(false);
        tempLabel.setVisible(false);
        locationLabel.setVisible(false);
    }

    private void enterUsername() {
        enterUserName.setVisible(true);
        enterUsernameLabel.setVisible(true);
    }

    private void loadUsername() throws IOException {
        List<String> sl = Files.readAllLines(Paths.get(usernamePath));
        if (sl.size() > 0) {
            username = sl.get(0);
        } else {
            enterUsername();
        }
    }

    private void saveUsername() throws FileNotFoundException, UnsupportedEncodingException {
        PrintWriter clearer = new PrintWriter(usernamePath, "UTF-8");
        clearer.close();
        PrintWriter writer = new PrintWriter(usernamePath, "UTF-8");
        writer.println(username);
        writer.close();
    }

    private int loadTemp() {
        try {
            List<String> sl = Files.readAllLines(Paths.get("src/ui/weather/temp.txt"));
            if (sl.size() > 0) {
                return Integer.parseInt(sl.get(0));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return -100;
    }

    private BufferedImage loadIcon() {
        try {
            File imageFile = new File("src/ui/weather/icon.png");
            BufferedImage image = ImageIO.read(imageFile);
            return image;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
