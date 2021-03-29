package Lesson04;


public class MainClass {
    public static void main (String [] args){
        Worker person1 = new Worker();
        //Worker [] arrayWorkers = new Worker[5];
        //arrayWorkers[0] = arrayWorkers[0].setName("Bibo");
        //arrayWorkers[0] = arrayWorkers[0].setPost("Cleaner");
        //arrayWorkers[0] = arrayWorkers[0].setSalary(10000);
        //System.out.println("Worker's name " + arrayWorkers[0].getName() + ", post " + arrayWorkers[0].getPost());

    /*for (int i = 0; i < arrayWorkers.length; i++){
        if (arrayWorkers[i].getAge() > 40){
            System.out.print("Worker's name " + arrayWorkers[i].getName() + ", post " + arrayWorkers[i].getPost());
            System.out.print(", pthone namber: " + arrayWorkers[i].getPhoneNumber() + ", salary: " + arrayWorkers[i].getSalary());
            System.out.print(", age: " + arrayWorkers[i].getAge() + ", ID: " + arrayWorkers[i].getId());
            System.out.println();
        }
    }
    upSalary(arrayWorkers);*/
        //System.out.println("Worker's name: " + person1.getName() + ", post: " + person1.getPost());

    }

    private static void upSalary (Worker[] person ){
        for (int i = 0; i < person.length; i++) {
            if (person[i].getAge() > 35) {
                person[i].setSalary(person[i].getSalary() + 10000);
            }
        }
    }
}
