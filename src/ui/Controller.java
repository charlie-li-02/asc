package ui;

import closet.Closet;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public abstract class Controller {


    abstract void init(Closet closet);

    protected void switchScene(String sceneName, ActionEvent event, Closet closet) {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource(sceneName + ".fxml"));
            Parent root = loader.load();
            Controller controller = loader.getController();
            controller.init(closet);
            Scene newScene = new Scene(root);
            newScene.getStylesheets().add(getClass().getResource("cssFiles/" + sceneName + ".css").toExternalForm());
            Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
            window.setScene(newScene);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
