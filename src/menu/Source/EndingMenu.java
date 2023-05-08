package menu.Source;

import audio.Sound;
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
import static main.BombermanGame.bgMusic;
import static main.BombermanGame.root;
import map.CreateMap;

public class EndingMenu {
    public static void lose(Stage stage) {
        bgMusic.stop();
        bgMusic = Sound.ending;
        bgMusic.play();
        // Button for Replay
        Button rePlay = new Button();

        rePlay.setStyle("-fx-background-color: transparent");
        rePlay.setPrefSize(166, 66);
        rePlay.setTranslateX(Sprite.SCALED_SIZE * 15 - 170 / 2 - 20);
        rePlay.setTranslateY(Sprite.SCALED_SIZE * 10 - 10);
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
            new CreateMap(stage, BombermanGame.level);
        });

        // Button for Exit
        Button exitButton = new Button();
        exitButton.setStyle("-fx-background-color: transparent");
        exitButton.setPrefSize(166, 66);
        exitButton.setTranslateX(Sprite.SCALED_SIZE * 15 - 170 / 2 + 20);
        exitButton.setTranslateY(Sprite.SCALED_SIZE * 10 - 10);
        InputStream exitEnd = null;
        try {
            exitEnd = new FileInputStream("res/exit.png");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        ImageView imgE = new ImageView();
        imgE.setFitWidth(120);
        imgE.setFitHeight(60);
        imgE.setImage(new Image(exitEnd));
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
        root.getChildren().addAll(rePlay, exitButton);
        Scene sc = new Scene(root, SCALED_SIZE * 30, SCALED_SIZE * 15, Color.GREEN);
        stage.setScene(sc);
        stage.show();

    }

    public static void win(Stage stage) {
        bgMusic.stop();
        bgMusic = Sound.win;
        bgMusic.play();

        Button exitButton = new Button();
        exitButton.setStyle("-fx-background-color: transparent");
        exitButton.setPrefSize(166, 66);
        exitButton.setTranslateX(Sprite.SCALED_SIZE * 15 - 170 / 2 );
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
        root.getChildren().addAll( exitButton);
        Scene sc = new Scene(root, SCALED_SIZE * 30, SCALED_SIZE * 15, Color.GREEN);
        stage.setScene(sc);
        stage.show();
    }

}
