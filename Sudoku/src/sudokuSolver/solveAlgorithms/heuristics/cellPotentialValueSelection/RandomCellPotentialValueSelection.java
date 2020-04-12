package sudokuSolver.solveAlgorithms.heuristics.cellPotentialValueSelection;

import sudokuInfo.Cell;

public class RandomCellPotentialValueSelection implements CellPotentialValueSelection {
    @Override
    public int selectCellPotentialValue(Cell cell) {
        if (cell.getDomain().size() > 0){
            return cell.getDomain().get((int)(Math.random() * cell.getDomain().size()));
        }
        return Cell.EMPTY_CELL_VALUE;
    }
}
