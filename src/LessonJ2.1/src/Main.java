
public class Main {
    public static void main(String[] args) {
        Cat barsik = new Cat("Barsik");
        Human bob = new Human("Bob");
        Robot chappi = new Robot("Chappi");
        Treadmill treadmill = new Treadmill(150);
        Wall wall = new Wall(2);

        Object[] members = {barsik,bob,chappi};
        /*Object[] barriers = {treadmill.GoToRun(), wall.GoToJump()};

       for(int i = 0; i < members.length; i++){
            System.out.println(members[i]);
       }

        for (int j = 0; j < barriers.length; j++){
            barsik.Run(barriers[j]);
        }*/

        barsik.Run(treadmill.GoToRun());
        barsik.Jump(wall.GoToJump());
        bob.Run(treadmill.GoToRun());
        bob.Jump(wall.GoToJump());
        chappi.Run(treadmill.GoToRun());
        chappi.Jump(wall.GoToJump());
    }
}
