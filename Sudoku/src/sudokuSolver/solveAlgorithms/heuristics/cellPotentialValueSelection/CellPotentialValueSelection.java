package sudokuSolver.solveAlgorithms.heuristics.cellPotentialValueSelection;

import sudokuInfo.Cell;

public interface CellPotentialValueSelection {
    int selectCellPotentialValue(Cell cell);
}
