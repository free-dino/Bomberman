package entities.bomb;

import audio.Sound;
import entities.Entity;
import entities.character.enemy.Enemy;
import entities.character.enemy.Oneal;
import entities.item.BombItem;
import entities.item.Item;
import entities.item.PortalItem;
import entities.item.SpeedItem;
import entities.block.Brick;
import entities.block.Wall;
import graphics.Sprite;
import javafx.application.Platform;
import javafx.scene.image.Image;

import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import static main.BombermanGame.*;

public class Bomb extends Entity {
    public static int cnt = 0;
    private final int size;
    private final List<Entity> entities;
    private boolean exploded = false;
    private Entity portalPos = null;

    public Bomb(int x, int y, Image img, List<Entity> entities, int size) {
        super(x, y, img);
        this.size = size;
        this.entities = entities;
        cnt++;
        if (table[x][y] instanceof PortalItem) {
            portalPos = table[x][y];
        }
        table[x][y] = this;
    }

    public void getImg() {
        if (exploded) {
            sprite = Sprite.movingSprite(Sprite.bomb_exploded, Sprite.bomb_exploded1, Sprite.bomb_exploded2, animationTime,20);
        } else {
            sprite = Sprite.movingSprite(Sprite.bomb, Sprite.bomb_1, Sprite.bomb_2, animationTime, 20);
        }
        img = sprite.getFxImage;
    }

    /**
     * Hàm này check xem Bomb có bị chặn hay ko
     * @param locationX
     * @param locationY
     * @return
     */
    private boolean checkBreak(int locationX, int locationY) {
        Entity cur = table[locationX][locationY];
        if (cur instanceof Wall) {
            return true;
        }
        if (cur instanceof Brick) {
            ((Brick) cur).setExploded();
            return true;
        }
        return false;
    }

    public boolean isExploded() {
        return exploded;
    }

    public void setExplode() {
        this.animationTime = 69;
    }

    private void hurtingByExplosion(int locationX, int locationY) {
        Entity cur = table[locationX][locationY];
        if (cur instanceof Enemy) {
            for (Entity e : enemies) {
                if (e.getLocationX() == locationX && e.getLocationY() == locationY) {
                    e.setHurt();
                }
            }
        }
        if (cur instanceof Oneal) cur.setDied();
        if (cur instanceof Item) cur.setDied();
        if (cur instanceof SpeedItem) cur.setDied();
        if (cur instanceof BombItem) cur.setDied();
        if (cur instanceof Bomb && !((Bomb) cur).isExploded()) ((Bomb) cur).setExplode();
        if (bomber.getBomberX() == locationX && bomber.getBomberY() == locationY && !bomber.isProtected()) {
            bomber.setHurt();
        }
    }

    @Override
    public void update() {
        animationTime++;
        int px = x / Sprite.SCALED_SIZE;
        int py = y / Sprite.SCALED_SIZE;
        if (table[px][py] instanceof PortalItem) {
            portalPos = table[px][py];
        }
        table[px][py] = this;

        if (animationTime == 70) {
            exploded = true;
            Sound.explosion.play();
            Platform.runLater(
                    () -> {
                        for (int c = 1; c <= size; c++) {
                            int i = x / Sprite.SCALED_SIZE - c;
                            int j = y / Sprite.SCALED_SIZE;
                            if (checkBreak(i, j)) break;
                            if (c < size) {
                                entities.add(new Flame(i, j, null, Flame.FlameDirection.OH, entities));
                            } else {
                                entities.add(new Flame(i, j, null, Flame.FlameDirection.L, entities));
                            }
                        }
                        for (int c = 1; c <= size; c++) {
                            int i = x / Sprite.SCALED_SIZE + c;
                            int j = y / Sprite.SCALED_SIZE;
                            if (checkBreak(i, j)) break;
                            if (c < size) {
                                entities.add(new Flame(i, j, Sprite.explosion_horizontal.getFxImage, Flame.FlameDirection.OH, entities));
                            } else {
                                entities.add(new Flame(i, j, Sprite.explosion_horizontal_right_last.getFxImage, Flame.FlameDirection.R, entities));
                            }
                        }
                        for (int c = 1; c <= size; c++) {
                            int i = x / Sprite.SCALED_SIZE;
                            int j = y / Sprite.SCALED_SIZE - c;
                            if (checkBreak(i, j)) break;
                            if (c < size) {
                                entities.add(new Flame(i, j, Sprite.explosion_vertical.getFxImage, Flame.FlameDirection.OV, entities));
                            } else {
                                entities.add(new Flame(i, j, Sprite.explosion_vertical_top_last.getFxImage, Flame.FlameDirection.U, entities));
                            }
                        }
                        for (int c = 1; c <= size; c++) {
                            int i = x / Sprite.SCALED_SIZE;
                            int j = y / Sprite.SCALED_SIZE + c;
                            if (checkBreak(i, j)) break;
                            if (c < size) {
                                entities.add(new Flame(i, j, Sprite.explosion_vertical.getFxImage, Flame.FlameDirection.OV, entities));
                            } else {
                                entities.add(new Flame(i, j, Sprite.explosion_vertical_down_last.getFxImage, Flame.FlameDirection.D, entities));
                            }
                        }
                        Timer bombTimer = new Timer();
                        bombTimer.schedule(new TimerTask() {
                            @Override
                            public void run() {
                                for (int c = 0; c <= size; c++) {
                                    int px = x / Sprite.SCALED_SIZE - c;
                                    int py = y / Sprite.SCALED_SIZE;
                                    if (checkBreak(px, py)) break;
                                    hurtingByExplosion(px, py);
                                }
                                for (int c = 1; c <= size; c++) {
                                    int px = x / Sprite.SCALED_SIZE + c;
                                    int py = y / Sprite.SCALED_SIZE;
                                    if (checkBreak(px, py)) break;
                                    hurtingByExplosion(px,py);
                                }
                                for (int c = 1; c <= size; c++) {
                                    int px = x / Sprite.SCALED_SIZE;
                                    int py = y / Sprite.SCALED_SIZE - c;
                                    if (checkBreak(px, py)) break;
                                    hurtingByExplosion(px, py);
                                }
                                for (int c = 1; c <= size; c++) {
                                    int px = x / Sprite.SCALED_SIZE;
                                    int py = y / Sprite.SCALED_SIZE + c;
                                    if (checkBreak(px, py)) break;
                                    hurtingByExplosion(px, py);
                                }
                            }
                        }, 10);
                    });
        }
        if (animationTime == 80) {
            Platform.runLater(
                    () -> {
                        table[px][py] = portalPos;
                        cnt--;
                        entities.remove(this);
                    });
        }
        if (animationTime > 1000000) {
            animationTime = 0;
        }
        getImg();
    }

}
