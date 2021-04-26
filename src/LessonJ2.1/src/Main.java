public class Main {
    public static void main(String[] args) {
        Cat barsik = new Cat("Barsik");
        Human bob = new Human("Bob");
        Robot chappi = new Robot("Chappi");
        Treadmill treadmill = new Treadmill(150);
        Wall wall = new Wall(2);


        Action[] runneds = {barsik,bob,chappi};
        /*runneds[0] = barsik;
        runneds[1] = barsik;
        runneds[2] = barsik;/*
        //Object[] members = {barsik,bob,chappi};*/

        Object[] bariers = {treadmill,wall,treadmill};
        for (int i = 0; i < runneds.length; i++) {
            for (int j = 0; j < bariers.length; j++){
                if(bariers[j] instanceof Treadmill){
                    runneds[i].run(bariers[j]);
                }else {
                    runneds[i].jump(bariers[j]);
                }
            }
        }
        /*
        barsik.Run(treadmill);
        barsik.Jump(wall);
        bob.Run(treadmill);
        bob.Jump(wall);
        chappi.Run(treadmill);
        chappi.Jump(wall);
        */

    }
}
