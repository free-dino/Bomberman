package entities.bomb;

import entities.Entity;
import entities.block.Brick;
import entities.block.Wall;
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
        quantity++;

        table[xUnit][yUnit] = this;
    }

    private void chooseSprite() {
        if (exploded) {
            sprite = Sprite.movingSprite(Sprite.bomb_exploded, Sprite.bomb_exploded1, Sprite.bomb_exploded2, animationTime, 20);
        } else {
            sprite = Sprite.movingSprite(Sprite.bomb, Sprite.bomb_1, Sprite.bomb_2, animationTime, 20);
        }
        img = sprite.getFxImage();
    }

    private boolean checkBreak(int locationX, int locationY) {
        Entity currentEntity = getEntity(locationX, locationY);
        if (currentEntity instanceof Wall) {
            return false;
        }
        if (currentEntity instanceof Brick) {
            ((Brick) currentEntity).setExploded();
            return true;
        }
        return true;
    }

    private void setExploded() {
        this.exploded = true;
        Platform.runLater(
                () -> {
                    for (int count = 1; count <= size; count++) {
                        int i = x / Sprite.SCALED_SIZE - count, j = y / Sprite.SCALED_SIZE;
                        if (!checkBreak(i, j)) break;
                        if (count < size) {
                            entities.add(new Flame(i, j, null, Direction.OH));
                        } else {
                            entities.add(new Flame(i, j, null, Direction.L));
                        }
                    }
                    for (int count = 1; count <= size; count++) {
                        int i = x / Sprite.SCALED_SIZE + count, j = y / Sprite.SCALED_SIZE;
                        if (!checkBreak(i, j)) break;
                        if (count < size) {
                            entities.add(new Flame(i, j, Sprite.explosion_horizontal.getFxImage(), Direction.OH));
                        } else {
                            entities.add(new Flame(i, j, Sprite.explosion_horizontal_right_last.getFxImage(), Direction.R));
                        }
                    }
                    for (int count = 1; count <= size; count++) {
                        int i = x / Sprite.SCALED_SIZE, j = y / Sprite.SCALED_SIZE - count;
                        if (!checkBreak(i, j)) break;
                        if (count < size) {
                            entities.add(new Flame(i, j, Sprite.explosion_vertical.getFxImage(), Direction.OV));
                        } else {
                            entities.add(new Flame(i, j, Sprite.explosion_vertical_top_last.getFxImage(), Direction.U));
                        }
                    }
                    for (int count = 1; count <= size; count++) {
                        int i = x / Sprite.SCALED_SIZE, j = y / Sprite.SCALED_SIZE + count;
                        if (!checkBreak(i, j)) break;
                        if (count < size) {
                            entities.add(new Flame(i, j, Sprite.explosion_vertical.getFxImage(), Direction.OV));
                        } else {
                            entities.add(new Flame(i, j, Sprite.explosion_vertical_down_last.getFxImage(), Direction.D));
                        }
                    }
                });
    }

    @Override
    public void update() {
        animationTime++;
        chooseSprite();
        if (animationTime > 1000000) {
            animationTime = 0;
        }
        if (animationTime == 70) {
            setExploded();
        }
        if (animationTime == 80) {
            Platform.runLater(
                    () -> {
                        table[getLocationX()][getLocationY()] = null;
                        quantity--;
                        entities.remove(this);
                    });
        }
    }
}
