public class Human implements Run, Jump {
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
    public void Run(Treadmill distanse) {
        if (distanse.GoToRun() <= RunDistanse) {
            System.out.println(type + " " + name + " runs distanse - " + distanse.GoToRun() + "m.");
        } else {
            System.out.println(type + " " + name + " doesn't run " + distanse.GoToRun() + "m.");
        }
    }

    @Override
    public void Jump(Wall wall) {
        if (wall.GoToJump() <= jumpHeight) {
            System.out.println(type + " " + name + " jumps - " + wall.GoToJump() + "m.");
        } else {
            System.out.println(type + " " + name + " doesn't jump " + wall.GoToJump() + "m.");
        }
    }
}
