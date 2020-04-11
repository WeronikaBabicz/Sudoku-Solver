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

        Sudoku currentSudoku = new Sudoku(sudoku);
        Sudoku offspringSudoku = new Sudoku(currentSudoku);

        Cell cell = cellSelection.selectCell(currentSudoku);

        int selectedCellRowIdx = currentSudoku.getRowIndexOfCell(cell);
        int selectedCellColumnIdx = currentSudoku.getColumnIndexOfCell(cell);

        while(cell.getDomain().size() > 0){
            int cellPotentialValue = cellValueSelection.selectCellPotentialValue(cell);

            cell.shrinkDomain(cellPotentialValue);
            offspringSudoku.setCellValue(selectedCellRowIdx, selectedCellColumnIdx, cellPotentialValue);

            if (offspringSudoku.isSolved()){
                solution.add(offspringSudoku);

                if (isFirstSolution())
                    setSurveyVariablesAfterFirstSolution();
            }

            else if (offspringSudoku.hasCorrectValues())
                findOffspring(offspringSudoku);

            else
                incrementBacktracks();
        }
    }
}
