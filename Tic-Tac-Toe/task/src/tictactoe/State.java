package tictactoe;

public enum State {
    GAME_NOT_FINISHED("Game not finished"),
    DRAW("Draw"),
    X_WINS("X wins"),
    O_WINS("O wins"),
    IMPOSSIBLE("Impossible");

    private String description;

    State(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}
