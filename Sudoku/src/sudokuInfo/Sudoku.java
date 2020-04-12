package sudokuInfo;

import java.util.ArrayList;

public class Sudoku {
    public static final int SUDOKU_SIZE = 9;

    private double difficulty;
    private ArrayList<ArrayList<Cell>> board;

    public Sudoku(ArrayList<ArrayList<Cell>> board, double difficulty) {
        this.board = board;
        this.difficulty = difficulty;
    }

    public Sudoku(Sudoku b) {
        ArrayList<ArrayList<Cell>> board = new ArrayList<ArrayList<Cell>>();
        ArrayList<Cell> row = new ArrayList<Cell>();
        for (ArrayList<Cell> bd : b.getBoard()){
            for (Cell cell : bd){
                row.add(new Cell(cell));
            }
            board.add(row);
            row = new ArrayList<Cell>();
        }
        this.board = board;
        this.difficulty = b.difficulty;
    }

    public ArrayList<ArrayList<Cell>> getBoard() {
        return board;
    }

    public double getDifficulty() {
        return difficulty;
    }

    public Cell getCellFromBoard(int row, int column){
        return board.get(row).get(column);
    }

    public int getRowIndexOfCell(Cell cell){
        return board.indexOf(getRow(cell));
    }

    public int getColumnIndexOfCell(Cell cell){
        return board.get(getRowIndexOfCell(cell)).indexOf(cell);
    }

    public int numberOfEmptyCells(){
        int numberOfEmptyCells = 0;
        for (ArrayList<Cell> row: board){
            for (Cell cell: row){
                if (cell.isEmptyCell())
                    numberOfEmptyCells++;

            }
        }
        return numberOfEmptyCells;
    }


    private boolean isFilled(){
        for (ArrayList<Cell> row : board){
            for (Cell cell : row){
                if (cell.getValue() == Cell.EMPTY_CELL_VALUE)
                    return false;
            }
        }
        return true;
    }

    public boolean hasCorrectValues(){
        return areProperColumns() && areProperRows() && areProperGrids();
    }

    public boolean isSolved(){
        return isFilled() && hasCorrectValues();
    }

    private ArrayList<Cell> getRow(Cell cell){
        for (ArrayList<Cell> row : board){
            if (row.contains(cell))
                return row;
        }
        return null;
    }

    public void setCellValue(int row, int column, int value){
        board.get(row).get(column).setValue(value);
    }

    @Override
    public String toString() {
        StringBuilder s = new StringBuilder();
        for (int i = 0; i < board.size(); i++){
            if (i % 3 == 0)
                s.append("-------------------------").append("\n");
            for (int j = 0; j < board.get(i).size(); j++){
                if (j % 3 == 0)
                    s.append("| ");
                s.append(board.get(i).get(j)).append(" ");
                if (j == board.get(i).size() - 1)
                    s.append("| ");
            }
            s.append("\n");
            if (i == board.size() - 1)
                s.append("-------------------------").append("\n");
        }
        return String.valueOf(s);
    }

    private boolean isProperColumn(int i){
        ArrayList<Integer> visited = new ArrayList<>();
        for (ArrayList<Cell> row : board){
            visited.removeIf(n -> n == 0);
            if (!visited.contains(row.get(i).getValue()))
                visited.add(row.get(i).getValue());
            else return false;
        }
        return true;
    }

    private boolean areProperColumns(){
        for (int i = 0; i < board.get(0).size(); i++){
            if (!isProperColumn(i))
                return false;
        }
        return true;
    }

    private boolean isProperRow(int i){
        ArrayList<Integer> visited = new ArrayList<>();
        for (Cell cell : board.get(i)){
            visited.removeIf(n -> n == 0);
            if (!visited.contains(cell.getValue()))
                visited.add(cell.getValue());
            else return false;
        }
        return true;
    }

    private boolean areProperRows(){
        for (int i = 0; i < board.size(); i++){
            if (!isProperRow(i))
                return false;
        }
        return true;
    }

    private boolean isProperGrid(int startRowIndex, int startColumnIndex){
        ArrayList<Integer> visited = new ArrayList<>();
        for (int i = startRowIndex; i < startRowIndex + 3; i++){
            for (int j = startColumnIndex; j < startColumnIndex + 3; j++){
                visited.removeIf(n -> n == 0);
                if (!visited.contains(board.get(i).get(j).getValue())){
                    visited.add(board.get(i).get(j).getValue());
                }

                else return false;
            }
        }
        return true;
    }

    private boolean areProperGrids(){
        for (int i = 0; i < board.size(); i = i + 3){
            for (int j = 0; j < board.get(i).size(); j = j + 3){
                if (!isProperGrid(i, j))
                    return false;
            }
        }
        return true;
    }

    public boolean shrinkDomains(Cell cell, int shrinkValue){
        return
        shrinkDomainsOfCellsInRow(getRowIndexOfCell(cell), shrinkValue) &&
        shrinkDomainsOfCellsInColumn(getColumnIndexOfCell(cell), shrinkValue) &&
        shrinkDomainsOfCellsInGrid(getRowIndexOfCell(cell), getColumnIndexOfCell(cell), shrinkValue);
    }

    private boolean shrinkDomainsOfCellsInRow(int rowIndex, int shrinkValue){
        for (Cell cell : board.get(rowIndex)) {
            cell.shrinkDomain(shrinkValue);
            if (hasEmptyCellEmptyDomain(cell))
                return false;
        }
        return true;
    }

    private boolean shrinkDomainsOfCellsInColumn(int columnIndex, int shrinkValue){
        for (ArrayList<Cell> row : board) {
            row.get(columnIndex).shrinkDomain(shrinkValue);
            if (hasEmptyCellEmptyDomain(row.get(columnIndex)))
                return false;
        }
        return true;
    }

    private boolean shrinkDomainsOfCellsInGrid(int rowIndex, int columnIndex, int shrinkValue){
        int startRowIdx = rowIndex - (rowIndex % 3);
        int startColumnIdx = columnIndex - (columnIndex % 3);

        for (int i = startRowIdx; i < startRowIdx + 3; i++){
            for (int j = startColumnIdx; j < startColumnIdx + 3; j++) {
                board.get(i).get(j).shrinkDomain(shrinkValue);
                if (hasEmptyCellEmptyDomain(board.get(i).get(j)))
                    return false;
            }
        }
        return true;
    }

    private boolean hasEmptyCellEmptyDomain(Cell cell){
        return cell.isEmptyCell() && cell.getDomain().size() == 0;
    }
}