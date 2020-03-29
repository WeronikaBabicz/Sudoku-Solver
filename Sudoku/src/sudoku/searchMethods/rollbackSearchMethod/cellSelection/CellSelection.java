package sudoku.searchMethods.rollbackSearchMethod.cellSelection;

import puzzleInfo.Board;
import puzzleInfo.Cell;

public interface CellSelection {
    Cell selectCell(Board board);
}
