package entities.character.enemy;

import graphics.Sprite;
import javafx.scene.image.Image;

import static main.BombermanGame.*;

public class Balloom extends Enemy {
    public Balloom(int xUnit, int yUnit, Image img) {
        super(xUnit, yUnit, img);
        SPEED = 1;
    }

    private void balloomMoving() {
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
                if (moving) {
                    sprite = Sprite.movingSprite(Sprite.balloom_right1, Sprite.balloom_right2, Sprite.balloom_right3, animationTime, 20);
                }
                break;
            case U:
                sprite = Sprite.balloom_left2;
                if (isValidEnemyMove(direction)) {
                    y -= SPEED;
                    moving = true;
                }
                if (moving) {
                    sprite = Sprite.movingSprite(Sprite.balloom_right1, Sprite.balloom_right2, Sprite.balloom_right3, animationTime, 20);
                }
                break;
            case L:
                sprite = Sprite.balloom_left1;
                if (isValidEnemyMove(direction)) {
                    x -= SPEED;
                    moving = true;
                }
                if (moving) {
                    sprite = Sprite.movingSprite(Sprite.balloom_left1, Sprite.balloom_left2, Sprite.balloom_left3, animationTime, 20);
                }
                break;
            case R:
                if (isValidEnemyMove(direction)) {
                    x += SPEED;
                    moving = true;
                }
                if (moving) {
                    sprite = Sprite.movingSprite(Sprite.balloom_right1, Sprite.balloom_right2, Sprite.balloom_right3, animationTime, 20);
                }
                break;
        }
        px = (x + Sprite.SCALED_SIZE / 2) / Sprite.SCALED_SIZE;
        py = (y + Sprite.SCALED_SIZE / 2) / Sprite.SCALED_SIZE;
        table[px][py] = this;
    }

    @Override
    public void update() {
        animationTime++;
        moving = false;
        chooseDirection();
        balloomMoving();
    }
}
