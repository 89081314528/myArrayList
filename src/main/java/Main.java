import java.util.Arrays;

import static java.lang.Integer.parseInt;

public class Main {
    public static void main(String[] args) {
//        IntListImpl list = new IntListImpl(10000);
//        for (int i = 0; i < list.getCapacity(); i++) {
//            list.add((int) (Math.random() * 100));
//        }
//        int[] list1 = list.toArray();
//        int[] list2 = list.toArray();


//        long start1 = System.currentTimeMillis();
//        sortBubble(list1);
//        System.out.println("Время пузырьковой сортировки " + (System.currentTimeMillis() - start1));
//
//        long start2 = System.currentTimeMillis();
//        sortSelection(list2);
//        System.out.println("Время сортировки выбором " + (System.currentTimeMillis() - start2));

    }



    private static void swapElements(int[] arr, int indexA, int indexB) {
        int tmp = arr[indexA];
        arr[indexA] = arr[indexB];
        arr[indexB] = tmp;
    }
}
