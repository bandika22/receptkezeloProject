package controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import org.slf4j.LoggerFactory;;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;


public class SidebarController implements Initializable{

    protected static final org.slf4j.Logger log = LoggerFactory.getLogger(SidebarController.class);

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        loadUI("search");
    }

    @FXML
    private BorderPane borderPane;


    @FXML
    public void close() {
        Stage stage = (Stage) borderPane.getScene().getWindow();
        stage.close();
        log.info("Closed the app");
    }


    @FXML
    public void ui2() throws IOException {
        loadUI("upload");
        log.info("Chose the upload scene");
    }

    @FXML
    public void ui3() {
        loadUI("search");
        log.info("Chose the search scene");
    }


    private void loadUI(String ui){
        Parent root = null;

        try {
            root = FXMLLoader.load(getClass().getResource("/" + ui + ".fxml"));
        } catch (IOException ex){
            Logger.getLogger(SidebarController.class.getName()).log(Level.SEVERE, null, ex);
        }
        borderPane.setCenter(root);
    }

}
