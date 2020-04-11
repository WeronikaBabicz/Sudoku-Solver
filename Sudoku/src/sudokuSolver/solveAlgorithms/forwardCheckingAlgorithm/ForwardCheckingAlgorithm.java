package sudokuSolver.solveAlgorithms.forwardCheckingAlgorithm;

import sudokuInfo.Cell;
import sudokuInfo.Sudoku;
import sudokuSolver.solveAlgorithms.SolveAlgorithm;
import sudokuSolver.solveAlgorithms.heuristics.cellPotentialValueSelection.CellPotentialValueSelection;
import sudokuSolver.solveAlgorithms.heuristics.cellSelection.CellSelection;
import sudokuSolver.solveAlgorithms.treeStructure.*;

import java.util.ArrayList;

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

        Sudoku currentSudoku = new Sudoku(currentNode.getValue());
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

            else if (offspringSudoku.hasCorrectValues()){
                Sudoku futureSudoku = new Sudoku(offspringSudoku);

                Cell changedCell = futureSudoku.getBoard().get(selectedCellRowIdx).get(selectedCellColumnIdx);
                boolean hasNotEmptyDomains = futureSudoku.shrinkDomains(changedCell, cellPotentialValue);

                if (hasNotEmptyDomains){
                    Node<Sudoku> offspringNode = new Node<Sudoku>(futureSudoku);
                    offspringNode.setParent(currentNode);
                    currentNode.addOffspring(plotTree(offspringNode));
                }

                else incrementBacktracks();
            }

            else
                incrementBacktracks();
        }

        return currentNode;
    }

}
