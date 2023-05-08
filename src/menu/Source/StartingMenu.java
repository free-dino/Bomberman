package menu.Source;

import audio.Sound;
import javafx.application.Platform;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.effect.Bloom;
import javafx.scene.paint.Color;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.control.Button;


import javafx.stage.Stage;
import main.BombermanGame;


import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

import static graphics.Sprite.SCALED_SIZE;
import static main.BombermanGame.*;

public class StartingMenu extends Button {
    public static void start(Stage stage) {
        bgMusic = Sound.main_bgm;
        bgMusic.loop();
        Button buttonStart = new Button();
        buttonStart.setStyle("-fx-background-color: transparent"); // để hiện cái nút k bị đè bởi khung
        buttonStart.setPrefSize(166, 66);
        buttonStart.setTranslateX(SCALED_SIZE * 30 / 2 - 166 / 2 - 106);
        buttonStart.setTranslateY(SCALED_SIZE * 15 / 2 + 66 / 2 + 20);

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
        buttonStart.setGraphic(imgView);
        buttonStart.setOnMouseEntered(e -> buttonStart.setEffect(new Bloom()));
        buttonStart.setOnMouseExited(e -> buttonStart.setEffect(null));
        buttonStart.setOnAction(e -> {
            typeMenu = MENU.PLAYING;
            PlayingMenu.setup(stage);
        });


        Button buttonExit = new Button();
        buttonExit.setStyle("-fx-background-color: transparent");
        buttonExit.setPrefSize(166, 66);
        buttonExit.setTranslateX(SCALED_SIZE * 30 / 2 - 166 / 2 + 96);
        buttonExit.setTranslateY(SCALED_SIZE * 15 / 2 + 66 / 2 + 20);
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
        buttonExit.setGraphic(ExitImage);
        buttonExit.setOnMouseEntered(e -> ExitImage.setEffect(new Bloom()));
        buttonExit.setOnMouseExited(e -> ExitImage.setEffect(null));
        buttonExit.setOnAction(e -> {
            typeMenu = MENU.EXIT;
        });

        // menu chính
        InputStream menu = null;
        try {
            menu = new FileInputStream("res/menu.jpg");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        ImageView menuView = new ImageView();
        menuView.setX(0);
        menuView.setY(0);
        menuView.setFitHeight(SCALED_SIZE * 15);
        menuView.setFitWidth(SCALED_SIZE * 30);
        menuView.setImage(new Image(menu));
        BombermanGame.root = new Group(menuView);
        BombermanGame.root.getChildren().addAll(buttonStart, buttonExit);
        Scene scene = new Scene(BombermanGame.root, SCALED_SIZE * 30, SCALED_SIZE * 15, Color.GREEN);
        stage.setTitle("BOMBERMAN");
        stage.setScene(scene);
        stage.show();
    }
}