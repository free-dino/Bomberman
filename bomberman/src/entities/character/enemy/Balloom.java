package entities.character.enemy;

import graphics.Sprite;
import javafx.scene.image.Image;

import static main.BombermanGame.*;

public class Balloom extends Enemy {
    public Balloom(int xUnit, int yUnit, Image img) {
        super(xUnit, yUnit, img);
        SPEED = 1;
    }

    protected void chooseSprite() {
        if (animationTime > 100000) animationTime = 0;
        if (beHurt) {
            img = Sprite.balloom_dead.getFxImage();
            return;
        }
        switch (direction) {
            case U:
                if (moving) {
                    sprite = Sprite.movingSprite(Sprite.balloom_right1, Sprite.balloom_right2, Sprite.balloom_right3, animationTime, 20);
                }
                break;
            case D:
                if (moving) {
                    sprite = Sprite.movingSprite(Sprite.balloom_left1, Sprite.balloom_left2, Sprite.balloom_left3, animationTime, 20);
                }
                break;
            case L:
                if (moving) {
                    sprite = Sprite.movingSprite(Sprite.balloom_left1, Sprite.balloom_left2, Sprite.balloom_left3, animationTime, 20);
                }
                break;
            default:
                if (moving) {
                    sprite = Sprite.movingSprite(Sprite.balloom_right1, Sprite.balloom_right2, Sprite.balloom_right3, animationTime, 20);
                }
                break;
        }
        img = sprite.getFxImage();
    }

    private void balloomMoving() {
        chooseDirection();
        int px = (x + Sprite.SCALED_SIZE / 2) / Sprite.SCALED_SIZE;
        int py = (y + Sprite.SCALED_SIZE / 2) / Sprite.SCALED_SIZE;
        table[px][py] = null;
        sprite = Sprite.balloom_right1;
        switch (direction) {
            case D:
                if (isValidEnemyMove(direction)) {
                    y += SPEED;
                    moving = true;
                }
                break;
            case U:
                if (isValidEnemyMove(direction)) {
                    y -= SPEED;
                    moving = true;
                }
                break;
            case L:
                if (isValidEnemyMove(direction)) {
                    x -= SPEED;
                    moving = true;
                }
                break;
            case R:
                if (isValidEnemyMove(direction)) {
                    x += SPEED;
                    moving = true;
                }
                break;
        }
        chooseSprite();
        px = (x + Sprite.SCALED_SIZE / 2) / Sprite.SCALED_SIZE;
        py = (y + Sprite.SCALED_SIZE / 2) / Sprite.SCALED_SIZE;
        table[px][py] = this;
    }

    @Override
    public void update() {
        try {
            animationTime++;
            moving = false;
            balloomMoving();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
