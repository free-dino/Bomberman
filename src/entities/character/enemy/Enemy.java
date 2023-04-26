package entities.character.enemy;

import algorithm.LowAI;
import entities.character.Animal;
import graphics.Sprite;
import javafx.scene.image.Image;


public abstract class Enemy extends Animal {
    public Enemy(int xUnit, int yUnit, Image img) {
        super(xUnit, yUnit, img);
    }

    public abstract void chooseDirection();
}
