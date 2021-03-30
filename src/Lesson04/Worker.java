package Lesson04;

public class Worker {
    private String name;
    private String post;
    private long phoneNumber;
    private int salary;
    private int age;
    private int id;
    private static int idBox = 1000000;

    public  Worker () {
        this.name = "default name";
        this.post = "default post";
        this.phoneNumber = 88005553535L ;
        this.salary = 0;
        this.age = 18;
        this.id = idBox++;
    }
    public Worker(String name, String post, long phoneNumber, int salary, int age){
        this.name = name;
        this.post = post;
        this.phoneNumber = phoneNumber;
        this.salary = salary;
        this.age = age;
        this.id = idBox++;
    }
    //GEtter
    public String getName() { return name; }
    public String getPost() { return post; }
    public long getPhoneNumber() { return phoneNumber; }
    public int getSalary() { return salary; }
    public int getAge () { return age; }
    public int getId () { return id;}

    //Setter
    public void setSalary (int salary) {this.salary = salary; }
    public void setName (String name) { this.name = name;}
    public void setPost (String post) { this.post = post;}
}
