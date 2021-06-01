public interface Swap {
    static void swapElements(Object[] objects, int numberOne, int numberTwo){
        Object buffer = null;
        try {
            buffer = objects[numberOne];
            objects[numberOne] = objects[numberTwo];
            objects[numberTwo] = buffer;
        }catch (ArrayIndexOutOfBoundsException oob){
            System.out.println("Один из передаваемых значений выходит за пределы массива.");
        }
    }
}
