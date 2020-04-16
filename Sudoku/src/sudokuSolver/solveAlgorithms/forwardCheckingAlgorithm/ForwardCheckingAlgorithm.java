package sudokuSolver.solveAlgorithms.forwardCheckingAlgorithm;

import sudokuInfo.Cell;
import sudokuInfo.Sudoku;
import sudokuSolver.solveAlgorithms.SolveAlgorithm;
import sudokuSolver.solveAlgorithms.heuristics.cellValueSelection.CellValueSelection;
import sudokuSolver.solveAlgorithms.heuristics.cellSelection.CellSelection;
import sudokuSolver.solveAlgorithms.treeStructure.*;

public class ForwardCheckingAlgorithm extends SolveAlgorithm {

    public ForwardCheckingAlgorithm(CellSelection cellSelection, CellValueSelection cellValueSelection) {
        this.cellSelection = cellSelection;
        this.cellValueSelection = cellValueSelection;
    }

    public void runAlgorithm(Sudoku sudoku) {
        Tree<Sudoku> tree = new Tree<Sudoku>(new Node<Sudoku>(sudoku));
        plotTree(tree.getRoot());
    }

    private Node<Sudoku> plotTree(Node<Sudoku> currentNode){
        incrementVisitedNodes();

        Sudoku currentSudoku = new Sudoku(currentNode.getValue());
        Sudoku offspringSudoku = new Sudoku(currentSudoku);

        Cell selectedCell = cellSelection.selectCell(currentSudoku);

        int selectedCellRowIdx = currentSudoku.getRowIndexOfCell(selectedCell);
        int selectedCellColumnIdx = currentSudoku.getColumnIndexOfCell(selectedCell);


        while(selectedCell.hasNotEmptyDomain()){
            selectCellPotentialValue(selectedCell, currentSudoku);
            setCellValueInOffspringSudoku(offspringSudoku, selectedCell, selectedCellRowIdx, selectedCellColumnIdx);

            if (offspringSudoku.isSolved())
                updateSolution(offspringSudoku);

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
