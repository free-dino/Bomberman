package entities.character;

import entities.Entity;
import entities.block.Brick;
import entities.block.Wall;
import entities.bomb.Bomb;
import entities.character.enemy.Enemy;
import graphics.Sprite;
import javafx.scene.image.Image;

import static main.BombermanGame.*;

/**
 * Các vật thể di chuyển được, bao gồm cả Player.
 */
public abstract class Animal extends Entity {
    protected boolean moving = false;
    protected boolean died = false;
    protected Sprite sprite;

    /**
     * L : Left
     * R : Right
     * U : Up
     * D : Down
     */
    public enum Direction {L, R, U, D}

    protected Direction direction = Direction.R;
    protected int SPEED;

    public Animal(int xUnit, int yUnit, Image img) {
        super(xUnit, yUnit, img);
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
    public boolean isValidPlayerMove(Direction direction) {
        switch (direction) {
            case R:
                return !checkWall(x + Sprite.SCALED_SIZE - 10, y + 3)
                        && !checkWall(x + Sprite.SCALED_SIZE - 10, y + Sprite.SCALED_SIZE - 3)
                        && !checkBrick(x + Sprite.SCALED_SIZE - 10, y + 3)
                        && !checkBrick(x + Sprite.SCALED_SIZE - 10, y + Sprite.SCALED_SIZE - 3);
            case L:
                return !checkWall(x - 2, y + 3)
                        && !checkWall(x - 2, y + Sprite.SCALED_SIZE - 3)
                        && !checkBrick(x - 2, y + 3)
                        && !checkBrick(x - 2, y + Sprite.SCALED_SIZE - 3);
            case U:
                return !checkWall(x, y - 2)
                        && !checkWall(x + Sprite.SCALED_SIZE - 12, y - 2)
                        && !checkBrick(x, y - 2)
                        && !checkBrick(x + Sprite.SCALED_SIZE - 12, y - 2);
            default:
                // DOWN
                return !checkWall(x, y + Sprite.SCALED_SIZE)
                        && !checkWall(x + Sprite.SCALED_SIZE - 12, y + Sprite.SCALED_SIZE)
                        && !checkBrick(x, y + Sprite.SCALED_SIZE)
                        && !checkBrick(x + Sprite.SCALED_SIZE - 12, y + Sprite.SCALED_SIZE);
        }
    }

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
                        && !checkEnemy(x + Sprite.SCALED_SIZE + SPEED - 1, y + Sprite.SCALED_SIZE - 1);
//                        && !checkBomb(x + Sprite.SCALED_SIZE - 10, y + 3)
//                        && !checkBomb(x + Sprite.SCALED_SIZE - 10, y + Sprite.SCALED_SIZE - 3)
//                        && !checkEnemy(x + Sprite.SCALED_SIZE - 10, y + 3)
//                        && !checkEnemy(x + Sprite.SCALED_SIZE - 10, y + Sprite.SCALED_SIZE - 3);
            case L:
                return !checkWall(x - SPEED, y)
                        && !checkWall(x - SPEED, y + Sprite.SCALED_SIZE - 1)
                        && !checkBrick(x - SPEED, y)
                        && !checkBrick(x - SPEED, y + Sprite.SCALED_SIZE - 1)
                        && !checkEnemy(x - SPEED, y)
                        && !checkEnemy(x - SPEED, y + Sprite.SCALED_SIZE - 1);
//                        && !checkBomb(x - 2, y + 3)
//                        && !checkBomb(x - 2, y + Sprite.SCALED_SIZE - 3)
//                        && !checkEnemy(x - 2, y + 3)
//                        && !checkEnemy(x - 2, y + Sprite.SCALED_SIZE - 3);
            case U:
                return !checkWall(x, y - SPEED)
                        && !checkWall(x + Sprite.SCALED_SIZE - 1, y - SPEED)
                        && !checkBrick(x, y - SPEED)
                        && !checkBrick(x + Sprite.SCALED_SIZE - 1, y - SPEED)
                        && !checkEnemy(x, y - SPEED)
                        && !checkEnemy(x + Sprite.SCALED_SIZE - 1, y - SPEED);
//                        && !checkBomb(x, y - 2)
//                        && !checkBomb(x + Sprite.SCALED_SIZE - 12, y - 2)
//                        && !checkEnemy(x, y - 2)
//                        && !checkEnemy(x + Sprite.SCALED_SIZE - 12, y - 2);
            default:
                // DOWN
                return !checkWall(x, y + Sprite.SCALED_SIZE + SPEED - 1)
                        && !checkWall(x + Sprite.SCALED_SIZE - 1, y + Sprite.SCALED_SIZE + SPEED - 1)
                        && !checkBrick(x, y + Sprite.SCALED_SIZE + SPEED - 1)
                        && !checkBrick(x + Sprite.SCALED_SIZE - 1, y + Sprite.SCALED_SIZE + SPEED - 1)
                        && !checkEnemy(x, y + Sprite.SCALED_SIZE + SPEED - 1)
                        && !checkEnemy(x + Sprite.SCALED_SIZE - 1, y + Sprite.SCALED_SIZE + SPEED - 1);
//                        && !checkBomb(x, y + Sprite.SCALED_SIZE + 1)
//                        && !checkBomb(x + Sprite.SCALED_SIZE - 12, y + Sprite.SCALED_SIZE + 1)
//                        && !checkEnemy(x, y + Sprite.SCALED_SIZE + 1)
//                        && !checkEnemy(x + Sprite.SCALED_SIZE - 12, y + Sprite.SCALED_SIZE + 1);
        }
    }

    public int getSpeed() {
        return SPEED;
    }
}
