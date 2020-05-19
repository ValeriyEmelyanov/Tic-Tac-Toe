package tictactoe;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        final Scanner scanner = new Scanner(System.in);
        Field field = Field.getInstance();

        int counter = 0;
        while (field.getState() == State.GAME_NOT_FINISHED) {
            field.print();
            while (true) {
                System.out.print("Enter the coordinates: ");
                String input = scanner.nextLine();
                String[] words = input.split("\\s");

                int x;
                int y;
                try {
                    x = Integer.parseInt(words[0]);
                    y = Integer.parseInt(words[1]);
                } catch (Exception e) {
                    System.out.println("You should enter numbers!");
                    continue;
                }

                if (field.checkCoordinates(x, y)) {
                    field.move(x, y, ++counter % 2 == 1 ? Field.CROSS : Field.ZERO);
                    break;
                }
            }
        }
        field.print();
        System.out.println(field.getState().getDescription());
    }
}
