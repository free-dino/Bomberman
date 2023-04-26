package entities.character.enemy;

import audio.Sound;
import entities.Entity;
import entities.character.Animal;
import graphics.Sprite;
import javafx.application.Platform;
import javafx.scene.image.Image;

import java.util.Random;

import static main.BombermanGame.*;

public abstract class Enemy extends Animal {
    protected Entity old_cur = null;
    public Enemy(int xUnit, int yUnit, Image img) {
        super(xUnit, yUnit, img);
    }

    protected void chooseDirection() {
        if (animationTime > 100000) animationTime = 0;
        if (animationTime % 50 == 0 && x % Sprite.SCALED_SIZE == 0 && y % Sprite.SCALED_SIZE == 0) {
            direction = Direction.values()[new Random().nextInt(Direction.values().length)];
        }
    }

    protected void gotHurt(Sprite sprite) {
        hurt_time++;
        if (hurt_time == 1) {
            //Sound.died.play();
        }
        img = sprite.getFxImage;
        if (hurt_time == 20) {
            hurt_time = 0;
            beHurt = false;
            if (HP == 0) {
                Platform.runLater(() -> {
                    enemies.remove(this);
                    table[(x + Sprite.SCALED_SIZE / 2) / Sprite.SCALED_SIZE][(y + Sprite.SCALED_SIZE / 2) / Sprite.SCALED_SIZE] = old_cur;
                });
            }
        }
    }
    protected void checkCollideWithBomber() {
        if (bomber.getBomberX() == getLocationX() && bomber.getBomberY() == getLocationY() /*&& !bomber.isProtectded()*/) {
            bomber.setHurt();
        }
    }
}
