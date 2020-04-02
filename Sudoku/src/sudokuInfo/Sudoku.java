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

    public void setCellValue(Cell cell, int value){
        cell.setValue(value);
    }

    @Override
    public String toString() {
        StringBuilder s = new StringBuilder();
        for (ArrayList<Cell> row: board){
            for (Cell cell: row)
                s.append(cell).append(" ");

            s.append("\n");
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
}