package tictactoe;

import java.util.Arrays;

public class Field {
    public static final char CROSS = 'X';
    public static final char ZERO = 'O';
    public static final char EMPTY = ' ';
    private static final int SIZE = 3;

    private static final Field instance = new Field();

    public static Field getInstance() {
        return instance;
    }

    private char[][] field = new char[SIZE][SIZE];

    private Field() {
        char[] chars = new char[SIZE * SIZE];
        Arrays.fill(chars, EMPTY);
        fillField(chars);
    }

    public void fillField(char[] symbols) {
        int idx = 0;
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                field[i][j] = symbols[idx] == '_' ? EMPTY : symbols[idx];
                idx++;
            }
        }
    }

    public void print() {
        String horBorder = "---------";
        String verBorder = "| ";

        System.out.println(horBorder);
        for (char[] row : field) {
            System.out.print(verBorder);
            for (char c : row) {
                System.out.print(c + " ");
            }
            System.out.println(verBorder);
        }
        System.out.println(horBorder);
    }

    public State getState() {
        boolean xWins = checkWin(CROSS);
        boolean oWins = checkWin(ZERO);
        if (xWins && oWins || checkImpossible()) {
            return State.IMPOSSIBLE;
        }
        if (xWins) {
            return State.X_WINS;
        }
        if (oWins) {
            return State.O_WINS;
        }
        if (!checkHasEmpty()) {
            return State.DRAW;
        }
        return State.GAME_NOT_FINISHED;
    }

    private boolean checkHasEmpty() {
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                if (field[i][j] == EMPTY) {
                    return true;
                }
            }
        }
        return false;
    }

    private boolean checkImpossible() {
        return Math.abs(countSymbol(CROSS) - countSymbol(ZERO)) > 1;
    }

    private int countSymbol(char c) {
        int count = 0;
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                if (field[i][j] == c) {
                    count++;
                }
            }
        }
        return count;
    }

    private boolean checkWin(char c) {
        return checkRows(c)
                || checkColumns(c)
                || checkDiagonalFromLeftTopToRightBottom(c)
                || checkDiagonalFromRightTopToLeftBottom(c);
    }

    private boolean checkRows(char c) {
        for (int i = 0; i < SIZE; i++) {
            int counter = 0;
            for (int j = 0; j < SIZE; j++) {
                if (field[i][j] != c) {
                    break;
                }
                counter++;
            }
            if (counter == SIZE) {
                return true;
            }
        }
        return false;
    }

    private boolean checkColumns(char c) {
        for (int i = 0; i < SIZE; i++) {
            int counter = 0;
            for (int j = 0; j < SIZE; j++) {
                if (field[j][i] != c) {
                    break;
                }
                counter++;
            }
            if (counter == SIZE) {
                return true;
            }
        }
        return false;
    }

    private boolean checkDiagonalFromLeftTopToRightBottom(char c) {
        int counter = 0;
        for (int i = 0; i < SIZE; i++) {
            if (field[i][i] != c) {
                break;
            }
            counter++;
        }
        if (counter == SIZE) {
            return true;
        }
        return false;
    }

    private boolean checkDiagonalFromRightTopToLeftBottom(char c) {
        int counter = 0;
        for (int i = 0; i < SIZE; i++) {
            if (field[i][SIZE - 1 - i] != c) {
                break;
            }
            counter++;
        }
        if (counter == SIZE) {
            return true;
        }
        return false;
    }

    public boolean checkCoordinates(int x, int y) {
        if (x < 1 || x > SIZE
                || y < 1 || y > SIZE) {
            System.out.println("Coordinates should be from 1 to 3!");
            return false;
        }
        if (field[convertY(y)][convertX(x)] != EMPTY) {
            System.out.println("This cell is occupied! Choose another one!");
            return false;
        }
        return true;
    }

    private int convertX(int x) {
        return x - 1;
    }

    private int convertY(int y) {
        return SIZE - y;
    }

    public void move(int x, int y, char c) {
        field[convertY(y)][convertX(x)] = c;
    }
}
