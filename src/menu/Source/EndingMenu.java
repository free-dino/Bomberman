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
import static main.BombermanGame.root;
import static main.BombermanGame.typeMenu;
import static main.BombermanGame.MENU;
import static map.CreateMap.createMap;

public class EndingMenu {
    public static void lose(Stage stage) {
        // Button for Replay
        Button rePlay = new Button();

        rePlay.setStyle("-fx-background-color: transparent");
        rePlay.setPrefSize(166, 66);
        rePlay.setTranslateX(SCALED_SIZE * 30 / 2 - 166 / 2 - 106);
        rePlay.setTranslateY(SCALED_SIZE * 15 / 2 + 66 / 2 + 20);
        InputStream inRe = null;
        try {
            inRe = new FileInputStream("res/replay.png");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        ImageView imgR = new ImageView();
        imgR.setFitWidth(120);
        imgR.setFitHeight(60);
        imgR.setImage(new Image(inRe));
        rePlay.setGraphic(imgR);
        rePlay.setOnAction(e -> {
            createMap(stage, BombermanGame.level);
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
            System.out.println(e.getMessage());
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
            System.out.println(e.getMessage());
        }
        ImageView endGame = new ImageView();
        endGame.setX(0);
        endGame.setY(0);
        endGame.setFitHeight(SCALED_SIZE * 15);
        endGame.setFitWidth(SCALED_SIZE * 30);
        endGame.setImage(new Image(menuEnd));
        root = new Group(endGame);
        root.getChildren().addAll(rePlay, buttonExit);
        Scene sc = new Scene(root, SCALED_SIZE * 30, SCALED_SIZE * 15, Color.GREEN);
        stage.setScene(sc);
        stage.show();

    }

    public static void win(Stage stage) {

        Button exitButton = new Button();
        exitButton.setStyle("-fx-background-color: transparent");
        exitButton.setPrefSize(166, 66);
        exitButton.setTranslateX(Sprite.SCALED_SIZE * 15 - 170 / 2);
        exitButton.setTranslateY(Sprite.SCALED_SIZE * 10 - 10);
        InputStream exitEnd = null;
        try {
            exitEnd = new FileInputStream("res/exit.png");
        } catch (Exception e) {
            e.getMessage();
        }
        ImageView imgE = new ImageView();
        imgE.setFitWidth(120);
        imgE.setFitHeight(60);
        imgE.setImage(new Image(exitEnd));
        // Menu khi thua
        InputStream menuEnd = null;
        try {
            menuEnd = new FileInputStream("res/win.jpeg");

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
        root.getChildren().addAll(exitButton);
        Scene sc = new Scene(root, SCALED_SIZE * 30, SCALED_SIZE * 15, Color.GREEN);
        stage.setScene(sc);
        stage.show();
    }

}
