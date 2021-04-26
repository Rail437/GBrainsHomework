import java.util.Objects;

public class Human implements Action {
    private String name = "Default name";
    private String type = "Human";
    private int RunDistanse = 1000;
    private float jumpHeight = 1.5f;
    private int id = 0;

    Human (String name){
        this.name = name;
        id++;
    }

    public String getName() {
        return name;
    }

    public String getType() {
        return type;
    }

    public int getRunDistanse() {
        return RunDistanse;
    }

    public float getJump() {
        return jumpHeight;
    }

    public int getId() {
        return id;
    }

    @Override
    public void run(Object Treadmill) {
        Treadmill treadmill = (Treadmill) Treadmill;
        if (treadmill.GoToRun() <= RunDistanse) {
            System.out.println(type + " " + name + " runs distanse - " + treadmill.GoToRun() + "m.");
        } else {
            System.out.println(type + " " + name + " doesn't run " + treadmill.GoToRun() + "m.");
        }
    }

    @Override
    public void jump(Object wall) {
        Wall wall1 = (Wall)wall;
        if (wall1.GoToJump() <= jumpHeight) {
            System.out.println(type + " " + name + " jumps - " + wall1.GoToJump() + "m.");
        } else {
            System.out.println(type + " " + name + " doesn't jump " + wall1.GoToJump() + "m.");
        }
    }
}
