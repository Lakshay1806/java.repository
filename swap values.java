public class SwapNumbers {
    public static void main(String[] args) {
        int a = 5, b = 7;

        // 1. Without 3rd variable
        System.out.println("Before Swap (without 3rd variable): a = " + a + ", b = " + b);
        a = a + b;
        b = a - b;
        a = a - b;
        System.out.println("After Swap: a = " + a + ", b = " + b);

        // 2. Using 3rd variable
        a = 5;
        b = 7;
        System.out.println("\nBefore Swap (using 3rd variable): a = " + a + ", b = " + b);
        int temp = a;
        a = b;
        b = temp;
        System.out.println("After Swap: a = " + a + ", b = " + b);
    }
}
