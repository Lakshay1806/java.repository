import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int a = sc.nextInt();
        int b = sc.nextInt();
        int c = sc.nextInt();

        if (a != b && b != c && a != c) {
            if (a < b && b < c)
                System.out.println("Strictly Increasing");
            else if (a > b && b > c)
                System.out.println("Strictly Decreasing");
            else if (b > a && b > c)
                System.out.println("Middle number is largest");
            else
                System.out.println("No specific pattern");
        } else {
            System.out.println("Numbers are not all different");
        }
    }
}
