package radix_sort;

public class BaseTenThousandInt {
    private static final int UPPER_LIMIT = 10000;
    private int[] digits;
    public BaseTenThousandInt(int digitZero, int digitOne) {
        if(digitZero >= UPPER_LIMIT || digitOne >= UPPER_LIMIT) {
            throw new IllegalArgumentException();
        }
        digits = new int[2];
        digits[0] = digitZero;
        digits[1] = digitOne;
    }
    public int calculateBaseTenValue() {
        int mostSignificantDigitValue = digits[1] * 10000;
        int leastSignificantDigitValue = digits[0];
        return mostSignificantDigitValue + leastSignificantDigitValue;
    }
    public boolean isLessThan(BaseTenThousandInt other) {
        return this.calculateBaseTenValue() < other.calculateBaseTenValue();
    }
    public int getDigit(int digit) {
        return digits[digit];
    }
    public int getMaxDigits() {
        return digits.length;
    }
}
