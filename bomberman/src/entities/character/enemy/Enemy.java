package entities.character.enemy;

import entities.character.Animal;
import graphics.Sprite;
import javafx.scene.image.Image;

import java.util.Random;

public abstract class Enemy extends Animal {
    public Enemy(int xUnit, int yUnit, Image img) {
        super(xUnit, yUnit, img);
    }

    protected void chooseDirection() {
        if (animationTime > 100000) animationTime = 0;
        if (animationTime % 30 == 0 && x % Sprite.SCALED_SIZE == 0 && y % Sprite.SCALED_SIZE == 0) {
            direction = Direction.values()[new Random().nextInt(Direction.values().length)];
            while (isValidEnemyMove(direction)) {
                direction = Direction.values()[new Random().nextInt(Direction.values().length)];
            }
        }
    }
}
