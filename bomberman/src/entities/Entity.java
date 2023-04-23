package entities;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import graphics.Sprite;

import static main.BombermanGame.table;

public abstract class Entity {
    protected int x; // Trục Ox từ trái sang phải;
    protected int y; // Trục Oy từ trên xuống dưới;.
    protected Image img;
    protected Sprite sprite;

    protected int animationTime = 0;
    protected int HP = 1;
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
    public enum Direction {L, R, U, D, OH, OV}

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

    public void setHurt() {
        if (!beHurt) {
            this.HP -= 1;
        }
        beHurt = true;
    }

    public int getLocationX() {
        return (x + Sprite.SCALED_SIZE / 2) / Sprite.SCALED_SIZE;
    }

    public int getLocationY() {
        return (y + Sprite.SCALED_SIZE / 2) / Sprite.SCALED_SIZE;
    }

    public Image getImg() {
        return img;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
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
}
