package algorithm;

import entities.Entity.Direction;

import java.util.Random;

public class LowAI {
    public static Direction getDirection() {
        return Direction.values()[new Random().nextInt(Direction.values().length)];
    }
}
