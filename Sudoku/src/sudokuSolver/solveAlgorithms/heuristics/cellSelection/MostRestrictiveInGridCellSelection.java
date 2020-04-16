package sudokuSolver.solveAlgorithms.heuristics.cellSelection;

import sudokuInfo.Cell;
import sudokuInfo.Sudoku;

public class MostRestrictiveInGridCellSelection implements CellSelection {
    @Override
    public Cell selectCell(Sudoku sudoku) {
        Cell gridStartCell = selectMostRestrictiveGrid(sudoku);
        int gridStartRowIdx = sudoku.getRowIndexOfCell(gridStartCell);
        int gridStartColumnIdx = sudoku.getColumnIndexOfCell(gridStartCell);

        Cell selectedCell = null;
        int minDomainSize = 10;

        for (int i = gridStartRowIdx; i < gridStartRowIdx + 3; i++){
            for (int j = gridStartColumnIdx; j < gridStartColumnIdx + 3; j++){
                if (sudoku.getBoard().get(i).get(j).isEmptyCell() &&
                    sudoku.getBoard().get(i).get(j).getDomain().size() < minDomainSize) {

                    minDomainSize = sudoku.getBoard().get(i).get(j).getDomain().size();
                    selectedCell = sudoku.getBoard().get(i).get(j);
                }
            }
        }
        return selectedCell;
    }

    private Cell selectMostRestrictiveGrid(Sudoku sudoku){
        Cell mostRestrictiveGrid = null;
        int minEmptyCells = 10;
        for (int i = 0; i < sudoku.getBoard().size(); i = i + 3){
            for (int j = 0; j < sudoku.getBoard().get(i).size(); j = j + 3){
                int emptyCells = countEmptyCellsInGrid(sudoku, i, j);
                if (minEmptyCells > emptyCells && emptyCells != 0)
                    mostRestrictiveGrid = sudoku.getBoard().get(i).get(j);
            }
        }
        return mostRestrictiveGrid;
    }

    private int countEmptyCellsInGrid(Sudoku sudoku, int gridStartRowIdx, int gridStartColumnIdx){
        return sudoku.countEmptyCellsInGrid(gridStartRowIdx, gridStartColumnIdx);
    }
}
