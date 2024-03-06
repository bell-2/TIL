import java.util.Arrays;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner  = new Scanner(System.in);
        int count = scanner.nextInt();
        int scores[] = new int[count];
        for (int i = 0; i < count; i++) {
            scores[i] = scanner.nextInt();
        }
        scanner.close();

        long max = Arrays.stream(scores).max().getAsInt();
        long sum = Arrays.stream(scores).sum();
        System.out.println(sum*100.0/max/count);
    }
}
