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
        int c = 0;
        b = randomRunDistanceCat(b);
        this.runDistanseCat = b;
        c = randomJumpDistanceCat(c);
        this.jumpHigthCat = c;
        System.out.println("b = " + b);
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

    public int getRunDistanceCat(){
        return runDistanseCat;
    }
    public int getJumpHigthCat(){
        return jumpHigthCat;
    }

    /**/
}
