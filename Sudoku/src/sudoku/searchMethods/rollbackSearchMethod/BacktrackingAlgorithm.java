package sudoku.searchMethods.rollbackSearchMethod;

import puzzleInfo.Board;
import sudoku.searchMethods.SolveAlgorithm;
import sudoku.searchMethods.rollbackSearchMethod.cellSelection.CellSelection;
import sudoku.searchMethods.rollbackSearchMethod.cellPotentialValueSelection.CellPotentialValueSelection;
import sudoku.searchMethods.rollbackSearchMethod.treeStructure.Tree;

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
