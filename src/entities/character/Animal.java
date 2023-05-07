package entities.character;

import audio.Sound;
import entities.Entity;
import entities.block.Brick;
import entities.block.Wall;
import entities.bomb.Bomb;
import entities.character.bomber.Bomber;
import entities.character.enemy.Enemy;
import graphics.Sprite;
import javafx.application.Platform;
import javafx.scene.image.Image;
import main.BombermanGame;

import static main.BombermanGame.*;

/**
 * Các vật thể di chuyển được, bao gồm cả Player.
 */
public abstract class Animal extends Entity {
    protected boolean moving = false;

    protected Sprite sprite;

    protected int SPEED;

    public Animal(int xUnit, int yUnit, Image img) {
        super(xUnit, yUnit, img);
    }

    protected void gotHurt(Sprite sprite) {
        hurt_time++;
        if (hurt_time == 1) {
            Sound.died.play();
        }
        img = sprite.getFxImage;
        if (hurt_time == 20) {
            hurt_time = 0;
            beHurt = false;
            if (HP == 0) {
                Platform.runLater(() -> {
                    enemies.remove(this);
                    BombermanGame.table[(x + Sprite.SCALED_SIZE / 2) / Sprite.SCALED_SIZE][(y + Sprite.SCALED_SIZE / 2) / Sprite.SCALED_SIZE] = null;
                });
            }
        }
    }

    protected void checkCollideWithBomber() {
        if (bomber.getBomberX() == getLocationX() && bomber.getBomberY() == getLocationY() && !bomber.isProtected()) {
            bomber.setHurt();
        }
    }


    protected boolean checkWall(int x, int y) {
        if (x < 0 || y < 0 || x > Sprite.SCALED_SIZE * WIDTH || y > Sprite.SCALED_SIZE * HEIGHT) {
            return true;
        }

        int px = x / Sprite.SCALED_SIZE;
        int py = y / Sprite.SCALED_SIZE;

        Entity currentEntity = getEntity(px, py);
        return currentEntity instanceof Wall;
    }

    protected boolean checkBrick(int x, int y) {
        if (x < 0 || y < 0 || x > Sprite.SCALED_SIZE * WIDTH || y > Sprite.SCALED_SIZE * HEIGHT) {
            return true;
        }

        int px = x / Sprite.SCALED_SIZE;
        int py = y / Sprite.SCALED_SIZE;

        Entity currentEntity = getEntity(px, py);
        return currentEntity instanceof Brick;
    }

    protected boolean checkBomb(int x, int y) {
        if (x < 0 || y < 0 || x > Sprite.SCALED_SIZE * WIDTH || y > Sprite.SCALED_SIZE * HEIGHT) {
            return true;
        }

        int px = x / Sprite.SCALED_SIZE;
        int py = y / Sprite.SCALED_SIZE;

        Entity currentEntity = getEntity(px, py);
        return currentEntity instanceof Bomb;
    }

    protected boolean checkEnemy(int x, int y) {
        if (x < 0 || y < 0 || x > Sprite.SCALED_SIZE * WIDTH || y > Sprite.SCALED_SIZE * HEIGHT) {
            return true;
        }

        int px = x / Sprite.SCALED_SIZE;
        int py = y / Sprite.SCALED_SIZE;

        Entity currentEntity = getEntity(px, py);
        return currentEntity instanceof Enemy;
    }

    /**
     * Người chơi có thể đi xuyên bom nhé! .
     *
     * @param direction - direction of the movement.
     *
     * @return true / false.
     */


    /**
     * Enemy không đi qua được vật thể Bomb.
     *
     * @param direction - direction.
     *
     * @return true / false.
     */
    public boolean isValidEnemyMove(Direction direction) {
        switch (direction) {
            case R:
                return !checkWall(x + Sprite.SCALED_SIZE + SPEED - 1, y)
                        && !checkWall(x + Sprite.SCALED_SIZE + SPEED - 1, y + Sprite.SCALED_SIZE - 1)
                        && !checkBrick(x + Sprite.SCALED_SIZE + SPEED - 1, y)
                        && !checkBrick(x + Sprite.SCALED_SIZE + SPEED - 1, y + Sprite.SCALED_SIZE - 1)
                        && !checkEnemy(x + Sprite.SCALED_SIZE + SPEED - 1, y)
                        && !checkEnemy(x + Sprite.SCALED_SIZE + SPEED - 1, y + Sprite.SCALED_SIZE - 1)
                        && !checkBomb(x + Sprite.SCALED_SIZE + SPEED - 1, y)
                        && !checkBomb(x + Sprite.SCALED_SIZE + SPEED - 1, y + Sprite.SCALED_SIZE - 1);
            case L:
                return !checkWall(x - SPEED, y)
                        && !checkWall(x - SPEED, y + Sprite.SCALED_SIZE - 1)
                        && !checkBrick(x - SPEED, y)
                        && !checkBrick(x - SPEED, y + Sprite.SCALED_SIZE - 1)
                        && !checkBomb(x - SPEED, y)
                        && !checkBomb(x - SPEED, y + Sprite.SCALED_SIZE - 1)
                        && !checkEnemy(x - SPEED, y)
                        && !checkEnemy(x - SPEED, y + Sprite.SCALED_SIZE - 1);
            case U:
                return !checkWall(x, y - SPEED)
                        && !checkWall(x + Sprite.SCALED_SIZE - 1, y - SPEED)
                        && !checkBrick(x, y - SPEED)
                        && !checkBrick(x + Sprite.SCALED_SIZE - 1, y - SPEED)
                        && !checkEnemy(x, y - SPEED)
                        && !checkEnemy(x + Sprite.SCALED_SIZE - 1, y - SPEED)
                        && !checkBomb(x, y - SPEED)
                        && !checkBomb(x + Sprite.SCALED_SIZE - 1, y - SPEED);
            default:
                // DOWN
                return !checkWall(x, y + Sprite.SCALED_SIZE + SPEED - 1)
                        && !checkWall(x + Sprite.SCALED_SIZE - 1, y + Sprite.SCALED_SIZE + SPEED - 1)
                        && !checkBrick(x, y + Sprite.SCALED_SIZE + SPEED - 1)
                        && !checkBrick(x + Sprite.SCALED_SIZE - 1, y + Sprite.SCALED_SIZE + SPEED - 1)
                        && !checkEnemy(x, y + Sprite.SCALED_SIZE + SPEED - 1)
                        && !checkEnemy(x + Sprite.SCALED_SIZE - 1, y + Sprite.SCALED_SIZE + SPEED - 1)
                        && !checkBomb(x, y + Sprite.SCALED_SIZE + SPEED - 1)
                        && !checkBomb(x + Sprite.SCALED_SIZE - 1, y + Sprite.SCALED_SIZE + SPEED - 1);
        }
    }

    public int getSpeed() {
        return SPEED;
    }
}
