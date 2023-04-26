package entities.character.enemy;

import algorithm.MediumAI;
import graphics.Sprite;
import javafx.scene.image.Image;

import static main.BombermanGame.*;

/**
 * Boss.
 */
public class Kondoria extends Enemy {
    public Kondoria(int xUnit, int yUnit, Image img) {
        super(xUnit, yUnit, img);
        SPEED = 1;
        HP = 4;
    }

    @Override
    public boolean isValidEnemyMove(Direction direction) {
        switch (direction) {
            case R:
                return !checkWall(x + Sprite.SCALED_SIZE + SPEED - 1, y)
                        && !checkWall(x + Sprite.SCALED_SIZE + SPEED - 1, y + Sprite.SCALED_SIZE - 1);
            case L:
                return !checkWall(x - SPEED, y)
                        && !checkWall(x - SPEED, y + Sprite.SCALED_SIZE - 1);
            case U:
                return !checkWall(x, y - SPEED)
                        && !checkWall(x + Sprite.SCALED_SIZE - 1, y - SPEED);
            default:
                // DOWN
                return !checkWall(x, y + Sprite.SCALED_SIZE + SPEED - 1)
                        && !checkWall(x + Sprite.SCALED_SIZE - 1, y + Sprite.SCALED_SIZE + SPEED - 1);
        }
    }

    @Override
    public void chooseDirection() {
        if (animationTime > 100000) animationTime = 0;
        if (x % Sprite.SCALED_SIZE == 0 && y % Sprite.SCALED_SIZE == 0) {
            direction = MediumAI.getDirection(this);
        }
    }

    protected void chooseSprite() {
        if (animationTime > 100000) animationTime = 0;
        if (beHurt) {
            img = Sprite.kondoria_dead.getFxImage;
            return;
        }
        switch (direction) {
            case U:
                if (moving) {
                    sprite = Sprite.movingSprite(Sprite.kondoria_right1, Sprite.kondoria_right2, Sprite.kondoria_right3, animationTime, 20);
                }
                break;
            case D:
                if (moving) {
                    sprite = Sprite.movingSprite(Sprite.kondoria_left1, Sprite.kondoria_left2, Sprite.kondoria_left3, animationTime, 20);
                }
                break;
            case L:
                if (moving) {
                    sprite = Sprite.movingSprite(Sprite.kondoria_left1, Sprite.kondoria_left2, Sprite.kondoria_left3, animationTime, 20);
                }
                break;
            default:
                if (moving) {
                    sprite = Sprite.movingSprite(Sprite.kondoria_right1, Sprite.kondoria_right2, Sprite.kondoria_right3, animationTime, 20);
                }
                break;
        }
        img = sprite.getFxImage();
    }

    @Override
    protected void enemyMoving() {
        chooseDirection();
        int px = (x + Sprite.SCALED_SIZE / 2) / Sprite.SCALED_SIZE;
        int py = (y + Sprite.SCALED_SIZE / 2) / Sprite.SCALED_SIZE;
        table[px][py] = null;
        sprite = Sprite.kondoria_right1;
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
                gotHurt(Sprite.kondoria_dead);
                return;
            }
            animationTime++;
            moving = false;
            interactWithBomber();
            enemyMoving();
        } catch (Exception e) {
            System.out.println("Error in Kondoria.java");
        }
    }
}
