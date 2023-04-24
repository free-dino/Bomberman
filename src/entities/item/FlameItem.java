package entities.item;

import graphics.Sprite;
import javafx.application.Platform;
import javafx.scene.image.Image;

import static main.BombermanGame.table;
import static main.BombermanGame.entities;

public class FlameItem extends Item {
    public FlameItem(int xUnit, int yUnit, Image img) {
        super(xUnit, yUnit, img);
    }

    /**
     * Chỉ update khi Item được hiện ra / show trong table[][].
     */
    @Override
    public void update() {
        img = Sprite.powerup_flames.getFxImage();
        table[getLocationX()][getLocationY()] = this;
        if (isPickUp()) {
            Platform.runLater(() -> {
                table[getLocationX()][getLocationY()] = null;
                entities.remove(this);
            });
        }
    }
}
