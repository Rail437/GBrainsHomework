package Lesson05;

public abstract class Animal {
    private String name;
    private int runDistance;
    private int swimDistance;
    private int jumpHight;
    private String kind;

    protected Animal (String kind, String name,int runDistance, int swimDistance, int jumpHight){
        this.kind = kind;
        this.name = name;
        this.runDistance = runDistance;
        this.swimDistance = swimDistance;
        this.jumpHight = jumpHight;
    }

    public void ollInfo(){
        System.out.println(name +" is " + kind);
        System.out.println("Max. run distance: " + runDistance + ", Max. swim distance: " + swimDistance + ", max. jump hight:" + jumpHight + " meters");
    }
    public void run(int distance){
        if(distance <= runDistance){
            System.out.println(name + " run " + distance + " meters");
        }else
            System.out.println(name+ " don't run " + distance + " meters");
    }
    public void swim(int distance){
        if(distance <= swimDistance){
            System.out.println(name + " swim " + distance + " meters");
        }else
            System.out.println(name + " don't swim " + distance + " meters");
    }
    public void jump(int distance){
        if(distance <= jumpHight){
            System.out.println(name + " jump " + distance + " meters");
        }else
            System.out.println(name + " don't jump " + distance + " meters");
    }

    public int getRunDistance(){
        return this.runDistance;
    }
    public String getName(){
        return name;
    }
    public void setRunDistance(int Distance){
        this.runDistance = Distance;
    }
}
