public class Cat implements Run, Jump {
    private String name = "Default name";
    private String type = "Cat";
    private int RunDistanse = 100;
    private float jumpHeight = 3.5f;
    private int id = 0;

    Cat (String name){
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

    /*@Override
    public void Run(Object obj) {
        System.out.println(type + " " + name + " runs distanse - " + obj + "m.");

    }*/
}
