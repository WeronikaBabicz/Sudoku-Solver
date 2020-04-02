package sudokuSolver.solveAlgorithms;

import sudokuInfo.Sudoku;

import java.util.ArrayList;

public interface SolveAlgorithm {
    ArrayList<Sudoku> runAlgorithm(Sudoku sudoku);
}
