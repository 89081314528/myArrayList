import java.util.Arrays;

import static java.lang.Integer.parseInt;

public class Main {
    public static void main(String[] args) {
        IntListImpl list = new IntListImpl(10000);
        for (int i = 0; i < list.getCapacity(); i++) {
            list.add((int) (Math.random() * 100));
        }
        int[] list1 = list.toArray();
        int[] list2 = list.toArray();
        int[] list3 = list.toArray();


        long start1 = System.currentTimeMillis();
        sortBubble(list1);
        System.out.println("Время пузырьковой сортировки " + (System.currentTimeMillis() - start1));

        long start2 = System.currentTimeMillis();
        sortSelection(list2);
        System.out.println("Время сортировки выбором " + (System.currentTimeMillis() - start2));

        long start3 = System.currentTimeMillis();
        sortInsertion(list3);
        System.out.println("Время сортировки вставками " + (System.currentTimeMillis() - start3));

    }

    public static void sortBubble(int[] arr) {
        for (int i = 0; i < arr.length - 1; i++) {
            for (int j = 0; j < arr.length - 1 - i; j++) {
                if (arr[j] > arr[j + 1]) {
                    swapElements(arr, j, j + 1);
                }
            }
        }
    }

    public static void sortSelection(int[] arr) {
        for (int i = 0; i < arr.length - 1; i++) {
            int minElementIndex = i;
            for (int j = i + 1; j < arr.length; j++) {
                if (arr[j] < arr[minElementIndex]) {
                    minElementIndex = j;
                }
            }
            swapElements(arr, i, minElementIndex);
        }
    }

    public static void sortInsertion(int[] arr) {
        for (int i = 1; i < arr.length; i++) {
            int temp = arr[i];
            int j = i;
            while (j > 0 && arr[j - 1] >= temp) {
                arr[j] = arr[j - 1];
                j--;
            }
            arr[j] = temp;
        }
    }

    private static void swapElements(int[] arr, int indexA, int indexB) {
        int tmp = arr[indexA];
        arr[indexA] = arr[indexB];
        arr[indexB] = tmp;
    }
}
