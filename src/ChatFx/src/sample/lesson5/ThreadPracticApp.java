package sample.lesson5;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;

public class ThreadPracticApp {
    static String buffer = "C";
    static int count = 0;

    /**
     * Почему тут count работает и не пишет что ошибка?
     * Ты же говорил, что count++ в этом случае использовать нельзя.
     * Я что то не понял(
     */
    public static void main(String[] args) {
        Object locker = new Object();
        ExecutorService ThreadsPractic = Executors.newSingleThreadExecutor();
        while (count < 5){
            ThreadsPractic.execute(()-> {
                while (buffer.equals("C") && count < 5){
                    System.out.print("A");
                    buffer = "A";
                }
            });
            ThreadsPractic.execute(()->{
                while (buffer.equals("A") && count < 5){
                    System.out.print("B");
                    buffer = "B";
                }
            });
            ThreadsPractic.execute(()->{
                while (buffer.equals("B") && count < 5){
                    System.out.print("C");
                    buffer = "C";
                    count++;
                }
            });
            if (count == 5){
                System.out.println("\nEnd");
                ThreadsPractic.shutdownNow();
            }
        }
        Threads_ver2();
    }
    public static void Threads_ver2(){
        Object locker = new Object();
        AtomicReference<String> box = new AtomicReference<>("C");
        AtomicInteger count2 = new AtomicInteger(0);

        Thread writeA = new Thread(()->{
            for (int i = 0; i < 5; i++) {
                synchronized (locker) {
                    while (!box.get().equals("C")) {
                        try {
                            locker.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    System.out.print("A");
                    box.set("A");
                    if (count2.get() == 5) {
                        break;
                    }
                    locker.notifyAll();
                }
            }
        });
        Thread writeB = new Thread(()->{
            for (int i = 0; i < 5; i++){
                synchronized (locker){
                    while (!box.get().equals("A")){
                        try {
                            locker.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    System.out.print("B");
                    box.set("B");
                    if(count2.get()==5){
                        break;
                    }
                    //count2.getAndIncrement();
                    locker.notifyAll();
                }
            }
        });
        Thread writeC = new Thread(()->{
            for (int i = 0; i < 5; i++){
                synchronized (locker){
                    while (!box.get().equals("B")){
                        try {
                            locker.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    System.out.print("C");
                    box.set("C");
                    if(count2.get()==5){
                        break;
                    }
                    count2.getAndIncrement();
                    locker.notifyAll();
                }
            }
        });
        writeA.start();
        writeB.start();
        writeC.start();
    }
}