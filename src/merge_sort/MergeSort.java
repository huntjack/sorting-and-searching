package merge_sort;

import java.util.Arrays;

public class MergeSort {
    public static int[] mergeSort(int[] array) {
        int[] temp = new int[array.length];
        int rightEnd = array.length - 1;
        mergeSort(array, temp, 0, rightEnd);
        return array;
    }
    private static void mergeSort(int[] array, int[] temp, int leftStart, int rightEnd) {
        if(leftStart >= rightEnd) {
            return;
        }
        int middle = (leftStart + rightEnd) / 2;
        mergeSort(array, temp, leftStart, middle);
        mergeSort(array, temp, middle + 1, rightEnd);
        merge(array, temp, leftStart, rightEnd);
    }
    private static void merge(int[] array, int[] temp, int left, int right) {
        int leftStart = left;
        int leftEnd = (left + right) / 2;
        int rightStart = leftEnd + 1;
        int rightEnd = right;
        int tempIndex = leftStart;
        while(leftStart <= leftEnd && rightStart <= rightEnd) {
            if(array[leftStart] <= array[rightStart]) {
                temp[tempIndex] = array[leftStart++];
            } else {
                temp[tempIndex] = array[rightStart++];
            }
            tempIndex++;
        }
        if(leftStart <= leftEnd) {
            int size = leftEnd - leftStart + 1;
            System.arraycopy(array, leftStart, temp, tempIndex, size);
        } else {
            int size = rightEnd - rightStart + 1;
            System.arraycopy(array, rightStart, temp, tempIndex, size);
        }
        int size = right - left + 1;
        System.arraycopy(temp, left, array, left, size);
    }
    public static void print(int[] array) {
        Arrays.stream(array)
                .forEach(element ->
                        System.out.println(element));
    }
    public static void main(String[] args) {
        int[] array = {23, 65, 7, 34, 89, 54, 2, 8};
        array = mergeSort(array);
        print(array);
    }
}
