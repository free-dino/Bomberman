package entities.character.enemy;

import graphics.Sprite;
import javafx.scene.image.Image;

import static main.BombermanGame.*;

public class Balloom extends Enemy {
    public Balloom(int xUnit, int yUnit, Image img) {
        super(xUnit, yUnit, img);
        SPEED = 1;
        HP = 1;
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
        int count = 0;
        switch (direction) {
            case D:
                if (isValidEnemyMove(direction)) {
                    count = 0;
                    while (isValidEnemyMove(direction) && count < this.SPEED) {
                        y += SPEED;
                        moving = true;
                        count++;
                    }
                }
                break;
            case U:
                if (isValidEnemyMove(direction)) {
                    count = 0;
                    while (isValidEnemyMove(direction) && count < this.SPEED) {
                        y -= SPEED;
                        moving = true;
                        count++;
                    }
                }
                break;
            case L:
                if (isValidEnemyMove(direction)) {
                    count = 0;
                    while (isValidEnemyMove(direction) && count < this.SPEED) {
                        x -= SPEED;
                        moving = true;
                        count++;
                    }
                }
                break;
            case R:
                if (isValidEnemyMove(direction)) {
                    count = 0;
                    while (isValidEnemyMove(direction) && count < this.SPEED) {
                        x += SPEED;
                        moving = true;
                        count++;
                    }
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
            if(beHurt){
                gotHurt(Sprite.balloom_dead);
                return;
            }
            animationTime++;
            moving = false;
            balloomMoving();
        } catch (Exception e) {
            System.out.println("Error in Balloom.java");
        }
    }
}
