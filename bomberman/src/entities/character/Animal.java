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
    /**
     * L : Left
     * R : Right
     * U : Up
     * D : Down
     */
    public enum Direction {L, R, U, D}

    ;

    protected Direction direction = Direction.R;
    protected int SPEED = 2;

    public Animal(int xUnit, int yUnit, Image img) {
        super(xUnit, yUnit, img);
    }

    protected boolean checkWall(int x, int y) {

        int px = x / Sprite.SCALED_SIZE;
        int py = y / Sprite.SCALED_SIZE;

        Entity currentEntity = getEntity(px, py);
        return currentEntity instanceof Wall;
    }

    protected boolean checkBrick(int x, int y) {

        int px = x / Sprite.SCALED_SIZE;
        int py = y / Sprite.SCALED_SIZE;

        Entity currentEntity = getEntity(px, py);
        return currentEntity instanceof Brick;
    }

    protected boolean checkBomb(int x, int y) {

        int px = x / Sprite.SCALED_SIZE;
        int py = y / Sprite.SCALED_SIZE;

        Entity currentEntity = getEntity(px, py);
        return currentEntity instanceof Bomb;
    }

    protected boolean checkEnemy(int x, int y) {

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
                return !checkWall(x, y + Sprite.SCALED_SIZE + 1)
                        && !checkWall(x + Sprite.SCALED_SIZE - 12, y + Sprite.SCALED_SIZE + 1)
                        && !checkBrick(x, y + Sprite.SCALED_SIZE + 1)
                        && !checkBrick(x + Sprite.SCALED_SIZE - 12, y + Sprite.SCALED_SIZE + 1);
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
                return !checkWall(x + Sprite.SCALED_SIZE - 10, y + 3)
                        && !checkWall(x + Sprite.SCALED_SIZE - 10, y + Sprite.SCALED_SIZE - 3)
                        && !checkBrick(x + Sprite.SCALED_SIZE - 10, y + 3)
                        && !checkBrick(x + Sprite.SCALED_SIZE - 10, y + Sprite.SCALED_SIZE - 3)
                        && !checkBomb(x + Sprite.SCALED_SIZE - 10, y + 3)
                        && !checkBomb(x + Sprite.SCALED_SIZE - 10, y + Sprite.SCALED_SIZE - 3)
                        && !checkEnemy(x + Sprite.SCALED_SIZE - 10, y + 3)
                        && !checkEnemy(x + Sprite.SCALED_SIZE - 10, y + Sprite.SCALED_SIZE - 3);
            case L:
                return !checkWall(x - 2, y + 3)
                        && !checkWall(x - 2, y + Sprite.SCALED_SIZE - 3)
                        && !checkBrick(x - 2, y + 3)
                        && !checkBrick(x - 2, y + Sprite.SCALED_SIZE - 3)
                        && !checkBomb(x - 2, y + 3)
                        && !checkBomb(x - 2, y + Sprite.SCALED_SIZE - 3)
                        && !checkEnemy(x - 2, y + 3)
                        && !checkEnemy(x - 2, y + Sprite.SCALED_SIZE - 3);
            case U:
                return !checkWall(x, y - 2)
                        && !checkWall(x + Sprite.SCALED_SIZE - 12, y - 2)
                        && !checkBrick(x, y - 2)
                        && !checkBrick(x + Sprite.SCALED_SIZE - 12, y - 2)
                        && !checkBomb(x, y - 2)
                        && !checkBomb(x + Sprite.SCALED_SIZE - 12, y - 2)
                        && !checkEnemy(x, y - 2)
                        && !checkEnemy(x + Sprite.SCALED_SIZE - 12, y - 2);
            default:
                // DOWN
                return !checkWall(x, y + Sprite.SCALED_SIZE + 1)
                        && !checkWall(x + Sprite.SCALED_SIZE - 12, y + Sprite.SCALED_SIZE + 1)
                        && !checkBrick(x, y + Sprite.SCALED_SIZE + 1)
                        && !checkBrick(x + Sprite.SCALED_SIZE - 12, y + Sprite.SCALED_SIZE + 1)
                        && !checkBomb(x, y + Sprite.SCALED_SIZE + 1)
                        && !checkBomb(x + Sprite.SCALED_SIZE - 12, y + Sprite.SCALED_SIZE + 1)
                        && !checkEnemy(x, y + Sprite.SCALED_SIZE + 1)
                        && !checkEnemy(x + Sprite.SCALED_SIZE - 12, y + Sprite.SCALED_SIZE + 1);
        }
    }

    public int getSpeed() {
        return SPEED;
    }
}
