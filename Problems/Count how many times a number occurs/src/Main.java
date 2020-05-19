import java.util.Scanner;

class Main {
    public static void main(String[] args) {
        final Scanner scanner = new Scanner(System.in);
        final int len = scanner.nextInt();
        int[] arr = new int[len];
        for (int i = 0; i < len; i++) {
            arr[i] = scanner.nextInt();
        }
        final int num = scanner.nextInt();

        int counter = 0;
        for (int i : arr) {
            if (i == num) {
                counter++;
            }
        }

        System.out.println(counter);
    }
}