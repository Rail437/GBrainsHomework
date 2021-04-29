public class Main {
    public static void main(String[] args) throws MyArraySizeException, MyArrayDataException {
        String[][] arr = new String[4][4];
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr[i].length; j++) {
                int random_number = (int) (Math.random() * 10);
                String str1 = Integer.toString(random_number);
                arr[i][j] = str1;
            }
        }
        arr[2][2] = " "; //Устанавливаем один элемент не подходящим по условиям, чтобы потом его поймать.
        String[][] arrSizeEx = {
                {"3", "2", "3", "3"},
                {"3", "6", "1", "4"},
                {"0", "2", "7",},           //Создаем массив с не правильной длиной строки.
                {"0", "2", "7", "2"}
        };
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr[i].length; j++) {
                System.out.print(arr[i][j]);
            }
            System.out.println();
        }
        //doSum(arr); // Здесь мы ловим эксепшен связанный с не правильным эелементом в массиве.
        doSum(arrSizeEx); // Здесь мы ловим эксепшен связанный с не правильным размером.
    }

    public static void doSum(String[][] strArray) throws MyArraySizeException, MyArrayDataException {

        int sum = 0;

        if (4 != strArray.length) throw new MyArraySizeException();

        for (int i = 0; i < strArray.length; i++) {

            if (4 != strArray[i].length) throw new MyArraySizeException();

            for (int j = 0; j < strArray[i].length; j++) {

                try {
                    sum += Integer.parseInt(strArray[i][j]); //преобразовываем String в Integer для суммирования с 0;
                } catch (NumberFormatException e) {
                    throw new MyArrayDataException(i, j);
                }
            }
        }
    }
}
