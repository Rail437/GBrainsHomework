package Lesson05;

import java.util.Random;

public class Dog extends Animal {
    private static final Random RANDOM = new Random();

    Dog(String name) {
        super("dog", name, RANDOM.nextInt(600) + 355, RANDOM.nextInt(50) + 5, RANDOM.nextInt(7)+3);
    }

}
