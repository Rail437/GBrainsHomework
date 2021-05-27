import java.util.ArrayList;

public class FruitBox <F extends Fruit>{
    /**
     * Нужно ли делать поле Имя для каждого ящика?
     * И я что-то не понял, как сделать пустой ящик не передавая туда ни один фрукт.
     * Буду рад если немного обьяснишь.
     */
    private final F[] fruits;
    private ArrayList<F> fruitsList;

    public FruitBox(F... fruit){
        this.fruits = fruit;
        fruitsList = ArrayToList.arrayToArrayList(fruits);
    }

    public void addFruit (F... obj){
        for (int i = 0; i < obj.length ; i++) {
            fruitsList.add(obj[i]);
        }
    }
    public void remuveFruit (F... obj){
        for (int i = 0; i < obj.length ; i++) {
            fruitsList.remove(obj);
        }
    }

    public float getWeight(){
        try {
            float weight = fruitsList.size() * fruits[0].getWEIGHT();
            return weight;
        }catch (ArrayIndexOutOfBoundsException oob){ //Если нет Нулевого элемента, то мы возвращаем сразу 0.0
            return 0f;
        }
    }

    public void transferFruit(FruitBox<F> box){
        for (F el: fruitsList) {
            box.addFruit(el);
        }
        fruitsList.clear();
    }

    public boolean compare(FruitBox<?> box){
        return this.getWeight() == box.getWeight();
    }

}
