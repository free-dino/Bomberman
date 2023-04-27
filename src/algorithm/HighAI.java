package algorithm;

import entities.Entity;
import entities.character.enemy.Enemy;

public class HighAI {
    public static Entity.Direction getDirection(Enemy enemy) {
        if (false) {
            return null; // chưa có ý tường
        } else {
            return MediumAI.getDirection(enemy);
        }
    }
}
