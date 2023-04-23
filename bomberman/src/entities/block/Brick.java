package entities.block;

import entities.Entity;
import graphics.Sprite;
import javafx.application.Platform;
import javafx.scene.image.Image;

import static main.BombermanGame.*;

public class Brick extends Entity {
    private boolean exploded = false;

    public Brick(int x, int y, Image img) {
        super(x, y, img);
    }

    public void setExploded() {
        this.exploded = true;
    }

    private void destroyBrick() {
        Sprite sprite = Sprite.movingSprite(Sprite.brick_exploded, Sprite.brick_exploded1, Sprite.brick_exploded2, animationTime, 20);
        animationTime++;
        if (animationTime == 10) {
            Platform.runLater(() -> {
                entities.remove(this);
                x /= Sprite.SCALED_SIZE;
                y /= Sprite.SCALED_SIZE;
                table[x][y] = null;
                Entity hiddenItem = hiddenTable[x][y];
                if (hiddenItem != null) {
                    table[x][y] = hiddenItem;
                    entities.add(hiddenItem);
                }
            });
        }
        img = sprite.getFxImage();
    }

    @Override
    public void update() {
        table[getLocationX()][getLocationY()] = this;
        if (exploded) {
            destroyBrick();
        }
    }
}