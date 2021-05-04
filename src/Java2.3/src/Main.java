import java.util.*;

public class Main {
    public static void main(String[] args) {
    Array1();
    thoneNumbers();
    }

    public static void Array1(){
        //Создаем массив из слов.
        String []strings = {"sema", "tema", "bob",
                            "cat", "dog", "ball",
                            "coffe", "bob", "cat",
                            "salt", "bob", "sema"};
        Map<String, Integer> map = new HashMap<>();

        //Добавляем каждое слово как ключ и сразу записываем ему единицу.
        // Если слово уже было добавлено до этого, меняем значение единицы на +1.
        for (int i = 0; i < strings.length; i++) {
            if (map.containsKey(strings[i])) {
                map.put(strings[i], map.get(strings[i]) + 1);
            } else {
                map.put(strings[i], 1);
            }
        }
        System.out.println(map);
    }

    private static void thoneNumbers() {
        /**Я не совсем понял, нужно чтобы у меня метод
        получал какое то имя и уже на основе его он создавал
        контакты или просто вот так можно самому указать в самом методе?*/

        NumberMap contacts = new NumberMap();
        contacts.add("Popov", "8999123321");
        contacts.add("Kotov", "8912155326");
        contacts.add("Dmitriev", "8917155552");
        contacts.add("Zanimalov", "88005553535");
        contacts.add("Taranov", "8913455672");
        contacts.add("Bolshova", "899999999");
        contacts.add("Kotov", "899111111");
        contacts.add("Kotov", "89923231999");
        contacts.add("Deripaska", "8888123113");

        System.out.println("Popov tel: " + contacts.get("Popov"));
        System.out.println("Kotov tel: " + contacts.get("Kotov"));
        System.out.println("Dmitriev tel: " + contacts.get("Dmitriev"));

        //contacts.getOllNambers(); // Этот метод выводит все фамилии и номера.
    }
}

