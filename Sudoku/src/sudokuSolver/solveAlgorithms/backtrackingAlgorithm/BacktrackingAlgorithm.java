package sudokuSolver.solveAlgorithms.backtrackingAlgorithm;

import sudokuInfo.Sudoku;
import sudokuInfo.Cell;
import sudokuSolver.solveAlgorithms.SolveAlgorithm;
import sudokuSolver.solveAlgorithms.heuristics.cellSelection.CellSelection;
import sudokuSolver.solveAlgorithms.heuristics.cellPotentialValueSelection.CellPotentialValueSelection;


public class BacktrackingAlgorithm extends SolveAlgorithm {

    public BacktrackingAlgorithm(CellSelection cellSelection, CellPotentialValueSelection cellValueSelection) {
        this.cellSelection = cellSelection;
        this.cellValueSelection = cellValueSelection;
    }

    public void runAlgorithm(Sudoku sudoku){
        findOffspring(sudoku);
    }

    private void findOffspring(Sudoku sudoku){
        incrementVisitedNodes();
        prepareSudokusForCurrentNode(sudoku);
        selectCell();

        while(!selectedCell.hasEmptyDomain()){
            selectCellPotentialValue();
            setCellValueInOffspringSudoku();

            if (offspringSudoku.isSolved())
                updateSolution();

            else if (offspringSudoku.hasCorrectValues())
                findOffspring(offspringSudoku);

            else
                incrementBacktracks();
        }
    }
}
