package sudokuSolver.solveAlgorithms;

import sudokuInfo.Cell;
import sudokuInfo.Sudoku;
import sudokuSolver.solveAlgorithms.heuristics.cellValueSelection.CellValueSelection;
import sudokuSolver.solveAlgorithms.heuristics.cellSelection.CellSelection;

import java.util.ArrayList;

public abstract class SolveAlgorithm {
    protected CellSelection cellSelection;
    protected CellValueSelection cellValueSelection;
    private ArrayList<Sudoku> solution  = new ArrayList<>();

    protected int selectedCellPotentialValue;

    private long survey_startTime = 0;
    private long survey_findSolutionsTime = 0;
    private long survey_findFirstSolutionTime = 0;
    private int survey_backtracks = 0;
    private int survey_backtracksFirstSolution = 0;
    private int survey_visitedNodes = 0;
    private int survey_visitedNodesFirstSolution = 0;


    public void run(Sudoku sudoku){
        survey_startTime = System.currentTimeMillis();
        runAlgorithm(sudoku);
        survey_findSolutionsTime = System.currentTimeMillis() - survey_startTime;
    }

    protected abstract void runAlgorithm(Sudoku sudoku);

    private boolean isFirstSolution(){
        return 1 == solution.size();
    }

    private void setSurveyVariablesAfterFirstSolution(){
        survey_findFirstSolutionTime = System.currentTimeMillis() - survey_startTime;
        survey_visitedNodesFirstSolution = survey_visitedNodes;
        survey_backtracksFirstSolution = survey_backtracks;
    }

    protected void incrementBacktracks(){
        survey_backtracks++;
    }

    protected void incrementVisitedNodes(){
        survey_visitedNodes++;
    }


    protected void selectCellPotentialValue(Cell selectedCell, Sudoku sudoku){
        selectedCellPotentialValue = cellValueSelection.selectCellPotentialValue(selectedCell, sudoku);
    }

    protected void setCellValueInOffspringSudoku(Sudoku offspringSudoku, Cell selectedCell, int selectedCellRowIdx, int selectedCellColumnIdx){
        selectedCell.shrinkDomain(selectedCellPotentialValue);
        offspringSudoku.setCellValue(selectedCellRowIdx, selectedCellColumnIdx, selectedCellPotentialValue);
    }

    protected void updateSolution(Sudoku offspringSudoku){
        solution.add(offspringSudoku);

        if (isFirstSolution())
            setSurveyVariablesAfterFirstSolution();
    }


    public long getSurvey_findSolutionsTime() {
        return survey_findSolutionsTime;
    }

    public long getSurvey_findFirstSolutionTime() {
        return survey_findFirstSolutionTime;
    }

    public int getSurvey_backtracks() {
        return survey_backtracks;
    }

    public int getSurvey_backtracksFirstSolution() {
        return survey_backtracksFirstSolution;
    }

    public int getSurvey_visitedNodes() {
        return survey_visitedNodes;
    }

    public int getSurvey_visitedNodesFirstSolution() {
        return survey_visitedNodesFirstSolution;
    }

    public ArrayList<Sudoku> getSolution() {
        return solution;
    }
}
