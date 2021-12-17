package lk.ijse.secondSem.hibernate;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class AppInitializer extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws IOException {
        primaryStage.setScene(new Scene(FXMLLoader.load(this.getClass().getResource("views/DashBordForm.fxml"))));
        primaryStage.setTitle("sipsewana institute");
        primaryStage.centerOnScreen();
        primaryStage.show();




    }
}
