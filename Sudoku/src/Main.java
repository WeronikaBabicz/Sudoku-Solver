import dataParser.Parser;
import puzzleInfo.Puzzle;

public class Main {
    public static void main(String [] args){
        Parser parser = new Parser();
        Puzzle puzzle = parser.parse("Sudoku.csv", 10);
    }
}
