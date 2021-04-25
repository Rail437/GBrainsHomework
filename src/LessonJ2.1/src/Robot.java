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
