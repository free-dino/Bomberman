package map;

import entities.Entity;
import entities.block.Brick;
import entities.block.Grass;
import entities.block.Wall;
import entities.bomb.Bomb;
import entities.character.bomber.Bomber;
import entities.character.enemy.Balloom;
import entities.character.enemy.Oneal;
import graphics.Sprite;

import java.io.File;
import java.io.FileReader;
import java.util.Scanner;

import static main.BombermanGame.*;

/**
 * Khởi tạo 1 map với đầu vào là 1 string: string Level.
 */
public class CreateMap {
    public CreateMap(String stringLevel) {
        System.out.println(System.getProperty("user.dir"));
        final File fileName = new File(stringLevel);
        try (FileReader inputFile = new FileReader(fileName)) {
            Scanner scanner = new Scanner(inputFile);

            level = scanner.nextInt();
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
                    if (line.charAt(j) != '#') {
                        stillObjects.add(new Grass(j, i, Sprite.grass.getFxImage()));
                    }
                    switch (line.charAt(j)) {
                        // Tiles:
                        case '#':
                            stillObject = new Wall(j, i, Sprite.wall.getFxImage());
                            break;
                        case '*':
                            object = new Brick(j, i, Sprite.brick.getFxImage());
                            break;
                        case 'p':
                            object = new Bomber(j, i, Sprite.player_right.getFxImage(), keyListener);
                            bomber = (Bomber) object;
                            break;
                        case '1':
                            enemy = new Balloom(j, i, Sprite.balloom_right1.getFxImage());
                            break;
                        case '2':
                            enemy = new Oneal(j, i, Sprite.oneal_right1.getFxImage());
                            break;
                    }
                    if (stillObject != null) {
                        stillObjects.add(stillObject);
                        table[j][i] = stillObject;
                    } else if (object != null) {
                        entities.add(object);
                        table[j][i] = object;
                    }
                    else if (hiddenObject != null) {
                        object = new Brick(j, i, Sprite.brick.getFxImage());
                        entities.add(object);
                        table[j][i] = object;
                        hiddenTable[j][i] = hiddenObject;
                    }
                    else if (enemy != null) {
                        enemies.add(enemy);
                        table[j][i] = enemy;
                    }
                }
            }
            MAX_SCORE = enemies.size() * 100;
            scanner.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
