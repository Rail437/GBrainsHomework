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
    public void Run(int distanse) {
        if (distanse <= RunDistanse) {
            System.out.println(type + " " + name + " runs distanse - " + distanse + "m.");
        } else {
            System.out.println(type + " " + name + " doesn't run " + distanse + "m.");
        }    }

    @Override
    public void Jump(float height) {
        if (height <= jumpHeight) {
            System.out.println(type + " " + name + " jumps - " + height + "m.");
        } else {
            System.out.println(type + " " + name + " doesn't jump " + height + "m.");
        }    }
}
