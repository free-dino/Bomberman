package entities.character.enemy;

import algorithm.HighAI;
import graphics.Sprite;
import javafx.scene.image.Image;

import static main.BombermanGame.*;

/**
 * Boss.
 */
public class Minvo extends Enemy {
    public Minvo(int xUnit, int yUnit, Image img) {
        super(xUnit, yUnit, img);
        SPEED = 1;
        HP = 1;
    }

    @Override
    public void chooseDirection() {
        if (animationTime > 100000) animationTime = 0;
        if (x % Sprite.SCALED_SIZE == 0 && y % Sprite.SCALED_SIZE == 0) {
            //direction = HighAI.getDirection(this);
        }
    }

    protected void chooseSprite() {
        if (animationTime > 100000) animationTime = 0;
        if (beHurt) {
            img = Sprite.movingSprite(Sprite.mob_dead1, Sprite.mob_dead2, Sprite.mob_dead3, animationTime, 20).getFxImage;
            return;
        }
        switch (direction) {
            case U:
                if (moving) {
                    sprite = Sprite.movingSprite(Sprite.minvo_right1, Sprite.minvo_right2, Sprite.minvo_right3, animationTime, 20);
                }
                break;
            case D:
                if (moving) {
                    sprite = Sprite.movingSprite(Sprite.minvo_left1, Sprite.minvo_left2, Sprite.minvo_left3, animationTime, 20);
                }
                break;
            case L:
                if (moving) {
                    sprite = Sprite.movingSprite(Sprite.minvo_left1, Sprite.minvo_left2, Sprite.minvo_left3, animationTime, 20);
                }
                break;
            default:
                if (moving) {
                    sprite = Sprite.movingSprite(Sprite.minvo_right1, Sprite.minvo_right2, Sprite.minvo_right3, animationTime, 20);
                }
                break;
        }
        img = sprite.getFxImage;
    }

    @Override
    protected void enemyMoving() {
        chooseDirection();
        int px = (x + Sprite.SCALED_SIZE / 2) / Sprite.SCALED_SIZE;
        int py = (y + Sprite.SCALED_SIZE / 2) / Sprite.SCALED_SIZE;
        table[px][py] = null;
        sprite = Sprite.minvo_right1;
        int count;
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
            if (beHurt) {
                gotHurt(Sprite.movingSprite(Sprite.mob_dead1, Sprite.mob_dead2, Sprite.mob_dead3, animationTime, 20));
                return;
            }
            animationTime++;
            moving = false;
            interactWithBomber();
            enemyMoving();
        } catch (Exception e) {
            System.out.println("Error in Minvo.java");
        }
    }
}
