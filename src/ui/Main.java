package ui;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import network.ReadWebPageWeather;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;

public class Main extends Application {

    Stage window;
    Scene home;
    Scene profile;


    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        try {
            window = primaryStage;
            window.setTitle("A.S.C.");
            window.getIcons().add(new Image("file:asc.png"));
            homeScene();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void homeScene() throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("home.fxml"));
        home = new Scene(root);
        home.getStylesheets().add(getClass().getResource("cssFiles/home.css").toExternalForm());

        ReadWebPageWeather weatherParser = new ReadWebPageWeather();
        weatherParser.readWeather();
        weatherParser.parseTemperature();
        weatherParser.weatherIcon();
        saveWeatherIcon(weatherParser.getIcon());
        saveTemp(weatherParser.getTemp());
        window.setScene(home);
        window.show();
    }

    private void saveWeatherIcon(BufferedImage icon) throws IOException {
        File out = new File("src/ui/weather/icon.png");
        ImageIO.write(icon, "png", out);
    }

    private void saveTemp(int temp) throws IOException {
        PrintWriter clearer = new PrintWriter("src/ui/weather/temp.txt", "UTF-8");
        clearer.close();
        PrintWriter writer = new PrintWriter("src/ui/weather/temp.txt", "UTF-8");
        writer.println(temp);
        writer.close();
    }





}
