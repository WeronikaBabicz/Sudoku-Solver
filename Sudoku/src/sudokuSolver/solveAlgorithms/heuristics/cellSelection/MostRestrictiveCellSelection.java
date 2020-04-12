package sudokuSolver.solveAlgorithms.heuristics.cellSelection;

import sudokuInfo.Cell;
import sudokuInfo.Sudoku;

import java.util.ArrayList;

public class MostRestrictiveCellSelection implements CellSelection {
    @Override
    public Cell selectCell(Sudoku sudoku) {
        Cell selectedCell = null;
        int minDomainSize = 10;
        for (ArrayList<Cell> row: sudoku.getBoard()){
            for (Cell cell: row){
                if (cell.isEmptyCell() && cell.hasNotEmptyDomain() && cell.getDomain().size() < minDomainSize) {
                    minDomainSize = cell.getDomain().size();
                    selectedCell = cell;
                }
            }
        }
        return selectedCell;
    }
}
