package controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;


public class SidebarController implements Initializable{

    @FXML
    private ImageView imageView;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        loadUI("search");
    }

    @FXML
    private BorderPane borderPane;


    @FXML
    public void close(MouseEvent mouseEvent) {
        Stage stage = (Stage) borderPane.getScene().getWindow();
        stage.close();
    }


    @FXML
    public void ui2(MouseEvent mouseEvent) throws IOException {
        loadUI("upload");
    }

    @FXML
    public void ui3(MouseEvent mouseEvent) {
        loadUI("search");
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
