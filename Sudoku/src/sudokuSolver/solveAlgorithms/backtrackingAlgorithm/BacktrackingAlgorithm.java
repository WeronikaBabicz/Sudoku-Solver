package sudokuSolver.solveAlgorithms.backtrackingAlgorithm;

import sudokuInfo.Sudoku;
import sudokuInfo.Cell;
import sudokuSolver.solveAlgorithms.SolveAlgorithm;
import sudokuSolver.solveAlgorithms.heuristics.cellSelection.CellSelection;
import sudokuSolver.solveAlgorithms.heuristics.cellPotentialValueSelection.CellValueSelection;


public class BacktrackingAlgorithm extends SolveAlgorithm {

    public BacktrackingAlgorithm(CellSelection cellSelection, CellValueSelection cellValueSelection) {
        this.cellSelection = cellSelection;
        this.cellValueSelection = cellValueSelection;
    }

    public void runAlgorithm(Sudoku sudoku){
        findOffspring(sudoku);
    }

    private void findOffspring(Sudoku sudoku){
        incrementVisitedNodes();

        Sudoku currentSudoku = new Sudoku(sudoku);
        Sudoku offspringSudoku = new Sudoku(currentSudoku);

        Cell selectedCell = cellSelection.selectCell(currentSudoku);

        int selectedCellRowIdx = currentSudoku.getRowIndexOfCell(selectedCell);
        int selectedCellColumnIdx = currentSudoku.getColumnIndexOfCell(selectedCell);


        while(selectedCell.hasNotEmptyDomain()){
            selectCellPotentialValue(selectedCell, currentSudoku);
            setCellValueInOffspringSudoku(offspringSudoku, selectedCell, selectedCellRowIdx, selectedCellColumnIdx);

            if (offspringSudoku.isSolved())
                updateSolution(offspringSudoku);

            else if (offspringSudoku.hasCorrectValues())
                findOffspring(offspringSudoku);

            else
                incrementBacktracks();
        }
    }
}
