package sudokuSolver.solveAlgorithms.backtrackingAlgorithm;

import sudokuInfo.Sudoku;
import sudokuInfo.Cell;
import sudokuSolver.solveAlgorithms.SolveAlgorithm;
import sudokuSolver.solveAlgorithms.backtrackingAlgorithm.cellSelection.CellSelection;
import sudokuSolver.solveAlgorithms.backtrackingAlgorithm.cellPotentialValueSelection.CellPotentialValueSelection;
import sudokuSolver.solveAlgorithms.backtrackingAlgorithm.treeStructure.Node;
import sudokuSolver.solveAlgorithms.backtrackingAlgorithm.treeStructure.Tree;

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
        createTree(tree.getRoot());
        return solution;
    }

    private Node<Sudoku> createTree(Node<Sudoku> currentNode)  {
        Sudoku currentSudoku = new Sudoku(currentNode.getValue());
        Sudoku offspringSudoku = new Sudoku(currentSudoku);

        Cell cell = cellSelection.selectCell(currentSudoku);

        int selectedCellRowIdx = currentSudoku.getRowIndexOfCell(cell);
        int selectedCellColumnIdx = currentSudoku.getColumnIndexOfCell(cell);

        while(cell.getDomain().size() > 0){
            int cellPotentialValue = cellValueSelection.selectCellPotentialValue(cell);

            cell.shrinkDomain(cellPotentialValue);
            offspringSudoku.setCellValue(selectedCellRowIdx, selectedCellColumnIdx, cellPotentialValue);
            Node<Sudoku> offspringNode = new Node<>(offspringSudoku);
            offspringNode.setParent(currentNode);

            if (offspringSudoku.isSolved())
                solution.add(offspringSudoku);

            else if (offspringSudoku.hasCorrectValues())
                currentNode.addOffspring(createTree(offspringNode));
        }
        return currentNode;
    }



}
