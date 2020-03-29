package sudoku.searchMethods.rollbackSearchMethod.cellSelection;

import puzzleInfo.Board;
import puzzleInfo.Cell;

import java.util.ArrayList;

public class InOrderCellSelection implements CellSelection {
    @Override
    public Cell selectCell(Board board) {
        for (ArrayList<Cell> row: board.getBoard()){
            for (Cell cell: row){
                if (cell.getValue() == Cell.EMPTY_CELL_VALUE)
                    return cell;
            }
        }
        return null;
    }
}
