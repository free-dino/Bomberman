package entities.item;

import audio.SoundManager;
import entities.Entity;
import javafx.scene.image.Image;

public abstract class Item extends Entity {
    protected boolean pickedUp = false;

    public Item(int xUnit, int yUnit, Image img) {
        super(xUnit, yUnit, img);
    }
    public static SoundManager collect = SoundManager.collect_item;

    public void pick() {


    }

    public boolean isPickUp() {
        return pickedUp;
    }
}
