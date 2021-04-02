package Lesson05;

public class Cat extends Animal {
    private int runDistanseCat;
    private int jumpHigthCat;
    /*private void setRunDistance(){
        int b;
        double x = (int)(Math.random()*((10-1)+1))+1;
        b = (int)x;
        b = b * 100;
        setRunDistance(b);
    }*/

    public Cat (String name){
        super(name);
        int b = 0;
        b = randomRunDistanceCat(b);
        this.runDistanseCat = b;
        b = randomJumpDistanceCat(b);
        this.jumpHigthCat = b;
    }

    private int randomRunDistanceCat(int b){
        double x = (int)(Math.random()*((3-1)+1))+1;
        b = (int)x;
        return b * 100;
    }
    private int randomJumpDistanceCat(int b){
        double x = (int)(Math.random()*((3-1)+1))+1;
        b = (int)x;
        return b * 5;
    }

    /*public void getRunDistanceCat(){
        System.out.println(this.getName() + " run " + this.runDistanseCat + " merets.");
    }
    public void getJumpHigthCat(){
        System.out.println(this.getName() + " run " + this.jumpHigthCat + " merets.");
    }
    public void getSwimDistance(){
        System.out.println(this.getName() + " can not swim");
    }*/

    public void run(int distance){
        if(distance <= this.runDistanseCat){
            System.out.println(this.getName() + " runs " + distance + " meters");
        }else
            System.out.println(this.getName() + " don't run this distance.");
    }
    public void jump(int distance){
        if(distance <= this.jumpHigthCat){
            System.out.println(this.getName() + " jumps " + distance + " meters");
        }else
            System.out.println(this.getName() + " don't jump this distance.");
    }
    public void swim(int distance){
        System.out.println(this.getName() + " don't swim.");
    }
}
