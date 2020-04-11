package sudokuSolver.solveAlgorithms.forwardCheckingAlgorithm;

import sudokuInfo.Cell;
import sudokuInfo.Sudoku;
import sudokuSolver.solveAlgorithms.SolveAlgorithm;
import sudokuSolver.solveAlgorithms.heuristics.cellPotentialValueSelection.CellPotentialValueSelection;
import sudokuSolver.solveAlgorithms.heuristics.cellSelection.CellSelection;
import sudokuSolver.solveAlgorithms.treeStructure.*;

public class ForwardCheckingAlgorithm extends SolveAlgorithm {

    public ForwardCheckingAlgorithm(CellSelection cellSelection, CellPotentialValueSelection cellValueSelection) {
        this.cellSelection = cellSelection;
        this.cellValueSelection = cellValueSelection;
    }

    public void runAlgorithm(Sudoku sudoku) {
        Tree<Sudoku> tree = new Tree<Sudoku>(new Node<Sudoku>(sudoku));
        plotTree(tree.getRoot());
    }

    private Node<Sudoku> plotTree(Node<Sudoku> currentNode){
        incrementVisitedNodes();
        prepareSudokusForCurrentNode(currentNode.getValue());
        selectCell();

        while(!selectedCell.hasEmptyDomain()){
            selectCellPotentialValue();
            setCellValueInOffspringSudoku();

            if (offspringSudoku.isSolved())
                updateSolution();

            else if (offspringSudoku.hasCorrectValues()){
                Sudoku futureSudoku = new Sudoku(offspringSudoku);
                Cell changedCell = futureSudoku.getBoard().get(selectedCellRowIdx).get(selectedCellColumnIdx);

                boolean hasNotEmptyDomains = futureSudoku.shrinkDomains(changedCell, selectedCellPotentialValue);

                if (hasNotEmptyDomains){
                    Node<Sudoku> offspringNode = new Node<Sudoku>(futureSudoku);
                    offspringNode.setParent(currentNode);
                    currentNode.addOffspring(plotTree(offspringNode));
                }
                else incrementBacktracks();
            }
            else incrementBacktracks();
        }
        return currentNode;
    }

}
