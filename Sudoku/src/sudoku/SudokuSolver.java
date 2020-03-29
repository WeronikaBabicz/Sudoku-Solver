package sudoku;

import puzzleInfo.Board;
import puzzleInfo.Sudoku;
import sudoku.searchMethods.SolveAlgorithm;

public class SudokuSolver {
    private Sudoku sudoku;
    private SolveAlgorithm method;

    public SudokuSolver(Sudoku sudoku, SolveAlgorithm method) {
        this.sudoku = sudoku;
        this.method = method;
    }

    public Board runSolver(){
        return method.runAlgorithm(sudoku.getBoard());
    }
}
