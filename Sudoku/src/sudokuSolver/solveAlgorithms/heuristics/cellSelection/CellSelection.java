package sudokuSolver.solveAlgorithms.heuristics.cellSelection;

import sudokuInfo.Sudoku;
import sudokuInfo.Cell;

public interface CellSelection {
    Cell selectCell(Sudoku sudoku);
}
