package sudokuSolver.solveAlgorithms.backtrackingAlgorithm;

import sudokuInfo.Board;
import sudokuSolver.solveAlgorithms.SolveAlgorithm;
import sudokuSolver.solveAlgorithms.backtrackingAlgorithm.cellSelection.CellSelection;
import sudokuSolver.solveAlgorithms.backtrackingAlgorithm.cellPotentialValueSelection.CellPotentialValueSelection;
import sudokuSolver.solveAlgorithms.backtrackingAlgorithm.treeStructure.Tree;

public class BacktrackingAlgorithm implements SolveAlgorithm {
    private CellSelection cellSelection;
    private CellPotentialValueSelection cellValueSelection;
    private Tree<Board> tree;
    private Board solution;

    public BacktrackingAlgorithm(CellSelection cellSelection, CellPotentialValueSelection cellValueSelection) {
        this.cellSelection = cellSelection;
        this.cellValueSelection = cellValueSelection;
    }

    @Override
    public Board runAlgorithm(Board board){
        solution = new Board(board);


        return solution;
    }


}
