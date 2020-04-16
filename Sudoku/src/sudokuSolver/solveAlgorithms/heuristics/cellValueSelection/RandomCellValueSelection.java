package sudokuSolver.solveAlgorithms.heuristics.cellValueSelection;

import sudokuInfo.Cell;
import sudokuInfo.Sudoku;

public class RandomCellValueSelection implements CellValueSelection {
    @Override
    public int selectCellPotentialValue(Cell cell, Sudoku sudoku) {
        if (cell.getDomain().size() > 0){
            return cell.getDomain().get((int)(Math.random() * cell.getDomain().size()));
        }
        return Cell.EMPTY_CELL_VALUE;
    }
}
