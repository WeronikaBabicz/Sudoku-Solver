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
        Sudoku currentSudoku = currentNode.getValue(); // not a deep copy
        Sudoku offspringSudoku = new Sudoku(currentSudoku); //deep copy

        Cell cell = cellSelection.selectCell(currentSudoku); //cell z currentNode'a

        if (cell != null){
            while(cell.getDomain().size() > 0){
                int cellPotentialValue = cellValueSelection.selectCellPotentialValue(cell);
                cell.shrinkDomain(cellPotentialValue); // cell's domain from currentSudoku and currentNode will change
                offspringSudoku.setCellValue(cell, cellPotentialValue);

                Node<Sudoku> offspringNode = new Node<>(offspringSudoku);
                offspringNode.setParent(currentNode);

                if (offspringSudoku.isSolved()){
                    solution.add(offspringSudoku);
                    return offspringNode;
                }

                else if (offspringSudoku.hasCorrectValues())
                    currentNode.addOffspring(createTree(offspringNode));

            }
        }
        return currentNode;
    }



}
