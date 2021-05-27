import java.util.ArrayList;

public class Lesson1Main implements ArrayToList,Swap{
    public static void main(String[] args) {
        String []arrays = {"H","e","o","l","l"};
        Integer []arraysInt = {1,2,5,4,3};
        Swap.swapElements(arrays,2,4);
        Swap.swapElements(arraysInt,2,4);
        ArrayList StringList = ArrayToList.arrayToArrayList(arrays);
        ArrayList IntList = ArrayToList.arrayToArrayList(arraysInt);
        System.out.println(StringList);
        System.out.println(IntList);

        Orange orange = new Orange();
        Orange orange1 = new Orange();
        Orange orange2 = new Orange();
        Orange orange3 = new Orange();

        Apple apple = new Apple();
        Apple apple1 = new Apple();
        Apple apple2 = new Apple();

        FruitBox<Orange> box1 = new FruitBox(orange1);
        FruitBox<Orange> orangeBox = new FruitBox<>(orange);
        FruitBox<Apple> appleFruitBox = new FruitBox<>(apple);

        box1.addFruit(orange2,orange3);

        /**Вопрос возник, наверное не правильно
        добавлять в бокс один обьект несколько раз?*/
        appleFruitBox.addFruit(apple1,apple1);
        box1.transferFruit(orangeBox);
        orangeBox.remuveFruit(orange);

        System.out.println("Вес первой коробки после удаления всех фруктов: " + box1.getWeight() + "кг.");
        System.out.println("Вес коробки: " + orangeBox.getWeight() + "кг.");
        System.out.println("Коробки апельсинов и коробка яблок одинаковые? - " + orangeBox.compare(appleFruitBox));
    }
}
