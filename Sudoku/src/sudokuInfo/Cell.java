package sudokuInfo;

import java.util.ArrayList;
import java.util.List;

public class Cell {
    public static int EMPTY_CELL_VALUE = 0;
    private int value;
    private ArrayList<Integer> domain = new ArrayList<Integer>();

    public Cell(int value) {
        this.value = value;
        initializeDomain();
    }

    Cell(Cell other){
        this.value = other.value;
        this.domain = new ArrayList<Integer>(other.domain);
    }

    public int getValue() {
        return value;
    }

    void setValue(int value) {
        this.value = value;
    }

    public ArrayList<Integer> getDomain() {
        return domain;
    }

    private void initializeDomain(){
        if (isEmptyCell())
            domain = new ArrayList<Integer>(List.of(1, 2, 3, 4, 5, 6, 7, 8, 9));
    }

    @Override
    public String toString() {
        return String.valueOf(value);
    }

    public void shrinkDomain(int value){
        domain.remove(Integer.valueOf(value));
    }

    public boolean isEmptyCell(){
        return value == EMPTY_CELL_VALUE;
    }
}
