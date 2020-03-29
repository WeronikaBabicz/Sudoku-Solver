import dataParser.Parser;
import puzzleInfo.Board;
import puzzleInfo.Sudoku;
import sudoku.SudokuSolver;
import sudoku.searchMethods.rollbackSearchMethod.cellSelection.CellSelection;
import sudoku.searchMethods.rollbackSearchMethod.cellSelection.InOrderCellSelection;
import sudoku.searchMethods.rollbackSearchMethod.cellPotentialValueSelection.CellPotentialValueSelection;
import sudoku.searchMethods.rollbackSearchMethod.cellPotentialValueSelection.InOrderCellPotentialValueSelection;
import sudoku.searchMethods.rollbackSearchMethod.BacktrackingAlgorithm;
import sudoku.searchMethods.SolveAlgorithm;

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
