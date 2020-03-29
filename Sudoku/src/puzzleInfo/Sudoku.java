package puzzleInfo;

public class Sudoku {
    private double difficulty;
    private Board board;

    public Sudoku(double difficulty, Board board) {
        this.difficulty = difficulty;
        this.board = board;
    }

    public Board getBoard() {
        return board;
    }

    public double getDifficulty() {
        return difficulty;
    }
}
