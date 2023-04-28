package algorithm;

import entities.Entity;
import entities.character.enemy.Enemy;
import main.BombermanGame;

import java.util.List;


public class HighAI {
    public static Entity.Direction getDirection(Enemy enemy) {
        if (false) {
            return null; // chưa có ý tường
        } else {
            return MediumAI.getDirection(enemy);
        }
    }

    static class Node implements Comparable<Node> {
        int row;
        int col;
        int dist;

        Node(int row, int col, int dist) {
            this.row = row;
            this.col = col;
            this.dist = dist;
        }

        @Override
        public int compareTo(Node other) {
            return Integer.compare(this.dist, other.dist);
        }
    }
}
