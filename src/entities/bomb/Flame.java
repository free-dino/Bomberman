package entities.bomb;

import entities.Entity;
import graphics.Sprite;
import javafx.application.Platform;
import javafx.scene.image.Image;

import java.util.List;

import static main.BombermanGame.*;

public class Flame extends Entity {
    public enum FlameDirection {L, R, U, D, OH, OV}
    private final FlameDirection flameDirection;
    private final List<Entity> entities;
    private int animate = 0;
    public Flame(int xUnit, int yUnit, Image img, FlameDirection flameDirection, List<Entity> entities) {
        super(xUnit, yUnit, img);
        this.flameDirection = flameDirection;
        this.entities = entities;
    }

    public void getImg() {
        Sprite sprite = null;
        switch (flameDirection) {
            case U:
                sprite = Sprite.movingSprite(Sprite.explosion_vertical_top_last, Sprite.explosion_vertical_top_last1, Sprite.explosion_vertical_top_last2, animate, 20);
                break;
            case D:
                sprite = Sprite.movingSprite(Sprite.explosion_vertical_down_last, Sprite.explosion_vertical_down_last1, Sprite.explosion_vertical_down_last2, animate, 20);
                break;
            case L:
                sprite = Sprite.movingSprite(Sprite.explosion_horizontal_left_last, Sprite.explosion_horizontal_left_last1, Sprite.explosion_horizontal_left_last2, animate, 20);
                break;
            case R:
                sprite = Sprite.movingSprite(Sprite.explosion_horizontal_right_last, Sprite.explosion_horizontal_right_last1, Sprite.explosion_horizontal_right_last2, animate, 20);
                break;
            case OH:
                sprite = Sprite.movingSprite(Sprite.explosion_horizontal, Sprite.explosion_horizontal1, Sprite.explosion_horizontal2, animate, 20);
                break;
            case OV:
                sprite = Sprite.movingSprite(Sprite.explosion_vertical, Sprite.explosion_vertical1, Sprite.explosion_vertical2, animate, 20);
                break;
        }
        img = sprite.getFxImage;
    }


    @Override
    public void update() {
        animate++;
        if (animate == 10) {
            Platform.runLater(() -> entities.remove(this));
        }
        getImg();
    }
}
