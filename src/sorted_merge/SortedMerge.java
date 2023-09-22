package sorted_merge;

import java.util.Arrays;

public class SortedMerge {
    public static Integer[] sortedMerge(Integer[] longArray, Integer[] shortArray) {
        if (!isValid(longArray) || !isValid(shortArray) || !areValid(longArray, shortArray)) {
            throw new IllegalArgumentException();
        }
        int targetIndex = longArray.length - 1;
        int longIndex = shortArray.length - 1;
        int shortIndex = longIndex;
        while(longIndex >= 0 && shortIndex >= 0) {
            if(longArray[longIndex] > shortArray[shortIndex]) {
                longArray[targetIndex] = longArray[longIndex--];
            } else {
                longArray[targetIndex] = shortArray[shortIndex--];
            }
            targetIndex--;
        }
        if(longIndex < 0) {
            int remainingElements = targetIndex + 1;
            System.arraycopy(shortArray, shortIndex, longArray, targetIndex, remainingElements);
        }
        return longArray;
    }
    private static boolean isValid(Integer[] array) {
        if(array == null || !isSorted(array)) {
            return false;
        }
        return true;
    }
    private static boolean isSorted(Integer[] array) {
        if(array.length <= 1) {
            return true;
        }
        int currentIndex = 1;
        int previousIndex = 0;
        while(currentIndex < array.length) {
            Integer current = array[currentIndex++];
            Integer previous = array[previousIndex++];
            if(current != null && current < previous ) {
                return false;
            }
        }
        return true;
    }
    private static boolean areValid(Integer[] longArray, Integer[] shortArray) {
        if(longArray.length != shortArray.length * 2) {
            return false;
        }
        for(int i = shortArray.length; i < longArray.length; i++) {
            if(longArray[i] != null) {
                return false;
            }
        }
        return true;
    }
    public static <T> void print(T[] array) {
        Arrays.stream(array)
                .forEach(element ->
                        System.out.println(element));
    }
    public static void main(String[] args) {
        Integer[] firstLongArray = {5, 9, 10, null, null, null};
        Integer[] firstShortArray = {6, 7, 8};
        Integer[] firstResult = sortedMerge(firstLongArray, firstShortArray);
        System.out.println("firstResult is sorted: " + isSorted(firstResult));
        print(firstResult);
        Integer[] secondLongArray = {6, 7, 8, null, null, null};
        Integer[] secondShortArray = {5, 9, 10};
        Integer[] secondResult = sortedMerge(secondLongArray, secondShortArray);
        System.out.println("secondResult is sorted: " + isSorted(secondResult));
        print(secondResult);
    }
}
