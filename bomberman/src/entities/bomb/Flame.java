package entities.bomb;

import entities.Entity;
import graphics.Sprite;
import javafx.application.Platform;
import javafx.scene.image.Image;

import static main.BombermanGame.*;

public class Flame extends Entity {
    public Flame(int xUnit, int yUnit, Image img, Direction direction) {
        super(xUnit, yUnit, img);
        this.direction = direction;
    }

    private void chooseSprite() {
        switch (direction) {
            case U:
                sprite = Sprite.movingSprite(Sprite.explosion_vertical_top_last, Sprite.explosion_vertical_top_last1, Sprite.explosion_vertical_top_last2, animationTime, 20);
                break;
            case D:
                sprite = Sprite.movingSprite(Sprite.explosion_vertical_down_last, Sprite.explosion_vertical_down_last1, Sprite.explosion_vertical_down_last2, animationTime, 20);
                break;
            case L:
                sprite = Sprite.movingSprite(Sprite.explosion_horizontal_left_last, Sprite.explosion_horizontal_left_last1, Sprite.explosion_horizontal_left_last2, animationTime, 20);
                break;
            case R:
                sprite = Sprite.movingSprite(Sprite.explosion_horizontal_right_last, Sprite.explosion_horizontal_right_last1, Sprite.explosion_horizontal_right_last2, animationTime, 20);
                break;
            case OH:
                sprite = Sprite.movingSprite(Sprite.explosion_horizontal, Sprite.explosion_horizontal1, Sprite.explosion_horizontal2, animationTime, 20);
                break;
            case OV:
                sprite = Sprite.movingSprite(Sprite.explosion_vertical, Sprite.explosion_vertical1, Sprite.explosion_vertical2, animationTime, 20);
                break;
        }
        img = sprite.getFxImage();
    }

    @Override
    public void update() {
        animationTime++;
        chooseSprite();
        if (animationTime == 10) {
            Platform.runLater(() -> entities.remove(this));
        }
    }
}
