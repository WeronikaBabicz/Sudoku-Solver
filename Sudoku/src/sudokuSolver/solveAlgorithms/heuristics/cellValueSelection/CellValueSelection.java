package sudokuSolver.solveAlgorithms.heuristics.cellValueSelection;

import sudokuInfo.Cell;
import sudokuInfo.Sudoku;

public interface CellValueSelection {
    int selectCellPotentialValue(Cell cell, Sudoku sudoku);
}
