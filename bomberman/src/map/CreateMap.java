package map;

import audio.SoundManager;
import entities.Entity;
import entities.block.Brick;
import entities.block.Grass;
import entities.block.Wall;
import entities.character.bomber.Bomber;
import graphics.Sprite;

import java.io.File;
import java.io.FileReader;
import java.util.Scanner;

import static main.BombermanGame.*;

/**
 * Khởi tạo 1 map với đầu vào là 1 string: string Level.
 */
public class CreateMap {
    public static SoundManager background = SoundManager.main_bgm;
    public CreateMap(String stringLevel) {
        System.out.println(System.getProperty("user.dir"));
        if ( background != null ) background.stop();
        background.loop();
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
                    }
                    if (stillObject != null) {
                        stillObjects.add(stillObject);
                        table[j][i] = stillObject;
                    } else if (object != null) {
                        entities.add(object);
                        table[j][i] = object;
                    }
                }
            }
            MAX_SCORE = enemies.size();
            scanner.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
