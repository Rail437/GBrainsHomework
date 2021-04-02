package Lesson05;
import java.util.Random;

public class Cat extends Animal {

    private static final Random RANDOM = new Random();

    Cat(String name) {
        super("cat", name, RANDOM.nextInt(300) + 100, 0, RANDOM.nextInt(6) + 2);
    }

    public void swim(int distance){
        System.out.println(this.getName() + " don't swim.");
    }
}