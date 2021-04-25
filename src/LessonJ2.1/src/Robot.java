public class Robot implements Run, Jump {
    private String name = "Default name";
    private String type = "Robot";
    private int RunDistanse = 5000;
    private float jumpHeight = 2.5f;
    private int id = 0;

    Robot (String name){
        this.name = name;
        id++;
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
        }
    }
}
