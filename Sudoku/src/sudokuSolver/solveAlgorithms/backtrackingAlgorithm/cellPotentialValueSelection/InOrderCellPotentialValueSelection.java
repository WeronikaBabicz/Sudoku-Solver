package sudokuSolver.solveAlgorithms.backtrackingAlgorithm.cellPotentialValueSelection;

import sudokuInfo.Cell;

import java.util.Collections;

public class InOrderCellPotentialValueSelection implements CellPotentialValueSelection {
    @Override
    public int selectCellValue(Cell cell) {
        if (cell.getDomain().size() > 0){
            Collections.sort(cell.getDomain());
            return cell.getDomain().get(0);
        }
        return Cell.EMPTY_CELL_VALUE;
    }
}
