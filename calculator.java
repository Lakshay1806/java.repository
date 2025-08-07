import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n1 = sc.nextInt();
        int n2 = sc.nextInt();

        System.out.println(n1 + n2);  // Sum
        System.out.println(n1 - n2);  // Difference
        System.out.println(n1 * n2);  // Product
        System.out.println(n1 / n2);  // Quotient

        sc.close(); // Always a good practice to close Scanner
    }
}
