package sample.Lesson6;

import java.util.ArrayList;
import java.util.List;

public class ExerciseApp {
    public static void main(String[] args) {
        /*int[] box = {2,4,34,5,6,7,8,9,99,};
        int[] buff = new int[0];
        int[] modbox = methodOne(box);
        for (int i = 0; i < modbox.length ; i++) {
            System.out.print(modbox[i]);
        }
        System.out.println();
        System.out.println(checkNumbers(buff));*/
    }
    public int[] methodOne(int[] numbers){
        int count = 0;
        int index = 0;
        for (int i = 0; i < numbers.length; i++) {
            if(numbers[i] == 4){
                count++;
            }
        }
        if (count == 0) throw new RuntimeException("Массив не содержит ни одной цифры 4");
        for (int i = 0; i < numbers.length; i++){
            if(numbers[i] == 4){
                count--;
            }
            if(count == 0){
                index = i+1;
                break;
            }
        }
        int[] result = new int[numbers.length-index];
        int i = 0;
        while (index < numbers.length){
            result[i] = numbers[index];
            i++;
            index++;
        }
        return result;
    }

    public boolean checkNumbers(int[] numbers){
        for (int i = 0; i < numbers.length ; i++) {
            if (numbers[i] == 1 || numbers[i] == 4){
                return true;
            }
        }
        return false;
    }
}
