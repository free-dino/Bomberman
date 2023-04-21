package entities.character;

import entities.Entity;
import entities.block.Brick;
import entities.block.Wall;
import entities.bomb.Bomb;
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

    protected Direction direction = Direction.R;

    public Animal(int xUnit, int yUnit, Image img) {
        super(xUnit, yUnit, img);
    }

    protected boolean checkWall(int x, int y) {
        if (x <= 0 || y <= 0 || x >= Sprite.SCALED_SIZE * WIDTH || y >= Sprite.SCALED_SIZE * HEIGHT) {
            return false;
        }

        int px = x / Sprite.SCALED_SIZE;
        int py = y / Sprite.SCALED_SIZE;

        Entity currentEntity = getEntity(px, py);
        return !(currentEntity instanceof Wall);
    }

    protected boolean checkBrick(int x, int y) {
        if (x <= 0 || y <= 0 || x >= Sprite.SCALED_SIZE * WIDTH || y >= Sprite.SCALED_SIZE * HEIGHT) {
            return false;
        }

        int px = x / Sprite.SCALED_SIZE;
        int py = y / Sprite.SCALED_SIZE;

        Entity currentEntity = getEntity(px, py);
        return !(currentEntity instanceof Brick);
    }

    protected boolean checkBomb(int x, int y) {
        if (x <= 0 || y <= 0 || x >= Sprite.SCALED_SIZE * WIDTH || y >= Sprite.SCALED_SIZE * HEIGHT) {
            return false;
        }

        int px = x / Sprite.SCALED_SIZE;
        int py = y / Sprite.SCALED_SIZE;

        Entity currentEntity = getEntity(px, py);
        return !(currentEntity instanceof Bomb);
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
                return (checkWall(x + Sprite.SCALED_SIZE + Sprite.STEP - 1, y)
                        && (checkWall(x + Sprite.SCALED_SIZE + Sprite.STEP - 1, y + Sprite.SCALED_SIZE - 1)))
                        && (checkBrick(x + Sprite.SCALED_SIZE + Sprite.STEP - 1, y)
                        && checkBrick(x + Sprite.SCALED_SIZE + Sprite.STEP - 1, y + Sprite.SCALED_SIZE - 1));
            case L:
                return (checkWall(x - Sprite.STEP, y)
                        && (checkWall(x - Sprite.STEP, y + Sprite.SCALED_SIZE - 1)))
                        && (checkBrick(x - Sprite.STEP, y)
                        && (checkBrick(x - Sprite.STEP, y + Sprite.SCALED_SIZE - 1)));
            case U:
                return (checkWall(x, y - Sprite.STEP)
                        && (checkWall(x + Sprite.SCALED_SIZE - 1, y - Sprite.STEP)))
                        && (checkBrick(x, y - Sprite.STEP)
                        && (checkBrick(x + Sprite.SCALED_SIZE - 1, y - Sprite.STEP)));
            default:
                return (checkWall(x, y + Sprite.SCALED_SIZE + Sprite.STEP - 1)
                        && (checkWall(x + Sprite.SCALED_SIZE - 1, y + Sprite.SCALED_SIZE + Sprite.STEP - 1)))
                        && (checkBrick(x, y + Sprite.SCALED_SIZE + Sprite.STEP - 1)
                        && (checkBrick(x + Sprite.SCALED_SIZE - 1, y + Sprite.SCALED_SIZE + Sprite.STEP - 1)));
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
                return (checkWall(x + Sprite.SCALED_SIZE + Sprite.STEP - 1, y)
                        && (checkWall(x + Sprite.SCALED_SIZE + Sprite.STEP - 1, y + Sprite.SCALED_SIZE - 1)))
                        && (checkBrick(x + Sprite.SCALED_SIZE + Sprite.STEP - 1, y)
                        && checkBrick(x + Sprite.SCALED_SIZE + Sprite.STEP - 1, y + Sprite.SCALED_SIZE - 1))
                        && (checkBomb(x + Sprite.SCALED_SIZE + Sprite.STEP - 1, y)
                        && checkBomb(x + Sprite.SCALED_SIZE + Sprite.STEP - 1, y + Sprite.SCALED_SIZE - 1));
            case L:
                return (checkWall(x - Sprite.STEP, y)
                        && (checkWall(x - Sprite.STEP, y + Sprite.SCALED_SIZE - 1)))
                        && (checkBrick(x - Sprite.STEP, y)
                        && (checkBrick(x - Sprite.STEP, y + Sprite.SCALED_SIZE - 1)))
                        && (checkBomb(x - Sprite.STEP, y)
                        && (checkBomb(x - Sprite.STEP, y + Sprite.SCALED_SIZE - 1)));
            case U:
                return (checkWall(x, y - Sprite.STEP)
                        && (checkWall(x + Sprite.SCALED_SIZE - 1, y - Sprite.STEP)))
                        && (checkBrick(x, y - Sprite.STEP)
                        && (checkBrick(x + Sprite.SCALED_SIZE - 1, y - Sprite.STEP)))
                        && (checkBomb(x, y - Sprite.STEP)
                        && (checkBomb(x + Sprite.SCALED_SIZE - 1, y - Sprite.STEP)));
            default:
                return (checkWall(x, y + Sprite.SCALED_SIZE + Sprite.STEP - 1)
                        && (checkWall(x + Sprite.SCALED_SIZE - 1, y + Sprite.SCALED_SIZE + Sprite.STEP - 1)))
                        && (checkBrick(x, y + Sprite.SCALED_SIZE + Sprite.STEP - 1)
                        && (checkBrick(x + Sprite.SCALED_SIZE - 1, y + Sprite.SCALED_SIZE + Sprite.STEP - 1)))
                        && (checkBomb(x, y + Sprite.SCALED_SIZE + Sprite.STEP - 1)
                        && (checkBomb(x + Sprite.SCALED_SIZE - 1, y + Sprite.SCALED_SIZE + Sprite.STEP - 1)));
        }
    }
}
