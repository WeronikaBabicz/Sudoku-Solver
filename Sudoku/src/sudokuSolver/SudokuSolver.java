package sudokuSolver;

import sudokuInfo.Sudoku;
import sudokuSolver.solveAlgorithms.SolveAlgorithm;

import java.util.ArrayList;

public class SudokuSolver {
    private Sudoku sudoku;
    private SolveAlgorithm method;

    public SudokuSolver(Sudoku sudoku, SolveAlgorithm method) {
        this.sudoku = sudoku;
        this.method = method;
    }

    public ArrayList<Sudoku> runSolver(){
        return method.runAlgorithm(sudoku);
    }
}
