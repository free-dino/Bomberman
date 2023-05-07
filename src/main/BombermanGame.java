package main;

import audio.Sound;
import control.KeyListener;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Group;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.effect.DropShadow;
import javafx.scene.effect.Effect;
import javafx.stage.Stage;
import entities.character.bomber.Bomber;
import entities.Entity;
import map.CreateMap;
import menu.Source.*;

import java.util.ArrayList;
import java.util.List;

public class BombermanGame extends Application {
    public static int WIDTH;
    public static int HEIGHT;
    public static int level;
    public static int MAX_SCORE;
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
    public static KeyListener keyListener;
    public static GraphicsContext gc;
    public static Canvas canvas;

    public static Stage window;

    public static boolean playGame = true;
    public static Sound bgMusic;
    public static MENU typeMenu = MENU.START;

    public enum MENU {START, PLAYING, PAUSE, END, NEXT_LV, EXIT}


    public static void main(String[] args) {
        Application.launch(BombermanGame.class);
        System.out.println("Done!");
    }

    public void gameLoop(Stage stage) {
        switch (typeMenu) {
            case START:
                StartingMenu.play(stage);
                break;

            case PLAYING:
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

    @Override
    public void start(Stage stage) {
        if (bgMusic != null) bgMusic.stop();
        window = stage;

        level = 1;
        new CreateMap(stage, level);
        stage.setResizable(false);
        stage.show();

        AnimationTimer timer = new AnimationTimer() {
            private long lastUpdate = 0;

            @Override
            public void handle(long now) {
                if (playGame == true) {
                    StartingMenu.play(window);
                    playGame = false;
                }
                render();
                update();


                long frameTime = (now - lastUpdate) / 1000000;
                if (frameTime < FPS_GAME) {
                    try {
                        Thread.sleep(FPS_GAME - frameTime);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
            }
        };
        timer.start();

    }


    public void update() {
        entities.forEach(Entity::update);
        enemies.forEach(Entity::update);
    }

    public void render() {
        gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
        stillObjects.forEach(g -> g.render(gc));
        entities.forEach(g -> g.render(gc));
        enemies.forEach(g -> g.render(gc));
        bomber.render(gc);
    }

}
