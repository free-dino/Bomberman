package entities.item;

import entities.Entity;
import javafx.scene.image.Image;

public abstract class Item extends Entity {
    protected boolean pickedUp = false;

    public Item(int xUnit, int yUnit, Image img) {
        super(xUnit, yUnit, img);
    }

    public void pick() {
    }

    public boolean isPickUp() {
        return pickedUp;
    }
}
