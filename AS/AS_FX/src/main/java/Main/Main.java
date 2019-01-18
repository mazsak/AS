package Main;

import hibernate.FactoryHibernate;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.awt.*;

public class Main extends Application {

    public void start(Stage primaryStage) throws Exception {
        FactoryHibernate factoryHibernate = new FactoryHibernate();
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
