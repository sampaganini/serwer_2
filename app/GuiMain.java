package files.project.manager.app;

import files.project.manager.controller.ServerViewController;
import files.project.manager.model.Config;
import files.project.manager.model.Connection;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

/**
 * uruchamianie połączenia. ładowanie sceny serwera
 */
public class GuiMain extends Application {
    public static void main(String[] args) {
        Connection newConnect = new Connection(Config.getPath());
        newConnect.start();

        launch(args);
    }
    @Override
    public void start(Stage stage) throws Exception {

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/serverView.fxml"));
        HBox userView = loader.load();
        //kontroler wczytujemy jesli w mainie chc3emy cos ustawić np jakies etykiety
       //ServerViewController serverViewController = loader.getController();
        Scene scene = new Scene(userView);
        stage.setScene(scene);
        stage.setTitle("server");
        stage.show();
    }
}
