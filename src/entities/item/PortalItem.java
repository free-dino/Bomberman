package entities.item;

import graphics.Sprite;
import javafx.scene.image.Image;

import static main.BombermanGame.table;

public class PortalItem extends Item {

    public PortalItem(int xUnit, int yUnit, Image img) {
        super(xUnit, yUnit, img);
    }

    @Override
    public void update() {
        table[getLocationX()][getLocationY()] = this;
        img = Sprite.portal.getFxImage;
    }
}
