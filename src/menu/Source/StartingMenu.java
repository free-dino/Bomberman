package menu.Source;

import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.effect.Reflection;
import javafx.scene.paint.Color;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.control.Button;


import javafx.stage.Stage;

import main.BombermanGame;
import map.CreateMap;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

import static graphics.Sprite.SCALED_SIZE;

public class StartingMenu extends Button {


    public static void play(Stage stage) {
        Button Start = new Button();
        Start.setStyle("-fx-background-color: transparent"); // để hiện cái nút k bị đè bởi khung
        Start.setPrefSize(166, 66);
        Start.setTranslateX(SCALED_SIZE * 30 / 2 - 166 / 2 - 106);
        Start.setTranslateY(SCALED_SIZE * 15 / 2 + 66 / 2 + 20);

        InputStream input = null;
        try {
            input = new FileInputStream("res/start.png");
        } catch (Exception e) {
            e.getMessage();
        }
        ImageView imgView = new ImageView();
        imgView.setFitHeight(70);
        imgView.setFitWidth(180);
        imgView.setImage(new Image(input));
        Start.setGraphic(imgView);
        Start.setOnMouseEntered(e-> Start.setEffect(new Reflection()));
        Start.setOnMouseExited(e-> Start.setEffect(null));
        Start.setOnAction(e -> {
            CreateMap createMap = new CreateMap("res/levels/Level1.txt");

        });


        Button Exit = new Button();
        Exit.setStyle("-fx-background-color: transparent");
        Exit.setPrefSize(166, 66);
        Exit.setTranslateX(SCALED_SIZE * 30 / 2 - 166 / 2 + 96);
        Exit.setTranslateY(SCALED_SIZE * 15 / 2 + 66 / 2 +20);
        InputStream exitInput = null;
        try {
            exitInput = new FileInputStream("res/exitMenu.png");
        } catch (FileNotFoundException e) {
            e.getMessage();
        }
        ImageView ExitImage = new ImageView();
        ExitImage.setFitHeight(70);
        ExitImage.setFitWidth(180);
        ExitImage.setImage(new Image(exitInput));
        Exit.setOnMouseEntered(e -> ExitImage.setEffect(new Reflection()));
        Exit.setOnMouseExited(e -> ExitImage.setEffect(null));
        Exit.setGraphic(ExitImage);

        // menu chính
        InputStream menu = null;
        try {
            menu = new FileInputStream("res/menu.jpg");


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