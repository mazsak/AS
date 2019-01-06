package Main;

import hibernate.FactoryHibernate;
import hibernate.HBull;
import hibernate.HCowshed;
import hibernate.HTeam;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import models.Cowshed;

import javax.persistence.EntityManager;
import java.awt.*;

public class Main extends Application {

    public void start(Stage primaryStage) throws Exception {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(this.getClass().getResource("/FXML/Main/MainWindow.fxml"));

        BorderPane bp = loader.load();
        Scene scene = new Scene(bp);

        primaryStage.setTitle("Asystent Stada");
        primaryStage.setScene(scene);
        primaryStage.setMinHeight(Toolkit.getDefaultToolkit().getScreenSize().getHeight()/1.5);
        primaryStage.setMinWidth(Toolkit.getDefaultToolkit().getScreenSize().getWidth()/1.5);
        primaryStage.setMaximized(true);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
