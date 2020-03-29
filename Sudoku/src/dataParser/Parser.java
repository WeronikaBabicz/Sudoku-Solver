package dataParser;

import puzzleInfo.Board;
import puzzleInfo.Cell;
import puzzleInfo.Sudoku;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Parser {
    private double puzzleDifficulty;
    private Board board;

    public Sudoku parse(String filename, int puzzleNumber){
        BufferedReader reader;
        try {
            reader = new BufferedReader(new FileReader(new File(getClass().getClassLoader().getResource("puzzleData/" +filename).getFile())));
            reader.readLine();
            String line;

            while (puzzleNumber != Integer.parseInt(splitLine(line = reader.readLine()).nextToken())) {}
            loadPuzzle(line);
            reader.close();

        }
        catch (IOException e) {
            e.printStackTrace();
        }

        return new Sudoku(puzzleDifficulty, board);

    }

    private StringTokenizer splitLine(String line){
        return new StringTokenizer(line, ";");
    }

    private void loadPuzzle(String line){
        StringTokenizer tokenizer = splitLine(line);
        tokenizer.nextToken();
        puzzleDifficulty = Double.parseDouble(tokenizer.nextToken());
        loadBoard(tokenizer.nextToken());
    }

    private void loadBoard(String str){
        ArrayList<ArrayList<Cell>> board = new ArrayList<ArrayList<Cell>>();
        ArrayList<Cell> row = new ArrayList<Cell>();

        String strWithoutDots = str.replace(".", String.valueOf(Cell.EMPTY_CELL_VALUE));

        for (char c: strWithoutDots.toCharArray()) {
            row.add(new Cell(Character.getNumericValue(c)));
            if (row.size() == Board.SUDOKU_SIZE){
                board.add(row);
                row = new ArrayList<Cell>();
            }
        }
        this.board = new Board(board);
    }
}
