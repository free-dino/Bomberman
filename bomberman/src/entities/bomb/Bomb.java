package entities.bomb;

import entities.Entity;
import entities.block.Brick;
import entities.block.Wall;
import entities.character.bomber.Bomber;
import entities.character.enemy.Enemy;
import graphics.Sprite;
import javafx.application.Platform;
import javafx.scene.image.Image;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import static main.BombermanGame.*;

public class Bomb extends Entity {
    private boolean exploded = false;
    private int size;
    public static int quantity;

    public Bomb(int xUnit, int yUnit, Image img, int size) {
        super(xUnit, yUnit, img);
        this.size = size;
        quantity++;
        table[xUnit][yUnit] = this;
    }

    public boolean isExploded() {
        return exploded;
    }

    private void chooseSprite() {
        if (exploded) {
            sprite = Sprite.movingSprite(Sprite.bomb_exploded, Sprite.bomb_exploded1, Sprite.bomb_exploded2, animationTime, 20);
        } else {
            sprite = Sprite.movingSprite(Sprite.bomb, Sprite.bomb_1, Sprite.bomb_2, animationTime, 20);
        }
        img = sprite.getFxImage();
    }

    /**
     * Hàm này check xem khi bom nổ thì tia lửa có đi qua được hay không.
     *
     * @param locationX - Vị trí trên Ox dạng từ 1 đến n.
     * @param locationY - Vị trí trên Oy dạng từ 1 đến m.
     *
     * @return true / false;
     */
    private boolean checkBreak(int locationX, int locationY) {
        if (locationX < 0 || locationY < 0 || locationX >= WIDTH || locationY >= HEIGHT) {
            return false;
        }
        Entity currentEntity = getEntity(locationX, locationY);
        if (currentEntity instanceof Wall) {
            return false;
        }
        if (currentEntity instanceof Brick) {
            ((Brick) currentEntity).setExploded();
            return false;
        }

        return true;
    }

    private void hurtingByExplosion(int locationX, int locationY) {
        if (locationX < 0 || locationY < 0 || locationX >= WIDTH || locationY >= HEIGHT) {
            return;
        }
        Entity currentEntity = getEntity(locationX, locationY);
        if (currentEntity instanceof Enemy) {
            System.out.println("Hearting enemy ");
            for (Entity enemy : enemies) {
                if (enemy.getLocationX() == locationX && enemy.getLocationY() == locationY) {
                    enemy.setHurt();
                }
            }
        }
        if (bomber.getBomberX() == locationX && bomber.getBomberY() == locationY) {
            System.out.println("Hearting player");
            bomber.setHurt();
        }
    }

    private void setExploded() {
        this.exploded = true;
        Platform.runLater(
                () -> {
                    for (int count = 1; count <= size; count++) {
                        int i = x / Sprite.SCALED_SIZE - count;
                        int j = y / Sprite.SCALED_SIZE;
                        if (!checkBreak(i, j)) break;
                        if (count < size) {
                            entities.add(new Flame(i, j, null, Direction.OH));
                        } else {
                            entities.add(new Flame(i, j, null, Direction.L));
                        }
                    }
                    for (int count = 1; count <= size; count++) {
                        int i = x / Sprite.SCALED_SIZE + count;
                        int j = y / Sprite.SCALED_SIZE;
                        if (!checkBreak(i, j)) break;
                        if (count < size) {
                            entities.add(new Flame(i, j, Sprite.explosion_horizontal.getFxImage(), Direction.OH));
                        } else {
                            entities.add(new Flame(i, j, Sprite.explosion_horizontal_right_last.getFxImage(), Direction.R));
                        }
                    }
                    for (int count = 1; count <= size; count++) {
                        int i = x / Sprite.SCALED_SIZE;
                        int j = y / Sprite.SCALED_SIZE - count;
                        if (!checkBreak(i, j)) break;
                        if (count < size) {
                            entities.add(new Flame(i, j, Sprite.explosion_vertical.getFxImage(), Direction.OV));
                        } else {
                            entities.add(new Flame(i, j, Sprite.explosion_vertical_top_last.getFxImage(), Direction.U));
                        }
                    }
                    for (int count = 1; count <= size; count++) {
                        int i = x / Sprite.SCALED_SIZE;
                        int j = y / Sprite.SCALED_SIZE + count;
                        if (!checkBreak(i, j)) break;
                        if (count < size) {
                            entities.add(new Flame(i, j, Sprite.explosion_vertical.getFxImage(), Direction.OV));
                        } else {
                            entities.add(new Flame(i, j, Sprite.explosion_vertical_down_last.getFxImage(), Direction.D));
                        }
                    }
                    Timer bombTimer = new Timer();
                    bombTimer.schedule(new TimerTask() {
                        @Override
                        public void run() {
                            int px = x / Sprite.SCALED_SIZE;
                            int py = y / Sprite.SCALED_SIZE;
                            for (int count = 0; count <= size; count++) {
                                hurtingByExplosion(px - count, py);
                            }
                            for (int count = 1; count <= size; count++) {
                                hurtingByExplosion(px + count, py);
                            }
                            for (int count = 1; count <= size; count++) {
                                hurtingByExplosion(px, py - count);
                            }
                            for (int count = 1; count <= size; count++) {
                                hurtingByExplosion(px, py + count);
                            }
                        }
                    }, 10);
                });
    }

    @Override
    public void update() {
        try {
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
        } catch (Exception e) {
            System.out.println("Error in Bomb.java");
            System.out.println(e.getMessage());
        }
    }
}
