package Lesson05;

import java.util.Random;

public class Horse extends Animal {
    private static final Random RANDOM = new Random();

    Horse(String name) {
        super("Horse", name, RANDOM.nextInt(1000) + 900, RANDOM.nextInt(100) + 5, RANDOM.nextInt(2)+1);
    }

}
