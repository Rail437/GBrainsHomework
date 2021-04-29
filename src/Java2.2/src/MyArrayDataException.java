public class MyArrayDataException extends Exception{
    public MyArrayDataException(int x, int y) {
        super(String.format("Parse to int exception in array[%d, %d]", x, y));
    }
}
