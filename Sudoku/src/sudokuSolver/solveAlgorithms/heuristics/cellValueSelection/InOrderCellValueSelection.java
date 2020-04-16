package sudokuSolver.solveAlgorithms.heuristics.cellValueSelection;

import sudokuInfo.Cell;
import sudokuInfo.Sudoku;

import java.util.Collections;

public class InOrderCellValueSelection implements CellValueSelection {
    @Override
    public int selectCellPotentialValue(Cell cell, Sudoku sudoku) {
        if (cell.getDomain().size() > 0){
            Collections.sort(cell.getDomain());
            return cell.getDomain().get(0);
        }
        return Cell.EMPTY_CELL_VALUE;
    }
}
