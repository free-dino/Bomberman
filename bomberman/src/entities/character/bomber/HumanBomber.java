package entities.character.bomber;

import entities.Entity;
import javafx.scene.SnapshotParameters;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;

public class HumanBomber extends Entity {

    public HumanBomber(int x, int y, Image img) {
        super( x, y, img);
    }

    @Override
    public void update() {

    }
}
