package entities;

import entities.block.Brick;
import entities.block.Wall;
import entities.bomb.Bomb;
import entities.character.enemy.Enemy;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import graphics.Sprite;

import static main.BombermanGame.*;

public abstract class Entity {
    protected int x; // Trục Ox từ trái sang phải;
    protected int y; // Trục Oy từ trên xuống dưới;.
    protected Image img;
    protected Sprite sprite;

    protected int animationTime = 0;
    public int HP = 1;
    protected boolean died = false;
    protected boolean beHurt = false;
    protected int hurt_time;
    protected int died_time;


    /**
     * L : Left
     * R : Right
     * U : Up
     * D : Down
     */
    public enum Direction {L, R, U, D}

    protected Direction direction = Direction.R;


    //Khởi tạo đối tượng, chuyển từ tọa độ đơn vị sang tọa độ trong canvas
    public Entity(int xUnit, int yUnit, Image img) {
        this.x = xUnit * Sprite.SCALED_SIZE;
        this.y = yUnit * Sprite.SCALED_SIZE;
        this.img = img;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void setDied() {
        this.died = true;
    }
    public void setHurt() {
        if (!beHurt) {
            HP -= 1;
        }
        beHurt = true;
    }

    public int getLocationX() {
        return (x + Sprite.SCALED_SIZE / 2) / Sprite.SCALED_SIZE;
    }

    public int getLocationY() {
        return (y + Sprite.SCALED_SIZE / 2) / Sprite.SCALED_SIZE;
    }

    public void setImg(Image img) {
        this.img = img;
    }

    public Entity getEntity(int xPosition, int yPosition) {
        return table[xPosition][yPosition];
    }

    public void render(GraphicsContext gc) {
        gc.drawImage(img, x, y);
    }

    public abstract void update();

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
}
