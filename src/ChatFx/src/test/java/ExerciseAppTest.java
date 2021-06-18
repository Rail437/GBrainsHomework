import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;
import sample.Lesson6.ExerciseApp;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertThrows;

public class ExerciseAppTest {

    ExerciseApp ex;

    private static Stream<Arguments> arraySource() {
        List<Arguments> list = new ArrayList<>();
        int[] a = {1, 2, 3, 4, 5, 6, 7};
        int[] b = {5,6,7};
        list.add(Arguments.arguments(a, b));
        a = new int[]{4};
        b = new int[]{};
        list.add(Arguments.arguments(a, b));
        return list.stream();
    }

    private static Stream<Arguments> arraysForMethodTwo() {
        List<Arguments> list = new ArrayList<>();
        int[] a = {1, 2, 3, 4, 5, 6, 7};
        list.add(Arguments.arguments(a));
        a = new int[]{2, 3, 4, 5, 6, 7};
        list.add(Arguments.arguments(a));
        a = new  int[]{2, 3,4, 5, 6, 7};
        list.add(Arguments.arguments(a));
        a = new  int[]{2, 3,5, 6, 7, 3, 3,3,3, 3, 3,3,3, 4};
        list.add(Arguments.arguments(a));
        return list.stream();
    }

    private static Stream<Arguments> arrForMethodTwo() {
        List<Arguments> list = new ArrayList<>();
        int[] a = {2, 3, 5, 6, 7};
        list.add(Arguments.arguments(a));
        a = new int[]{2, 3, 5, 6, 7};
        list.add(Arguments.arguments(a));
        return list.stream();
    }

    @BeforeEach
    void init(){
        ex = new ExerciseApp();
    }

    @MethodSource("arraySource")
    @ParameterizedTest
    @DisplayName("Тест первого метода")
    void checkMethodOne(int[] a, int[] res){
        //int[] a = {1,2,3,4,5,6,7};
        //int[] res = {5, 6, 7};
        Assertions.assertArrayEquals(res, ex.methodOne(a), "Should be " + res);
    }

    @Test
    @DisplayName("Тест первого метода на исключение")
    void checkThrow(){
        assertThrows(RuntimeException.class, ()-> ex.methodOne(new int[]{2}));
    }

    @MethodSource("arraysForMethodTwo")
    @ParameterizedTest
    @DisplayName("Тест второго метода")
    void tester(int[] a){
        Assertions.assertTrue(()-> ex.checkNumbers(a));
    }

    @MethodSource("arrForMethodTwo")
    @ParameterizedTest
    @DisplayName("Тест второго метода на отсутствие 1 или 4")
    void testerTwo(int[] a){
        Assertions.assertFalse(()->ex.checkNumbers(a));
    }

}
