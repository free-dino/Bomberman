package main;

import control.KeyListener;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.effect.DropShadow;
import javafx.scene.effect.Effect;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import entities.character.bomber.Bomber;
import entities.Entity;
import graphics.Sprite;
import map.CreateMap;
import map.MapLevel1;
import map.MapLevel2;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class BombermanGame extends Application {
    public static int WIDTH;
    public static int HEIGHT;
    public static int level;
    public static int MAX_SCORE;
    public static long FPS_GAME = 1000 / 60;

    public static List<Entity> entities = new ArrayList<>();
    public static List<Entity> stillObjects = new ArrayList<>();
    public static List<Entity> enemies = new ArrayList<>();
    public static Entity[][] table;     // Mảng 2 chiều các vật thể hiện ra.
    public static Entity[][] hiddenTable; // Mảng 2 chiều các vật thể bị che đi.
    public static Bomber bomber;
    public final Effect shadow = new DropShadow();
    Group root = null;
    public static KeyListener keyListener;
    public static GraphicsContext gc;
    public static Canvas canvas;
    private Text textLife = null;
    Text textLevel = null;
    private Text textScore = null;
    public static Stage window;

    public static boolean playGame = true;


    public static void main(String[] args) {
        Application.launch(BombermanGame.class);
        System.out.println("Done!");
    }

    public void menu(Stage stage) {
        playGame = false;
        Button Start = new Button();
        Button StartAI = new Button();
        Button Exit = new Button();
// bắt đầu với Start thường trước
        Start.setStyle("-fx-background-color: transparent; ");
        Start.setPrefSize(166, 66);
        Start.setTranslateX(Sprite.SCALED_SIZE * 30 / 2 - 166 / 2);
        Start.setTranslateY(Sprite.SCALED_SIZE * 15 / 2  + 66/2 - 20 );
        InputStream input = null;
        try {
            input = new FileInputStream("res/start.png");
        } catch (Exception e) {
            e.getMessage();
        }
        Image img = new Image(input);
        ImageView view = new ImageView();
        view.setFitHeight(66);
        view.setFitWidth(166);
        view.setImage(img);

        Start.setOnMouseEntered(e -> Start.setEffect(shadow));
        Start.setOnMouseExited(e -> Start.setEffect(null));
        Start.setOnAction(e -> {
            CreateMap createMap = new CreateMap("res/levels/Level1.txt");



        });
        StartAI.setStyle("-fx-background-color: transparent; ");
        StartAI.setPrefSize(166, 66);
        StartAI.setTranslateX(Sprite.SCALED_SIZE * 30 / 2 - 166 / 2);
        StartAI.setTranslateY(Sprite.SCALED_SIZE * 15 / 2 + 66 / 2 + 20);
        try {
            input = new FileInputStream("res/Ai_button.png");
        } catch (FileNotFoundException e) {
            e.getMessage();
        }
        img = new Image(input);
        view = new ImageView();
        view.setFitHeight(66);
        view.setFitWidth(166);
        view.setImage(img);
        StartAI.setGraphic(view);
        StartAI.setOnMouseEntered(e -> StartAI.setEffect(shadow));
        StartAI.setOnMouseExited(e -> StartAI.setEffect(null));
        StartAI.setOnAction(event -> {
//            gameState = STATE.EXIT;
            CreateMap createMap = new CreateMap("res/levels/Level1.txt");




//            Platform.runLater(()->{
//                root.getChildren().removeAll(start_button, exit_button);
//            });
        });
        Exit.setStyle("-fx-background-color: transparent; ");
//        button.setText("Single player");
        Exit.setPrefSize(166, 66);
        Exit.setTranslateX(Sprite.SCALED_SIZE * 30 / 2 - 166 / 2);
        Exit.setTranslateY(Sprite.SCALED_SIZE * 15 / 2 + 66 / 2 + 20 + 80);
        try {
            new MapLevel1();

        } catch (Exception e) {
            e.getMessage();
        }
        img = new Image(input);
        view = new ImageView();
        view.setFitHeight(66);
        view.setFitWidth(166);
        view.setImage(img);
        Exit.setGraphic(view);
        Exit.setOnMouseEntered(e -> Exit.setEffect(shadow));
        Exit.setOnMouseExited(e -> Exit.setEffect(null));
        Exit.setOnAction(event -> {

//            gameState = STATE.SINGLE;
//            Bomber.AI = true;
//            Platform.runLater(()->{
//                root.getChildren().removeAll(start_button, exit_button);
//            });
        });
        try {
            input = new FileInputStream("res/menu.jpeg");
        } catch (Exception e) {
            e.getMessage();
        }
        Image image = new Image(input);
        ImageView imgView = new ImageView();
        imgView.setImage(image);
        imgView.setX(0);
        imgView.setY(0);
        imgView.setFitHeight(Sprite.SCALED_SIZE * 15);
        imgView.setFitWidth(Sprite.SCALED_SIZE * 30);
        root = new Group(imgView);
        root.getChildren().addAll(Start, StartAI, Exit);
        Scene scene = new Scene(root, Sprite.SCALED_SIZE * 30, Sprite.SCALED_SIZE * 15, Color.GREEN);
        stage.setTitle("BOMBERMAN");
        stage.setScene(scene);
        stage.show();
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
                if (playGame == true) {
                    menu(stage);
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
        root.getChildren().remove(textLife);
        root.getChildren().remove(textScore);
        root.getChildren().remove(textLevel);

        Font font = new Font("Arial", 15);

        textLife = new Text(10, Sprite.SCALED_SIZE * HEIGHT + 40, "LIFE: " + Bomber.isProtected());
        textLife.setFont(font);
        textLife.setFill(Color.PINK);

        textScore = new Text(100, Sprite.SCALED_SIZE * HEIGHT + 40, "SCORE: " + (MAX_SCORE - enemies.size()) * 100);
        textScore.setFont(font);
        textScore.setFill(Color.PINK);

        textLevel = new Text(Sprite.SCALED_SIZE * WIDTH / 2 - 40, Sprite.SCALED_SIZE * HEIGHT + 40, "LEVEL: " + level);
        textLevel.setFont(font);
        textLevel.setFill(Color.PINK);

        root.getChildren().add(textLevel);
        root.getChildren().add(textLife);
        root.getChildren().add(textScore);
    }

}
