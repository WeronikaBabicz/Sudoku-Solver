package sudokuSolver.solveAlgorithms.heuristics.cellPotentialValueSelection;

import sudokuInfo.Cell;
import sudokuInfo.Sudoku;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Map;
import java.util.stream.Collectors;

public class LeastOccurencedInRowCellValueSelection implements CellValueSelection{
    @Override
    public int selectCellPotentialValue(Cell cell, Sudoku sudoku) {
        int rowIndex = sudoku.getRowIndexOfCell(cell);
        ArrayList<Integer> domainValuesInRow = new ArrayList<>();
        for (Cell c : sudoku.getBoard().get(rowIndex))
            domainValuesInRow.addAll(cell.getDomain());

        int leastOccurenced = domainValuesInRow.stream()
                            .collect(Collectors.groupingBy(w -> w, Collectors.counting()))
                            .entrySet()
                            .stream()
                            .min(Comparator.comparing(Map.Entry::getValue))
                            .get()
                            .getKey();
        return leastOccurenced;

    }
}
