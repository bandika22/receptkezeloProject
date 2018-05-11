package app;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import model.DBManager;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class Main extends Application {

    private double x, y;

    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("/sidebar.fxml"));

        Scene scene = new Scene(root);
        scene.setFill(Color.TRANSPARENT);

        Image close = new Image("/icon/Crystal_Project_Exit.png");
        ImageView piece = new ImageView(close);
        piece.setX(10);
        piece.setY(10);


        /*root.setOnMousePressed(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                x = mouseEvent.getSceneX();
                y = mouseEvent.getSceneY();
            }
        });

        root.setOnMouseDragged(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                primaryStage.setX(mouseEvent.getSceneX() - x);
                primaryStage.setY(mouseEvent.getSceneY() - y);

            }
        });*/

        primaryStage.setScene(scene);
        primaryStage.initStyle(StageStyle.TRANSPARENT);
        primaryStage.show();

    }

    public static void main(String[] args) {
        launch(args);

    /*    try {
            String dbURL2 = "jdbc:derby:receptKezeloDB;";
            Connection conn2 = DriverManager.getConnection(dbURL2);
            if (conn2 != null) {
                System.out.println("Connected to database #2");
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }*/

    }

}

