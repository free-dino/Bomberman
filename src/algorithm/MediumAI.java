package algorithm;

import entities.Entity.Direction;
import entities.character.Animal;
import entities.character.enemy.Enemy;

import java.util.Random;

import static main.BombermanGame.bomber;

public class MediumAI {
    public static Direction getDirection(Enemy enemy) {
        if (!bomber.isProtected()) {
            if (enemy.getLocationX() < bomber.getLocationX() && enemy.isValidEnemyMove(Direction.R)) {
                return Direction.R;
            } else if (enemy.getLocationX() > bomber.getLocationX() && enemy.isValidEnemyMove(Direction.L)) {
                return Direction.L;
            } else if (enemy.getLocationY() < bomber.getLocationY() && enemy.isValidEnemyMove(Direction.D)) {
                return Direction.D;
            } else if (enemy.getLocationY() > bomber.getLocationY() && enemy.isValidEnemyMove(Direction.U)) {
                return Direction.U;
            }
        }
        return LowAI.getDirection();
    }
}
