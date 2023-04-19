package map;

import entities.Entity;
import entities.block.Brick;
import entities.block.Grass;
import entities.block.Wall;
import entities.character.bomber.Bomber;
import entities.item.FlameItem;
import entities.item.PortalItem;
import entities.item.SpeedItem;
import graphics.Sprite;

import java.io.File;
import java.io.FileReader;
import java.util.Scanner;

import static main.BombermanGame.*;

/**
 * Khởi tạo 1 map với đầu vào là 1 string: string Level.
 */
public class createMap {
    public createMap(String stringLevel){
        System.out.println(System.getProperty("user.dir"));
        final File fileName = new File(stringLevel);
        try (FileReader inputFile = new FileReader(fileName)) {
            Scanner scanner = new Scanner(inputFile);

            level = scanner.nextInt();
            height = scanner.nextInt();
            width = scanner.nextInt();

            table = new Entity[width][height];
            hiddenTable = new Entity[width][height];

            scanner.nextLine();
            for (int i = 0; i < height; i++) {
                String cur = scanner.nextLine();
                for (int j = 0; j < width; j++) {
                    Entity stillObject = null;
                    Entity object = null;
                    Entity hiddenObject = null;
                    Entity enemy = null;
                    stillObjects.add(new Grass(j, i, Sprite.grass.getFxImage()));
                    switch (cur.charAt(j)) {
                        // Tiles:
                        case '#':
                            stillObject = new Wall(j, i, Sprite.wall.getFxImage());
                            break;
                        case '*':
                            object = new Brick(j, i, Sprite.brick.getFxImage());
                            break;
                        // Character:
//                        case 'p':
//                            object = new Bomber(j, i, Sprite.player_right.getFxImage(), keyListener);
//                            bomber = (Bomber) object;
//                            break;
//                        case '1':
//                            enemy = new Balloom(j, i, Sprite.balloom_right1.getFxImage);
////                            cnt_enemy++;
//                            break;
//                        case '2':
//                            enemy = new Oneal(j, i, Sprite.oneal_right1.getFxImage);
////                            cnt_enemy++;
//                            break;
//                        case '3':
//                            enemy = new Doll(j, i, Sprite.doll_right1.getFxImage);
////                            cnt_enemy++;
//                            break;
//                        case '4':
//                            enemy = new Kondoria(j, i, Sprite.doll_right1.getFxImage);
////                            cnt_enemy++;
//                            break;
                        // Items:
//                        case 'f':
//                            hiddenObject = new FlameItem(j, i, Sprite.powerup_flames.getFxImage());
//                            break;
//                        case 's':
//                            hiddenObject = new SpeedItem(j, i, Sprite.powerup_speed.getFxImage());
//                            break;
//                        case 'b':
//                            hiddenObject = new BombItem(j, i, Sprite.powerup_bombs.getFxImage);
//                            break;
//                        case 'x':
//                            hiddenObject = new PortalItem(j, i, Sprite.portal.getFxImage());
//                            break;
//                        case 'm':
//                            hiddenObject = new FlamePassItem(j, i, Sprite.powerup_flamepass.getFxImage);
//                            break;
//                        case 'w':
//                            hiddenObject = new WallPassItem(j, i, Sprite.powerup_wallpass.getFxImage);
//                            break;
                    }
                    if (stillObject != null) {
                        stillObjects.add(stillObject);
                        table[j][i] = stillObject;
                    } else if (object != null) {
                        entities.add(object);
                        table[j][i] = object;
                    }
//                    else if (hiddenObject != null) {
//                        object = new Brick(j, i, Sprite.brick.getFxImage());
//                        entities.add(object);
//                        table[j][i] = object;
//                        hiddenTable[j][i] = hiddenObject;
//                    } else if (enemy != null) {
//                        enemies.add(enemy);
//                        table[j][i] = enemy;
//                    }
                }
            }
            MAX_SCORE = enemies.size();
            scanner.close();
        } catch (Exception e){
            System.out.println(e.getMessage());
        }
    }
}
