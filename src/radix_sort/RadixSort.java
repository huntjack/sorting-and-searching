package radix_sort;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class RadixSort {
    public static BaseTenThousandInt[] sortWithRadixSort(BaseTenThousandInt[] array) {
        if(array == null) {
            throw new IllegalArgumentException();
        }
        int maxDigits = array[0]
                .getMaxDigits();
        for(int i = 0; i < maxDigits; i++) {
            array = countingSort(array, i);
        }
        return array;
    }
    private static BaseTenThousandInt[] countingSort(BaseTenThousandInt[] input, int digit) {
        int[] counts = calculateCounts(input, digit);
        int[] prefixSums = calculatePrefixSums(counts);
        return rebuildArray(input, prefixSums, digit);
    }
    private static int[] calculateCounts(BaseTenThousandInt[] input, int digit) {
        int[] counts = new int[input.length];
        for(BaseTenThousandInt current : input) {
            int value = current
                    .getDigit(digit);
            counts[value]++;
        }
        return counts;
    }
    private static int[] calculatePrefixSums(int[] counts) {
        int sum = counts[0];
        for(int i = 1; i < counts.length; i++) {
            counts[i] += sum;
            sum = counts[i];
        }
        return counts;
    }
    private static BaseTenThousandInt[] rebuildArray(BaseTenThousandInt[] input, int[] prefixSums, int digit) {
        int length = input.length;
        BaseTenThousandInt[] output = new BaseTenThousandInt[length];
        for(int i = length - 1; i >= 0; i--) {
            BaseTenThousandInt current = input[i];
            int digitValue = current
                    .getDigit(digit);
            int outputIndex = --prefixSums[digitValue];
            output[outputIndex] = current;
        }
        System.arraycopy(output, 0, input, 0, length);
        return input;
    }
    public static void main(String[] args) {
        BaseTenThousandInt[] array = initializeArray();
        array = shuffle(array);
        array = sortWithRadixSort(array);
        print(array);
        System.out.println("Array is sorted: " + assertSorted(array));
    }
    private static BaseTenThousandInt[] initializeArray() {
        BaseTenThousandInt[] array = new BaseTenThousandInt[10000];
        int j = array.length - 1;
        for(int i = 0; i < array.length; i++) {
            array[i] = new BaseTenThousandInt(j--, i);
        }
        return array;
    }
    private static BaseTenThousandInt[] shuffle(BaseTenThousandInt[] array) {
        List<BaseTenThousandInt> list = Arrays.asList(array);
        Collections.shuffle(list);
        return list.toArray(array);
    }
    private static void print(BaseTenThousandInt[] array) {
        for(BaseTenThousandInt element : array) {
            StringBuilder stringBuilder = new StringBuilder();
            int digitZero = element
                    .getDigit(0);
            int digitOne = element.getDigit(1);
            stringBuilder.append("(Base-10,000) ");
            stringBuilder.append(digitOne);
            stringBuilder.append(",");
            stringBuilder.append(digitZero);
            stringBuilder.append(" = (Base-10) ");
            int baseTen = element.calculateBaseTenValue();
            stringBuilder.append(baseTen);
            System.out.println(stringBuilder);
        }
    }
    private static boolean assertSorted(BaseTenThousandInt[] array) {
        if(array.length <= 1) {
            return true;
        }
        int currentIndex = 1;
        int previousIndex = 0;
        while(currentIndex < array.length) {
            BaseTenThousandInt current = array[currentIndex++];
            BaseTenThousandInt previous = array[previousIndex++];
            if(current.isLessThan(previous)) {
                return false;
            }
        }
        return true;
    }
}
