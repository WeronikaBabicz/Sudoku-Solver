package dataParser;

import puzzleInfo.Board;
import puzzleInfo.Puzzle;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Parser {
    private double puzzleDifficulty;
    private Board board;

    public Puzzle parse(String filename, int puzzleNumber){
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

        return new Puzzle(puzzleDifficulty, board);

    }

    private StringTokenizer splitLine(String line){
        return new StringTokenizer(line, ";");
    }

    private void loadPuzzle(String line){
        StringTokenizer tokenizer = splitLine(line);
        tokenizer.nextToken(); // index
        puzzleDifficulty = Double.parseDouble(tokenizer.nextToken());
        loadBoard(tokenizer.nextToken());
    }

    private void loadBoard(String str){
        ArrayList<ArrayList<Integer>> board = new ArrayList<ArrayList<Integer>>();
        ArrayList<Integer> row = new ArrayList<Integer>();

        String strWithoutDots = str.replace(".", "0");

        for (char c: strWithoutDots.toCharArray()) {
            row.add(Character.getNumericValue(c));
            if (row.size() == Board.SUDOKU_SIZE){
                board.add(row);
                row = new ArrayList<Integer>();
            }
        }
        this.board = new Board(board);
    }
}
