package entities.item;

import entities.item.Item;
import graphics.Sprite;
import javafx.application.Platform;
import javafx.scene.image.Image;

import static main.BombermanGame.*;

public class BombItem extends Item {
    public BombItem(int x, int y, Image img) {
        super(x, y, img);
    }

    @Override
    public void update() {
        img = Sprite.powerup_bombs.getFxImage;
        table[x / Sprite.SCALED_SIZE][y / Sprite.SCALED_SIZE] = this;
        if (pickedup || died) {
            Platform.runLater(() -> {
                table[x / Sprite.SCALED_SIZE][y / Sprite.SCALED_SIZE] = null;
                entities.remove(this);
            });
        }
    }
}