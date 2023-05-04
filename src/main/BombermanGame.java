package main;

import control.KeyListener;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.stage.Stage;
import entities.character.bomber.Bomber;
import entities.Entity;
import map.MapLevel1;
import menu.Source.StartingMenu;

import java.util.ArrayList;
import java.util.List;

public class BombermanGame extends Application {
    public static int WIDTH;
    public static int HEIGHT;
    public static int level;
    public static final int MAX_LEVEL = 3;
    public static int MAX_SCORE;
    public static long FPS_GAME = 1000 / 60;

    public static List<Entity> entities = new ArrayList<>();
    public static List<Entity> stillObjects = new ArrayList<>();
    public static List<Entity> enemies = new ArrayList<>();
    public static List<Entity> flames = new ArrayList<>();
    public static Entity[][] table;     // Mảng 2 chiều các vật thể hiện ra.
    public static Entity[][] hiddenTable; // Mảng 2 chiều các vật thể bị che đi.
    public static Bomber bomber;

    public static Group root = null;
    public static KeyListener keyListener;
    public static GraphicsContext gc;
    public static Canvas canvas;

    public static Stage window;

    public enum STATE {START, SINGLE, PAUSE, END, NEXT_LV, EXIT}

    public static STATE gameState = STATE.START;
    public static boolean isPlaying = true;


    public static void main(String[] args) {
        Application.launch(BombermanGame.class);
        System.out.println("Done!");
    }

    // hàm này cho tiện tạo map khi win.
    public static String convertToString(int level) {
        return System.getProperty("user.dir") + "res/levels/Level" + level + ".txt";
    }


    @Override
    public void start(Stage stage) {

        window = stage;

        new MapLevel1(); // Test tạo map
        level = 1;


        stage.setResizable(false);
        stage.show();

        AnimationTimer timer = new AnimationTimer() {
            private long lastUpdate = 0;

            @Override
            public void handle(long now) {
                if (isPlaying == true) {
                    StartingMenu.play(window);
                    isPlaying = false;
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
