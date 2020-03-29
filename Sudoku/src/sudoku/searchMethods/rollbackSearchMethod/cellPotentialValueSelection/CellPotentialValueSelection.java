package sudoku.searchMethods.rollbackSearchMethod.cellPotentialValueSelection;

import puzzleInfo.Cell;

public interface CellPotentialValueSelection {
    int selectCellValue(Cell cell);
}
