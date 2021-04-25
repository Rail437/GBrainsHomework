
public class Main {
    public static void main(String[] args) {
        Cat barsik = new Cat("Barsik");
        Human bob = new Human("Bob");
        Robot chappi = new Robot("Chappi");
        Treadmill treadmill = new Treadmill(150);
        Wall wall = new Wall(2);

        Object[] members = {barsik,bob,chappi};
        Object[] barriers = {treadmill, wall};

        for(int i = 0; i < members.length; i++){
            for (int j = 0; j < barriers.length; j++){
                members[i].
            }
        }


        /*
        barsik.Run(treadmill);
        barsik.Jump(wall);
        bob.Run(treadmill);
        bob.Jump(wall);
        chappi.Run(treadmill);
        chappi.Jump(wall);*/
    }
}
