package entities;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import graphics.Sprite;

public abstract class Entity {
    protected int x; // Trục Ox từ trái sang phải;
    protected int y; // Trục Oy từ trên xuống dưới;.
    protected Image img;

    protected int animationTime = 0;

    protected boolean died = false;
    protected boolean beHurt = false;

    /**
     * L : Di chuyển Trái;
     * R : Di chuyển Phải;
     * U : Di chuyển Lên;
     * D : Di chuyển Xuống;
     * OH: Đứng yên theo phương ngang;
     * OV: Đứng yên theo phương dọc;
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

    public void render(GraphicsContext gc) {
        gc.drawImage(img, x, y);
    }

    public abstract void update();
}
