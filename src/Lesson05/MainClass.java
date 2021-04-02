package Lesson05;


public class MainClass {
    public static void main(String[] args){
    Cat tom = new Cat("Tom");
    Dog jack = new Dog("Jack");
    Dog bobik = new Dog("Bobik");
    Horse horse = new Horse("Black Horse");
    Bird bird = new Bird("Kesha");

    tom.ollInfo();
    tom.run(100);
    tom.jump(5);
    tom.swim(50);
    System.out.println("-----------------------------------------------------------------");
    jack.ollInfo();
    jack.run(500);
    jack.jump(20);
    jack.swim(10);
    System.out.println("-----------------------------------------------------------------");
    bobik.ollInfo();
    bobik.run(500);
    bobik.jump(20);
    bobik.swim(10);
    System.out.println("-----------------------------------------------------------------");
    horse.ollInfo();
    horse.run(1500);
    horse.jump(2);
    horse.swim(20);
    System.out.println("-----------------------------------------------------------------");
    bird.ollInfo();
    bird.run(50);
    bird.jump(2);
    bird.swim(20);

        //Animal bob = new Animal("Zhuk");
    //System.out.println(bob.getName());
    }

}
