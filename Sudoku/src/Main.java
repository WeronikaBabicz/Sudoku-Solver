import dataParser.Parser;
import sudokuInfo.Sudoku;
import sudokuSolver.SudokuSolver;
import sudokuSolver.solveAlgorithms.backtrackingAlgorithm.BacktrackingAlgorithm;
import sudokuSolver.solveAlgorithms.forwardCheckingAlgorithm.ForwardCheckingAlgorithm;
import sudokuSolver.solveAlgorithms.heuristics.cellSelection.InOrderCellSelection;
import sudokuSolver.solveAlgorithms.heuristics.cellSelection.MostRestrictiveCellSelection;
import sudokuSolver.solveAlgorithms.heuristics.cellValueSelection.InOrderCellValueSelection;
import sudokuSolver.solveAlgorithms.heuristics.cellValueSelection.LeastOccurencedInRowCellValueSelection;
import sudokuSolver.solveAlgorithms.heuristics.cellSelection.CellSelection;
import sudokuSolver.solveAlgorithms.heuristics.cellValueSelection.CellValueSelection;
import sudokuSolver.solveAlgorithms.SolveAlgorithm;
import sudokuSolver.solveAlgorithms.heuristics.cellSelection.MostRestrictiveInGridCellSelection;
import sudokuSolver.solveAlgorithms.heuristics.cellValueSelection.RandomCellValueSelection;

import java.util.ArrayList;

public class Main {
    private static final int SUDOKU_NUMBER = 44;
    public static void main(String [] args){
        Parser parser = new Parser();
        Sudoku sudoku = parser.parse("Sudoku.csv", SUDOKU_NUMBER);

        CellSelection cellSelection = new InOrderCellSelection();
        CellValueSelection cellValueSelection = new InOrderCellValueSelection();


        SolveAlgorithm solveAlgorithm = new ForwardCheckingAlgorithm(cellSelection, cellValueSelection);
        SudokuSolver sudokuSolver = new SudokuSolver(sudoku, solveAlgorithm);

        ArrayList<Sudoku> solution = sudokuSolver.runSolver();
        System.out.println("SOLUTION: " + solution.size());
        for (Sudoku s : solution){
            System.out.println("Rozwiązanie sudoku " + SUDOKU_NUMBER + " o trudności " + s.getDifficulty());
            System.out.println(s);
        }
        System.out.println("Time of finding first solution: " + sudokuSolver.getFindFirstSolutionTime());
        System.out.println("Time of finding all solutions: " + sudokuSolver.getFindSolutionsTime());
        System.out.println("Number of backtracks until finding first solution: " + sudokuSolver.getNumberOfBacktracksFirstSolution());
        System.out.println("Number of backtracks until finding all solutions: " + sudokuSolver.getNumberOfBacktracks());
        System.out.println("Number of visited nodes until finding first solution: " + sudokuSolver.getNumberOfVisitedNodesFirstSolution());
        System.out.println("Number of visited nodes until finding all solutions: " + sudokuSolver.getNumberOfVisitedNodes());
        System.out.println("Solutions: " + solution.size());
    }
}
