package Lesson05;

import java.util.Random;

public class Bird extends Animal {
    private static final Random RANDOM = new Random();
    private int fly = RANDOM.nextInt(1500) + 1000;

    Bird(String name) {
        super("Bird", name, RANDOM.nextInt(10), 0, RANDOM.nextInt(6) + 2);
    }

    public void swim(int distance){
        System.out.println(this.getName() + " don't swim.");
    }
    public void ollInfo(){
       //this.ollInfo();
        System.out.println("This bird can fly: " + this.fly + " meters");
    }
}
