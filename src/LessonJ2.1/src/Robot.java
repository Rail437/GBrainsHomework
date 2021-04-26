public class Robot implements Action {
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
    public void run(Object distanse) {
        Treadmill treadmill = (Treadmill) distanse;
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
