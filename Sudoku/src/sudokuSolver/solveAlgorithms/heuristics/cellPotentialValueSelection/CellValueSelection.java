package sudokuSolver.solveAlgorithms.heuristics.cellPotentialValueSelection;

import sudokuInfo.Cell;
import sudokuInfo.Sudoku;

public interface CellValueSelection {
    int selectCellPotentialValue(Cell cell, Sudoku sudoku);
}
