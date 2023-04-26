package entities.character.bomber;

import audio.SoundManager;
import entities.Entity;
import control.KeyListener;

import entities.block.Brick;
import entities.bomb.Bomb;
import entities.character.Animal;
import entities.item.BombItem;
import entities.item.FlameItem;
import entities.item.PortalItem;
import entities.item.SpeedItem;
import graphics.Sprite;

import javafx.application.Platform;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import map.MapLevel1;
import map.MapLevel2;
import map.MapLevel3;

import static audio.SoundManager.*;
import static main.BombermanGame.*;

public class Bomber extends Animal {
    private int quantityOfBoms = 1;
    private int sizeOfBomb = 1;

    private  int protectedTime = 0;
    private int hurtTick = 0;
    private boolean flamePass = false;

    public Bomber(int x, int y, Image img, KeyListener _keyListener) {
        super(x, y, img);
        keyListener = _keyListener;
        quantityOfBoms = 1;
        HP = 5;
        SPEED = 2;
    }

    public void setDied() {
        this.died = true;
    }

    public  boolean isProtected() {
        return protectedTime > 0;
    }

    protected void chooseSprite() {
        if (animationTime > 100000) animationTime = 0;
        if (beHurt) {
            img = Sprite.movingSprite(Sprite.player_dead1, Sprite.player_dead2, Sprite.player_dead3, animationTime, 20).getFxImage();
            return;
        }
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


    private void bomberMoving() {
        int px = getLocationX();
        int py = getLocationY();
        Entity currentEntity = table[px][py]; // Cập nhật sau di chuyển.
        table[px][py] = null;
        if (keyListener.isPressed(KeyCode.D)) {
            direction = Direction.R;
            int count = 0;
            while (isValidPlayerMove(direction) && count < this.SPEED) {
                x += 1;
                moving = true;
                count++;
            }
        }
        if (keyListener.isPressed(KeyCode.A)) {
            direction = Direction.L;
            int count = 0;
            while (isValidPlayerMove(direction) && count < this.SPEED) {
                x -= 1;
                moving = true;
                count++;
            }
        }
        if (keyListener.isPressed(KeyCode.W)) {
            direction = Direction.U;
            int count = 0;
            while (isValidPlayerMove(direction) && count < this.SPEED) {
                y -= 1;
                moving = true;
                count++;
            }
        }
        if (keyListener.isPressed(KeyCode.S)) {
            direction = Direction.D;
            int count = 0;
            while (isValidPlayerMove(direction) && count < this.SPEED) {
                y += 1;
                moving = true;
                count++;
            }
        }
        chooseSprite();
        table[px][py] = currentEntity;
    }

    public void placeBomb() {
        if (Bomb.quantity < quantityOfBoms && !(table[getBomberX()][getBomberY()] instanceof Bomb) && !(table[getBomberX()][getBomberY()] instanceof Brick)) {
            Platform.runLater(() -> {
                Entity object = new Bomb(getBomberX(), getBomberY(), Sprite.bomb.getFxImage(), entities, sizeOfBomb);
                entities.add(object);
            });
           Sound.place_bomb.play();
        }
    }

    public void pickItemUp() {
        int px = getBomberX();
        int py = getBomberY();
        if (table[px][py] instanceof FlameItem) {
            if (!((FlameItem) table[px][py]).isPickUp()) {
                ((FlameItem) table[px][py]).pick();
                this.sizeOfBomb++;
                System.out.println("Picked up Flame Item");
            }
            ((FlameItem) table[px][py]).pick();
        } else if (table[px][py] instanceof SpeedItem) {
            if (!((SpeedItem) table[px][py]).isPickUp()) {
                ((SpeedItem) table[px][py]).pick();
                this.SPEED++;
            }
            ((SpeedItem) table[px][py]).pick();
        } else if (table[px][py] instanceof BombItem) {
            if (!((BombItem) table[px][py]).isPickUp()) {
                ((BombItem) table[px][py]).pick();
                this.quantityOfBoms++;
            }
            ((BombItem) table[px][py]).pick();
        } else if (table[px][py] instanceof PortalItem) {
            if (!((PortalItem) table[px][py]).isPickUp() && enemies.isEmpty()) {
                ((PortalItem) table[px][py]).pick();
                try {
                    if (level == 1) {
                        level = 2;
                        new MapLevel2();
                    } else if (level == 2) {
                        level = 3;
                        new MapLevel3();
                    } else if (level == 3) {
                        level = 1;
                        new MapLevel1();
                    }
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
            }
        }
    }

    @Override
    public void update() {
        try {
            if (beHurt) {
                if (hurtTick == 0) {
//                    Sound.died.play();
                }
                if (hurtTick == 30) {
                    if (HP == 0) {
                        // end game
                        System.out.println("END GAME!!!");
                    }
                    beHurt = false;
                    hurtTick = 0;
                    protectedTime = 60 * 3 / 2;
                    return;
                }
                chooseSprite();
                hurtTick++;
                return;
            }
            protectedTime = Math.max(0, protectedTime - 1);
            animationTime++;
            this.moving = false;
            bomberMoving();
            pickItemUp();
            if (keyListener.isPressed(KeyCode.SPACE)) placeBomb();
        } catch (Exception e) {
            System.out.println("Error in Bomber.java");
        }
    }

    public int getBomberX() {
        return (x + (75 * Sprite.SCALED_SIZE) / (2 * 100)) / Sprite.SCALED_SIZE;
    }

    public int getBomberY() {
        return (y + Sprite.SCALED_SIZE / 2) / Sprite.SCALED_SIZE;
    }
    public boolean isFlamePass() {
        return flamePass;
    }


}
