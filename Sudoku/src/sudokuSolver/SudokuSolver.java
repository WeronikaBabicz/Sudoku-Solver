package sudokuSolver;

import sudokuInfo.Sudoku;
import sudokuSolver.solveAlgorithms.SolveAlgorithm;

import java.util.ArrayList;

public class SudokuSolver {
    private Sudoku sudoku;
    private SolveAlgorithm method;

    public SudokuSolver(Sudoku sudoku, SolveAlgorithm method) {
        this.sudoku = sudoku;
        this.method = method;
    }

    public ArrayList<Sudoku> runSolver(){
        method.run(sudoku);
        return method.getSolution();
    }

    public long getFindSolutionsTime(){
        return method.getSurvey_findSolutionsTime();
    }

    public long getFindFirstSolutionTime(){
        return method.getSurvey_findFirstSolutionTime();
    }

    public int getNumberOfBacktracks(){
        return method.getSurvey_backtracks();
    }

    public int getNumberOfBacktracksFirstSolution(){
        return method.getSurvey_backtracksFirstSolution();
    }

    public int getNumberOfVisitedNodes(){
        return method.getSurvey_visitedNodes();
    }

    public int getNumberOfVisitedNodesFirstSolution(){
        return method.getSurvey_visitedNodesFirstSolution();
    }
}
