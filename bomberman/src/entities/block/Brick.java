package entities.block;

import entities.Entity;
import javafx.scene.image.Image;

public class Brick extends Entity {
    private boolean exploded = false;

    public Brick(int x, int y, Image img) {
        super(x, y, img);
    }
    public void setExploded() {
        this.exploded = true;
    }
    @Override
    public void update() {

    }
}