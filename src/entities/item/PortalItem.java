package entities.item;

import graphics.Sprite;
import javafx.application.Platform;
import javafx.scene.image.Image;

import static main.BombermanGame.entities;
import static main.BombermanGame.table;

public class PortalItem extends Item {

    public PortalItem(int xUnit, int yUnit, Image img) {
        super(xUnit, yUnit, img);
    }

    @Override
    public void update() {
        img = Sprite.portal.getFxImage;
        table[getLocationX()][getLocationY()] = this;
    }
}
