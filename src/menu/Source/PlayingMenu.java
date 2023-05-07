package menu.Source;

import javafx.animation.AnimationTimer;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import map.CreateMap;

import static main.BombermanGame.*;

public class PlayingMenu {
    public static Label healthLabel;
    public static Label levelLabel;

    public static void setup(Stage stage) {
        new CreateMap(stage, level);
        AnimationTimer timer = new AnimationTimer() {
            private long lastUpdate = 0;

            @Override
            public void handle(long now) {
                if (typeMenu == MENU.PLAYING) {
                    newGame = true;
                    stop();
                    return;
                } else {
                    update();
                    render(stage);
                }
                long frameTime = (now - lastUpdate) / 1000000;
                if (frameTime < FPS_GAME) {
                    try {
                        Thread.sleep(FPS_GAME - frameTime);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
                lastUpdate = System.nanoTime();
            }
        };
        timer.start();
    }
}
