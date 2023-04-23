package entities.bomb;

import entities.Entity;
import graphics.Sprite;
import javafx.application.Platform;
import javafx.scene.image.Image;

import java.util.ArrayList;
import java.util.List;

import static main.BombermanGame.*;
public class Bomb extends Entity {
    private boolean exploded = false;
    private final List<Entity> flamesList = new ArrayList<>();
    private int size;
    public static int quantity;

    public Bomb(int xUnit, int yUnit, Image img, int size) {
        super(xUnit, yUnit, img);
        this.size = size;
//        quantity++;
    }

    private void chooseSprite() {
        if (exploded) {
            sprite = Sprite.movingSprite(Sprite.bomb_exploded, Sprite.bomb_exploded1, Sprite.bomb_exploded2, animationTime, 20);
        } else {
            sprite = Sprite.movingSprite(Sprite.bomb, Sprite.bomb_1, Sprite.bomb_2, animationTime, 20);
        }
        img = sprite.getFxImage();
    }

    @Override
    public void update() {
        animationTime++;
        chooseSprite();
        if (animationTime > 1000000) {
            animationTime = 0;
        }
        if (animationTime == 70) {
            exploded = true;
        }
//        if (animationTime == 80) {
//            Platform.runLater(
//                    () -> {
//                        table[getLocationX()][getLocationY()] = null;
//                        quantity--;
//                        entities.remove(this);
//                    });
//        }
    }
}
