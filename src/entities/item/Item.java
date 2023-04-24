package entities.item;

import audio.SoundManager;
import entities.Entity;
import javafx.scene.image.Image;

public abstract class Item extends Entity {
    protected boolean pickedUp = false;

    public Item(int xUnit, int yUnit, Image img) {
        super(xUnit, yUnit, img);
    }

    public void pick() {
        this.pickedUp = true;
        SoundManager.collect_item.play();
    }

    public boolean isPickUp() {
        return pickedUp;
    }
}
