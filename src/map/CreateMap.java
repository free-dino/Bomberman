package map;

import audio.Sound;
import control.KeyListener;
import entities.Entity;
import entities.block.Brick;
import entities.block.Grass;
import entities.block.Wall;
import entities.bomb.Bomb;
import entities.character.bomber.Bomber;
import entities.character.enemy.Balloom;
import entities.character.enemy.Kondoria;
import entities.character.enemy.Minvo;
import entities.character.enemy.Oneal;
import entities.item.BombItem;
import entities.item.FlameItem;
import entities.item.PortalItem;
import entities.item.SpeedItem;
import graphics.Sprite;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import main.BombermanGame;

import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Scanner;

import static main.BombermanGame.*;

/**
 * Khởi tạo 1 map với đầu vào là 1 string: string Level.
 */
public class CreateMap {

    public static void createMap(Stage stage, int level) {
        if (bgMusic != null) bgMusic.stop();
        Bomb.cnt = 0;
        entities = new ArrayList<>();
        enemies = new ArrayList<>();
        flames = new ArrayList<>();
        stillObjects = new ArrayList<>();
        bgMusic = Sound.main_bgm;
        bgMusic.loop();
        System.out.println(System.getProperty("user.dir"));
        final File fileName = new File("res/levels/Level" + level + ".txt");
        try (FileReader inputFile = new FileReader(fileName)) {
            Scanner scanner = new Scanner(inputFile);

            scanner.nextInt();
            HEIGHT = scanner.nextInt();
            WIDTH = scanner.nextInt();

            table = new Entity[WIDTH][HEIGHT];
            hiddenTable = new Entity[WIDTH][HEIGHT];

            scanner.nextLine();
            for (int i = 0; i < HEIGHT; i++) {
                String line = scanner.nextLine();
                for (int j = 0; j < WIDTH; j++) {
                    Entity stillObject = null;
                    Entity object = null;
                    Entity hiddenObject = null;
                    Entity enemy = null;
                    stillObjects.add(new Grass(j, i, Sprite.grass.getFxImage));
                    switch (line.charAt(j)) {
                        // Tiles:
                        case '#':
                            stillObject = new Wall(j, i, Sprite.wall.getFxImage);
                            break;
                        case '*':
                            object = new Brick(j, i, Sprite.brick.getFxImage);
                            break;
                        case 'p':
                            object = new Bomber(j, i, Sprite.player_right.getFxImage(), keyListener);
                            bomber = (Bomber) object;
                            break;
                        case '1':
                            enemy = new Balloom(j, i, Sprite.balloom_right1.getFxImage);
                            break;
                        case '2':
                            enemy = new Oneal(j, i, Sprite.oneal_right1.getFxImage);
                            break;
                        case '3':
                            enemy = new Minvo(j, i, Sprite.minvo_right1.getFxImage);
                            break;
                        case '4':
                            enemy = new Kondoria(j, i, Sprite.kondoria_right1.getFxImage);
                            break;
                        // Items:
                        case 'f':
                            hiddenObject = new FlameItem(j, i, Sprite.powerup_flames.getFxImage);
                            System.out.println("Flame Item in " + j + " " + i);
                            break;
                        case 's':
                            hiddenObject = new SpeedItem(j, i, Sprite.powerup_speed.getFxImage);
                            System.out.println("Speed Item in " + j + " " + i);
                            break;
                        case 'b':
                            hiddenObject = new BombItem(j, i, Sprite.powerup_bombs.getFxImage);
                            System.out.println("Bomb Item in " + j + " " + i);
                            break;
                        case 'x':
                            hiddenObject = new PortalItem(j, i, Sprite.portal.getFxImage);
                            System.out.println("Portal Item in " + j + " " + i);
                            break;
                    }
                    if (stillObject != null) {
                        stillObjects.add(stillObject);
                        table[j][i] = stillObject;
                    } else if (object != null) {
                        entities.add(object);
                        table[j][i] = object;
                    } else if (hiddenObject != null) {
                        object = new Brick(j, i, Sprite.brick.getFxImage());
                        entities.add(object);
                        table[j][i] = object;
                        hiddenTable[j][i] = hiddenObject;
                    } else if (enemy != null) {
                        enemies.add(enemy);
                        table[j][i] = enemy;
                    }
                }
            }
            canvas = new Canvas(Sprite.SCALED_SIZE * WIDTH, Sprite.SCALED_SIZE * HEIGHT + 40);
            gc = canvas.getGraphicsContext2D();

            // Tao root container
            root = new Group();
            root.getChildren().add(canvas);

            scene = new Scene(root, Color.BLACK);
            keyListener = new KeyListener(scene);
            stage.setScene(scene);
            scanner.close();
        } catch (Exception e) {
            System.out.println("Error in CreateMap.java");
            System.out.println(e.getMessage());
        }
    }
}
