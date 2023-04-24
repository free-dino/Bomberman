package entities.item;

import graphics.Sprite;
import javafx.scene.image.Image;

import static main.BombermanGame.*;

public class PortalItem extends Item {
    public PortalItem(int x, int y, Image img) {
        super(x, y, img);
    }

    @Override
    public void update() {
        table[x / Sprite.SCALED_SIZE][y / Sprite.SCALED_SIZE] = this;
        img = Sprite.portal.getFxImage;
    }
}