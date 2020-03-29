package sudokuSolver.solveAlgorithms.backtrackingAlgorithm.cellSelection;

import sudokuInfo.Board;
import sudokuInfo.Cell;

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
