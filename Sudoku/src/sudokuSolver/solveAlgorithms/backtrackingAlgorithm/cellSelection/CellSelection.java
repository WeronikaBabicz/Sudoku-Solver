package sudokuSolver.solveAlgorithms.backtrackingAlgorithm.cellSelection;

import sudokuInfo.Board;
import sudokuInfo.Cell;

public interface CellSelection {
    Cell selectCell(Board board);
}
