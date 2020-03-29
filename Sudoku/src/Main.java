import dataParser.Parser;
import puzzle.Puzzle;

public class Main {
    public static void main(String [] args){
        Parser parser = new Parser();
        Puzzle problemData = parser.parse("Sudoku.csv", 10);
    }
}
