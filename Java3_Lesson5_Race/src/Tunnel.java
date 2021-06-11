public class Tunnel extends Stage {
    public Tunnel() {
        this.length = 80;
        this.description = "Тоннель " + length + " метров";
    }

    @Override
    public void go(Car c) {
        try {
            try {
                System.out.println(c.getName() + " подъехал к тоннелю, а в туннеле  : " + MainClass.carsInTunnel);
                System.out.println(c.getName() + " готовится к этапу(ждет): " + description);
                while (MainClass.carsInTunnel >= MainClass.CARS_COUNT/2){
                    Thread.sleep(100);
                }
                MainClass.carsInTunnel++;
                System.out.println(c.getName() + " начал этап: " + description);
                Thread.sleep(length / c.getSpeed() * 1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                System.out.println(c.getName() + " закончил этап: " + description);
                MainClass.carsInTunnel--;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
