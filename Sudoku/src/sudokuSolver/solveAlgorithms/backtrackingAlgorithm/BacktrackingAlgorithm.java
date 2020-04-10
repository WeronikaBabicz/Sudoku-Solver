package sudokuSolver.solveAlgorithms.backtrackingAlgorithm;

import sudokuInfo.Sudoku;
import sudokuInfo.Cell;
import sudokuSolver.solveAlgorithms.SolveAlgorithm;
import sudokuSolver.solveAlgorithms.heuristics.cellSelection.CellSelection;
import sudokuSolver.solveAlgorithms.heuristics.cellPotentialValueSelection.CellPotentialValueSelection;
import sudokuSolver.solveAlgorithms.treeStructure.Node;
import sudokuSolver.solveAlgorithms.treeStructure.Tree;

import java.util.ArrayList;

public class BacktrackingAlgorithm implements SolveAlgorithm {
    private CellSelection cellSelection;
    private CellPotentialValueSelection cellValueSelection;
    private Tree<Sudoku> tree;
    private ArrayList<Sudoku> solution  = new ArrayList<>();

    public BacktrackingAlgorithm(CellSelection cellSelection, CellPotentialValueSelection cellValueSelection) {
        this.cellSelection = cellSelection;
        this.cellValueSelection = cellValueSelection;
    }

    @Override
    public ArrayList<Sudoku> runAlgorithm(Sudoku sudoku){
        tree = new Tree<Sudoku>(new Node<Sudoku>(sudoku));
        findOffspring(sudoku);
        return solution;
    }

    private void findOffspring(Sudoku sudoku)  {
        Sudoku currentSudoku = new Sudoku(sudoku);
        Sudoku offspringSudoku = new Sudoku(currentSudoku);

        Cell cell = cellSelection.selectCell(currentSudoku);

        int selectedCellRowIdx = currentSudoku.getRowIndexOfCell(cell);
        int selectedCellColumnIdx = currentSudoku.getColumnIndexOfCell(cell);

        while(cell.getDomain().size() > 0){
            int cellPotentialValue = cellValueSelection.selectCellPotentialValue(cell);

            cell.shrinkDomain(cellPotentialValue);
            offspringSudoku.setCellValue(selectedCellRowIdx, selectedCellColumnIdx, cellPotentialValue);

            if (offspringSudoku.isSolved())
                solution.add(offspringSudoku);

            else if (offspringSudoku.hasCorrectValues())
                findOffspring(offspringSudoku);
        }
    }



}
