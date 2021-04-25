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
    public void Run(int distanse) {
        if (distanse <= RunDistanse) {
            System.out.println(type + " " + name + " runs distanse - " + distanse + "m.");
        } else {
            System.out.println(type + " " + name + " doesn't run " + distanse + "m.");
        }
    }

    @Override
    public void Jump(float height) {
        if (height <= jumpHeight) {
            System.out.println(type + " " + name + " jumps - " + height + "m.");
        } else {
            System.out.println(type + " " + name + " doesn't jump " + height + "m.");
        }    }

    /*@Override
    public void Run(Object obj) {
        System.out.println(type + " " + name + " runs distanse - " + obj + "m.");

    }*/
}
