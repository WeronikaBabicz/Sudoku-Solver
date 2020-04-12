package sudokuSolver.solveAlgorithms.heuristics.cellSelection;

import sudokuInfo.Sudoku;
import sudokuInfo.Cell;

import java.util.ArrayList;

public class InOrderCellSelection implements CellSelection {
    @Override
    public Cell selectCell(Sudoku sudoku) {
        for (ArrayList<Cell> row: sudoku.getBoard()){
            for (Cell cell: row){
                if (cell.isEmptyCell())
                    return cell;
            }
        }
        return null;
    }
}
