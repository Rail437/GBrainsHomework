package Lesson04;


public class MainClass {
    public static void main (String [] args){
        Worker person1 = new Worker();
        Worker [] arrayWorkers = new Worker[5];
        arrayWorkers[0] = new Worker ("Bibo", "Cleaner", 3243395950L, 35000, 32 );
        arrayWorkers[1] = new Worker ("Mark", "Boss", 9053657799L, 120000, 61);
        arrayWorkers[2] = new Worker("Alex", "Coocker", 9367482470L, 45000, 32);
        arrayWorkers[3] = new Worker ("Anna", "Secretary", 9394752427L, 30000, 28);
        arrayWorkers[4] = new Worker ("John", "Artist", 7632348923L, 40000, 44);

        //Задание 4.
        System.out.println("Worker's name " + arrayWorkers[0].getName() + ", post " + arrayWorkers[0].getPost());


    for (int i = 0; i < arrayWorkers.length; i++){
        if (arrayWorkers[i].getAge() > 40){
            System.out.print("Worker's name: " + arrayWorkers[i].getName() + ", post: " + arrayWorkers[i].getPost());
            System.out.print(", pthone namber: " + arrayWorkers[i].getPhoneNumber() + ", salary: " + arrayWorkers[i].getSalary());
            System.out.print(", age: " + arrayWorkers[i].getAge() + ", ID: " + arrayWorkers[i].getId());
            System.out.println();
        }
    }
    upSalary(arrayWorkers);

    }

    private static void upSalary (Worker[] person ){
        for (int i = 0; i < person.length; i++) {
            if (person[i].getAge() > 35) {
                person[i].setSalary(person[i].getSalary() + 10000);
            }
        }

    }

}
