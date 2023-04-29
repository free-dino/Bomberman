package menu.Source;

import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.control.Button;


import javafx.stage.Stage;

import main.BombermanGame;
import map.CreateMap;
import main.BombermanGame.*;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

import static graphics.Sprite.SCALED_SIZE;

public class MENU extends Button {


    public static void play(Stage stage) {
        Button Start = new Button();
        Start.setStyle("-fx-background-color: transparent"); // để hiện cái nút k bị đè bởi khung
        Start.setPrefSize(166, 66);
        Start.setTranslateX(SCALED_SIZE * 30 / 2 - 166 / 2 - 30);
        Start.setTranslateY(SCALED_SIZE * 15 / 2 + 66 / 2 - 20);

        InputStream input = null;
        try {
            input = new FileInputStream("res/start.png");
        } catch (Exception e) {
            e.getMessage();
        }
        ImageView imgView = new ImageView();
        imgView.setFitHeight(70);
        imgView.setFitWidth(200);
        imgView.setImage(new Image(input));
        Start.setGraphic(imgView);
        Start.setOnAction(e -> {
            CreateMap createMap = new CreateMap("res/levels/Level1.txt");

        });


        Button Exit = new Button();
        Exit.setStyle("-fx-background-color: transparent");
        Exit.setPrefSize(166, 66);
        Exit.setTranslateX(SCALED_SIZE * 30 / 2 - 166 / 2 - 30);
        Exit.setTranslateY(SCALED_SIZE * 15 / 2 + 66 / 2 + 60);
        InputStream exitInput = null;
        try {
            exitInput = new FileInputStream("res/exit.png");
        } catch (FileNotFoundException e) {
            e.getMessage();
        }
        ImageView ExitImage = new ImageView();
        ExitImage.setFitHeight(70);
        ExitImage.setFitWidth(200);
        ExitImage.setImage(new Image(exitInput));
        Exit.setGraphic(ExitImage);

        // menu chính
        InputStream menu =  null;
        try {
            menu = new FileInputStream("res/menu.jpeg");


        } catch (Exception e) {
            e.getMessage();
        }
        ImageView menuView = new ImageView();
        menuView.setX(0);
        menuView.setY(0);
        menuView.setFitHeight(SCALED_SIZE * 15);
        menuView.setFitWidth(SCALED_SIZE * 30);
        menuView.setImage(new Image(menu));
        BombermanGame.root = new Group(menuView);
        BombermanGame.root.getChildren().addAll(Start, Exit);
        Scene scene = new Scene(BombermanGame.root, SCALED_SIZE * 30, SCALED_SIZE * 15, Color.GREEN);
        stage.setTitle("BOMBERMAN");
        stage.setScene(scene);
        stage.show();


    }
}