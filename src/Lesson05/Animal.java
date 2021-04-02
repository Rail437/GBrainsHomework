package Lesson05;

public class Animal {
    private String name;
    private int runDistance;
    private int swimDistance;
    private int jumpHight;
    //private int

    protected Animal (String name){
        this.name = name;
        this.runDistance = 1;//runDistance;
        this.swimDistance = 1; //swimDistance;
        this.jumpHight = 1; //jumpHight;
    }
    /*void getRandomIntegerBetweenRange(double min, double max){
        double x = (int)(Math.random()*((max-min)+1))+min;
        this.runDistance = (int)x;
    }*/

    public void run(int distance){
        if(distance <= runDistance){
            System.out.println(name + " runs " + distance + "meters");
        }else
            System.out.println(name+ " don't run this distance.");
    }
    public void swim(int distance){
        if(distance <= swimDistance){
            System.out.println(name + " swims " + distance + "meters");
        }else
            System.out.println(name + " don't swim this distance.");
    }
    public void jump(int distance){
        if(distance <= jumpHight){
            System.out.println(name + " jumps " + distance + "meters");
        }else
            System.out.println(name + " don't jump this distance.");
    }

    /*protected int getRunDistance(){
        return this.runDistance;
    }*/
    protected String getName(){
        return name;
    }
    protected void setRunDistance(int Distance){
        this.runDistance = Distance;
    }
}
