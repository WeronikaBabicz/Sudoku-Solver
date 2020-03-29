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

    public int getValue() {
        return value;
    }

    public ArrayList<Integer> getDomain() {
        return domain;
    }

    private void initializeDomain(){
        if (EMPTY_CELL_VALUE == value)
            domain = new ArrayList<Integer>(List.of(1, 2, 3, 4, 5, 6, 7, 8, 9));
    }

    @Override
    public String toString() {
        return String.valueOf(value);
    }
}
