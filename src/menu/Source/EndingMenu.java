package menu.Source;

import graphics.Sprite;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import main.BombermanGame;

import java.io.FileInputStream;
import java.io.InputStream;

import static graphics.Sprite.SCALED_SIZE;
import static main.BombermanGame.*;
import static map.CreateMap.createMap;

public class EndingMenu {
    public static void lose(Stage stage) {
        System.out.println("You lose");
        bgMusic.stop();
        Button buttonRePlay = new Button();

        buttonRePlay.setStyle("-fx-background-color: transparent");
        buttonRePlay.setPrefSize(166, 66);
        buttonRePlay.setTranslateX(SCALED_SIZE * 30 / 2 - 166 / 2 - 106);
        buttonRePlay.setTranslateY(SCALED_SIZE * 15 / 2 + 66 / 2 + 20);
        InputStream inRe = null;
        try {
            inRe = new FileInputStream("res/replay.png");
        } catch (Exception e) {
            e.getMessage();
        }
        ImageView imgR = new ImageView();
        imgR.setFitWidth(120);
        imgR.setFitHeight(60);
        imgR.setImage(new Image(inRe));
        buttonRePlay.setGraphic(imgR);
        buttonRePlay.setOnAction(e -> {
            createMap(stage, level);
        });

        // Button for Exit
        Button buttonExit = new Button();
        buttonExit.setStyle("-fx-background-color: transparent");
        buttonExit.setPrefSize(166, 66);
        buttonExit.setTranslateX(SCALED_SIZE * 30 / 2 - 166 / 2 + 96);
        buttonExit.setTranslateY(SCALED_SIZE * 15 / 2 + 66 / 2 + 20);

        InputStream exitEnd = null;
        try {
            exitEnd = new FileInputStream("res/exitMenu.png");
        } catch (Exception e) {
            e.getMessage();
        }
        ImageView imgE = new ImageView();
        imgE.setFitWidth(120);
        imgE.setFitHeight(60);
        imgE.setImage(new Image(exitEnd));
        buttonExit.setGraphic(imgE);
        buttonExit.setOnAction(e -> {
            typeMenu = MENU.EXIT;
        });

        // Background
        InputStream menuEnd = null;
        try {
            menuEnd = new FileInputStream("res/endgame.jpeg");

        } catch (Exception e) {
            e.getMessage();
        }
        ImageView endGame = new ImageView();

        endGame.setX(0);
        endGame.setY(0);
        endGame.setFitHeight(SCALED_SIZE * 15);
        endGame.setFitWidth(SCALED_SIZE * 30);
        endGame.setImage(new Image(menuEnd));
        root = new Group(endGame);
        root.getChildren().addAll(buttonRePlay, buttonExit);
        Scene sc = new Scene(root, SCALED_SIZE * 30, SCALED_SIZE * 15, Color.GREEN);
        stage.setScene(sc);
        stage.show();
    }

    public static void win(Stage stage) {
        System.out.println("You win!");
        bgMusic.stop();

        // Button for Exit
        Button buttonExit = new Button();
        buttonExit.setStyle("-fx-background-color: transparent");
        buttonExit.setPrefSize(166, 66);
        buttonExit.setTranslateX(SCALED_SIZE * 30 / 2 - 166 / 2 + 96);
        buttonExit.setTranslateY(SCALED_SIZE * 15 / 2 + 66 / 2 + 20);

        InputStream exitEnd = null;
        try {
            exitEnd = new FileInputStream("res/exitMenu.png");
        } catch (Exception e) {
            e.getMessage();
        }
        ImageView imgE = new ImageView();
        imgE.setFitWidth(120);
        imgE.setFitHeight(60);
        imgE.setImage(new Image(exitEnd));
        buttonExit.setGraphic(imgE);
        buttonExit.setOnAction(e -> {
            typeMenu = MENU.EXIT;
        });

        // Background
        InputStream menuWin = null;
        try {
            menuWin = new FileInputStream("res/endgame.jpeg");

        } catch (Exception e) {
            e.getMessage();
        }
        ImageView endGame = new ImageView();

        endGame.setX(0);
        endGame.setY(0);
        endGame.setFitHeight(SCALED_SIZE * 15);
        endGame.setFitWidth(SCALED_SIZE * 30);
        endGame.setImage(new Image(menuWin));
        root = new Group(endGame);
        root.getChildren().add(buttonExit);
        Scene sc = new Scene(root, SCALED_SIZE * 30, SCALED_SIZE * 15, Color.GREEN);
        stage.setScene(sc);
        stage.show();
    }

}
