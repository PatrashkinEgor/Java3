package Lesson1;

/**
 * Java. Level 3. Lesson 1. Homework
 *
 * @author Egor Patrashkin
 * @version dated Nov 24, 2018
 */

import java.util.ArrayList;
import java.util.List;
import java.util.Collections;

/**Задание:
 * 1. Написать метод, который меняет два элемента массива местами (массив может быть любого ссылочного типа);
 * 2. Написать метод, который преобразует массив в ArrayList;
 */
public class MyArray {

    public static <T> void changeItemsInArr(T[] arr, int firstIndex, int secondIndex){
        if ((firstIndex<arr.length)&&(secondIndex<arr.length)){
            T temp = arr[firstIndex];
            arr[firstIndex] = arr[secondIndex];
            arr[secondIndex] = temp;
        }
    }

    public static <T> void printItemsFromArr(T[] arr){
        for (T a:arr) {
            System.out.print(a+" ");
        }
        System.out.println();
    }


    public static <T> List<T>  arrToArrList(T[] arr){
        List<T> arrList = new ArrayList<T>();
        Collections.addAll(arrList, arr);
        return arrList;
    }

    public static <T> void printItemsFromArrList(List<T> arrList){
        for (T a:arrList) {
            System.out.print(a+" ");
        }
        System.out.println();
    }


    public static void main(String[] args) {
        Integer[] arr1 = {1,2,3,4,5,6,7,8,9};
        String[] arr2 = {"a","b","c","d","e","f","g","h","i"};

        List<Integer> arrList1 = arrToArrList(arr1);
        List<String> arrList2 = arrToArrList(arr2);

        changeItemsInArr(arr1,1,2);
        changeItemsInArr(arr2,1,2);

        printItemsFromArr(arr1);
        printItemsFromArr(arr2);

        printItemsFromArrList(arrList1);
        printItemsFromArrList(arrList2);
    }
}
