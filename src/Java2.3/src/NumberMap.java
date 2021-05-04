import java.util.*;

public class NumberMap {
    private static Map<String, List<String>> numbers = new HashMap<>();
    private static List<String> phone_number_list;

    public void add(String surname, String phone_number) {
        if (numbers.containsKey(surname)) {
            phone_number_list = numbers.get(surname);
            phone_number_list.add(phone_number);
            numbers.put(surname, phone_number_list);
        } else {
            phone_number_list = new ArrayList<>();
            phone_number_list.add(phone_number);
            numbers.put(surname, phone_number_list);
        }
    }

    public List<String> get(String surname) {
        return numbers.get(surname);
    }

    public void getOllNambers(){
        System.out.println(numbers);
    }
}
