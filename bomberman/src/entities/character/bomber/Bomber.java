package entities.character.bomber;

import entities.Entity;
import control.KeyListener;

import entities.character.Animal;
import graphics.Sprite;

import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;

import static main.BombermanGame.*;

public class Bomber extends Animal {
    private int bomber_HP;
    private boolean moving = false;
    private int quantityOfBoms = 1;
    private boolean died = false;

    public Bomber(int x, int y, Image img, KeyListener keyListener) {
        super(x, y, img);
        keyListener = keyListener;
        quantityOfBoms = 1;
        bomber_HP = 5;
    }

    public int getBomber_HP() {
        return bomber_HP;
    }

    public void reduceBomber_HP() {
        if (!died && bomber_HP > 0) {
            this.bomber_HP--;
        }
    }

    public void setDied() {
        this.died = true;
    }

    private void chooseSprite() {
        animationTime++;
        if (animationTime > 100000) animationTime = 0;
        if (beHurt) {
            img = Sprite.movingSprite(Sprite.player_dead1, Sprite.player_dead2, Sprite.player_dead3, animationTime, 20).getFxImage();
            return;
        }
        Sprite sprite;
        switch (direction) {
            case U:
                sprite = Sprite.player_up;
                if (moving) {
                    sprite = Sprite.movingSprite(Sprite.player_up_1, Sprite.player_up_2, animationTime, 20);
                }
                break;
            case D:
                sprite = Sprite.player_down;
                if (moving) {
                    sprite = Sprite.movingSprite(Sprite.player_down_1, Sprite.player_down_2, animationTime, 20);
                }
                break;
            case L:
                sprite = Sprite.player_left;
                if (moving) {
                    sprite = Sprite.movingSprite(Sprite.player_left_1, Sprite.player_left_2, animationTime, 20);
                }
                break;
            default:
                sprite = Sprite.player_right;
                if (moving) {
                    sprite = Sprite.movingSprite(Sprite.player_right_1, Sprite.player_right_2, animationTime, 20);
                }
                break;
        }
        img = sprite.getFxImage();
    }


    public void bomberMoving() {
        int px = (x + (75 * Sprite.SCALED_SIZE) / (2 * 100)) / Sprite.SCALED_SIZE;
        int py = (y + Sprite.SCALED_SIZE / 2) / Sprite.SCALED_SIZE;
        Entity currentEntity = table[px][py]; // Cập nhật sau di chuyển.
        table[px][py] = null;
        if (keyListener.isPressed(KeyCode.D)) {
            direction = Direction.R;
            if (isValidPlayerMove(direction)) {
                x += Sprite.STEP;
                moving = true;
            }
        }
        if (keyListener.isPressed(KeyCode.A)) {
            direction = Direction.L;
            if (isValidPlayerMove(direction)) {
                x -= Sprite.STEP;
                moving = true;
            }
        }
        if (keyListener.isPressed(KeyCode.W)) {
            direction = Direction.U;
            if (isValidPlayerMove(direction)) {
                y -= Sprite.STEP;
                moving = true;
            }
        }
        if (keyListener.isPressed(KeyCode.S)) {
            direction = Direction.D;
            if (isValidPlayerMove(direction)) {
                y += Sprite.STEP;
                moving = true;
            }
        }
        table[px][py] = currentEntity;
    }

    @Override
    public void update() {
        try {
            this.moving = false;
            bomberMoving();
            chooseSprite();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
