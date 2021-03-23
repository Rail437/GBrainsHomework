package Lesson02;

import java.util.Arrays;

public class Lesson02 {
    public static void main(String[] args) {
        int [] array_one = {0,0,1,1,0,1,0,0,1};
        int [] array_null = new int [8];
        int [] array_x2 = {1, 5, 3, 2, 11, 4, 5, 2, 4, 8, 9, 1};
        int [][] arr_square = new int [7][7];
        int [] arr_box = {1,2,3,4,5};

        print(array_one);
        print(array_null);
        print(array_x2);
        swap(array_one);
        numbers(array_null);
        arr_min_max(array_x2);
        arr_x2(array_x2);
        diagonals(arr_square);

        print(array_one);
        print(array_null);
        print(array_x2);
        print_matrix(arr_square);
        shift(arr_box, 2);
        print(arr_box);
    }

    public static void swap (int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == 0) {
                arr[i] = 1;
            } else arr[i] = 0;
        }
    }
    public static void numbers (int [] array1){
        int [] buff_arr = {1,4,7,10,13,16,19,22};
        for (int i = 0; i < array1.length; i++) {
            array1[i] = buff_arr[i];
        }
    }
    public static void arr_x2 (int [] arr) {
        for (int i = 0; i < arr.length; i ++) {
            if (arr[i] < 6) {
                arr[i] = arr[i] * 2;
            }
        }
    }
    public static void arr_min_max(int [] arr){
        int min = arr[0];
        int max = arr[0];
        for (int i = 0; i < arr.length; i++) {
            if(arr[i] > max) {
                max = arr[i];
            }
            if(arr[i] < min){
                min = arr[i];
            }
        }
        System.out.println("max array: " + max + "  min array: " + min);
    }
    public static void print (int [] array) {
        for (int j = 0; j < array.length; j++) {
            System.out.print(array[j] + " | ");
        }
        System.out.println();
    }
    public static void diagonals(int [][] arr) {
        int count = 0;
        int count_rev = 0;
        for (int i = 0; i < arr.length; i ++){
            for (int j = 0; j < arr[i].length; j++){
                if(count == j) {
                    arr[i][j] = 1;
                }
                count_rev = j;
            }
            arr[i][count_rev - count] = 1;
            count++;
        }
    }
    public static void print_matrix (int [][] arr) {
        for (int i = 0; i < arr.length; i ++) {
            for (int j = 0; j < arr[i].length; j++) {
                System.out.print(arr[i][j] + " | ");
            }
            System.out.println();
        }
    }
    public static void shift(int [] arr, int count){
        int [] arrbuff = Arrays.copyOf(arr, arr.length);
        int j = count;
        if (count < 0){
            j = arr.length + count;
        }
        for (int i = 0; i < arr.length; i++){
            if (j >= arr.length) {
                j = 0;
            }
            arr[j++] = arrbuff[i];
        }
    }
}