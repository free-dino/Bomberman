package main;

import audio.Sound;
import control.KeyListener;
import graphics.Sprite;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Label;
import javafx.scene.effect.DropShadow;
import javafx.scene.effect.Effect;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import entities.character.bomber.Bomber;
import entities.Entity;
import map.CreateMap;
import menu.Source.*;

import java.util.ArrayList;
import java.util.List;

import static menu.Source.PlayingMenu.*;

public class BombermanGame extends Application {
    public static int WIDTH;
    public static int HEIGHT;
    public static int level = 1;
    public static int MAX_LEVEL = 3;
    public static long FPS_GAME = 1000 / 60;

    public static List<Entity> entities = new ArrayList<>();
    public static List<Entity> stillObjects = new ArrayList<>();
    public static List<Entity> enemies = new ArrayList<>();
    public static List<Entity> flames = new ArrayList<>();
    public static Entity[][] table;     // Mảng 2 chiều các vật thể hiện ra.
    public static Entity[][] hiddenTable; // Mảng 2 chiều các vật thể bị che đi.
    public static Bomber bomber;
    public final Effect shadow = new DropShadow();
    public static Group root = null;
    public static Scene scene = null;
    public static Stage window = null;
    public static KeyListener keyListener;
    public static GraphicsContext gc;
    public static Canvas canvas;


    public static boolean playGame = true;
    public static Sound bgMusic;
    public static MENU typeMenu = MENU.START;

    public enum MENU {START, PLAYING, PAUSE, END, NEXT_LEVEL, EXIT}

    public static boolean isEnd = false;
    public static boolean newGame = true;


    public static void main(String[] args) {
        Application.launch(BombermanGame.class);
        System.out.println("Done!");
    }

    @Override
    public void start(Stage stage) {
        window = stage;
        AnimationTimer timer = new AnimationTimer() {
            @Override
            public void handle(long now) {
                if (newGame) {
                    gameLoop(stage);
                    newGame = false;
                }
                if (typeMenu == MENU.EXIT) {
                    Platform.exit();
                }
            }
        };
        timer.start();
    }


    public static void update() {
        entities.forEach(Entity::update);
        enemies.forEach(Entity::update);
    }

    public static void render(Stage stage) {
        switch (typeMenu) {
            case PLAYING:
                gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
                stillObjects.forEach(g -> g.render(gc));
                entities.forEach(g -> g.render(gc));
                enemies.forEach(g -> g.render(gc));
                bomber.render(gc);

                root.getChildren().remove(healthLabel);
                root.getChildren().remove(levelLabel);

                healthLabel = new Label("Health: " + Bomber.bomber_HP);
                healthLabel.setFont(Font.font("Arial", FontWeight.BOLD, 20));
                healthLabel.setTextFill(Color.RED);
                healthLabel.setLayoutX(10);
                healthLabel.setLayoutY(Sprite.SCALED_SIZE * HEIGHT + 10);
                root.getChildren().add(healthLabel);

                levelLabel = new Label("Level: " + BombermanGame.level);
                levelLabel.setFont(Font.font("Arial", FontWeight.BOLD, 20));
                levelLabel.setTextFill(Color.GREEN);
                levelLabel.setLayoutX(Sprite.SCALED_SIZE * WIDTH / 2 - 40);
                levelLabel.setLayoutY(Sprite.SCALED_SIZE * HEIGHT + 10);
                root.getChildren().add(levelLabel);

                break;

            case PAUSE:
                break;

            case NEXT_LEVEL:
                if (level < MAX_LEVEL) {
                    typeMenu = MENU.PLAYING;
                    new CreateMap(stage, level++);
                } else if (!isEnd) {
                    EndingMenu.lose(stage);
                    isEnd = true;
                }
                break;
            case END:
                if (!isEnd) {
                    EndingMenu.win(stage);
                    isEnd = true;
                }
                break;
            default:
                System.out.println("Lỗi ở render trong main");
                break;
        }
    }

    public void gameLoop(Stage stage) {
        switch (typeMenu) {
            case START:
                StartingMenu.start(stage);
                break;

            case PLAYING:
                PlayingMenu.setup(stage);
                break;

            case PAUSE:
                break;

            case EXIT:
                Platform.exit();
                break;
            default:
                throw new IllegalArgumentException("Invalid game state");
        }
    }
}
