import dataParser.Parser;
import sudokuInfo.Board;
import sudokuInfo.Sudoku;
import sudokuSolver.SudokuSolver;
import sudokuSolver.solveAlgorithms.backtrackingAlgorithm.cellSelection.CellSelection;
import sudokuSolver.solveAlgorithms.backtrackingAlgorithm.cellSelection.InOrderCellSelection;
import sudokuSolver.solveAlgorithms.backtrackingAlgorithm.cellPotentialValueSelection.CellPotentialValueSelection;
import sudokuSolver.solveAlgorithms.backtrackingAlgorithm.cellPotentialValueSelection.InOrderCellPotentialValueSelection;
import sudokuSolver.solveAlgorithms.backtrackingAlgorithm.BacktrackingAlgorithm;
import sudokuSolver.solveAlgorithms.SolveAlgorithm;

public class Main {
    private static final int SUDOKU_NUMBER = 43;
    public static void main(String [] args){
        Parser parser = new Parser();
        Sudoku sudoku = parser.parse("Sudoku.csv", SUDOKU_NUMBER);

        CellSelection cellSelection = new InOrderCellSelection();
        CellPotentialValueSelection cellValueSelection = new InOrderCellPotentialValueSelection();


        SolveAlgorithm solveAlgorithm = new BacktrackingAlgorithm(cellSelection, cellValueSelection);
        SudokuSolver sudokuSolver = new SudokuSolver(sudoku, solveAlgorithm);

        Board solution = sudokuSolver.runSolver();
        System.out.println("Rozwiązanie sudoku " + SUDOKU_NUMBER + " o trudności " + sudoku.getDifficulty());
        System.out.println(solution);

    }
}
