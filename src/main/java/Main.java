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


//        long start1 = System.currentTimeMillis();
//        quickSort(list1);
//        System.out.println("Время пузырьковой сортировки " + (System.currentTimeMillis() - start1));

        long start2 = System.currentTimeMillis();
        mergeSort(list2);
        System.out.println("Время сортировки выбором " + (System.currentTimeMillis() - start2));
    }

        public static void quickSort(int[] arr, int begin, int end) {
            if (begin < end) {
                int partitionIndex = partition(arr, begin, end);

                quickSort(arr, begin, partitionIndex - 1);
                quickSort(arr, partitionIndex + 1, end);
            }
        }

        public static int partition(int[] arr, int begin, int end) {
            int pivot = arr[end];
            int i = (begin - 1);

            for (int j = begin; j < end; j++) {
                if (arr[j] <= pivot) {
                    i++;

                    swapElements(arr, i, j);
                }
            }

            swapElements(arr, i + 1, end);
            return i + 1;
    }

    private static void swapElements(int[] arr, int indexA, int indexB) {
        int tmp = arr[indexA];
        arr[indexA] = arr[indexB];
        arr[indexB] = tmp;
    }

    public static void mergeSort(int[] arr) {
        if (arr.length < 2) {
            return;
        }
        int mid = arr.length / 2;
        int[] left = new int[mid];
        int[] right = new int[arr.length - mid];

        for (int i = 0; i < left.length; i++) {
            left[i] = arr[i];
        }

        for (int i = 0; i < right.length; i++) {
            right[i] = arr[mid + i];
        }

        mergeSort(left);
        mergeSort(right);

//        merge(arr, left, right);
    }
}
