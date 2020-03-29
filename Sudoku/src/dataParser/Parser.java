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

            while (getPuzzleIndexFromLine(line = reader.readLine()) != puzzleNumber) {}
            loadPuzzle(line);
            reader.close();

        }
        catch (IOException e) {
            e.printStackTrace();
        }

        return new Puzzle(puzzleDifficulty, board);

    }

    private int getPuzzleIndexFromLine(String line){
        StringTokenizer tokenizer = new StringTokenizer(line, ";");
        return Integer.parseInt(tokenizer.nextToken());
    }

    private void loadPuzzle(String line){
        StringTokenizer tokenizer = new StringTokenizer(line, ";");
        tokenizer.nextToken(); // index
        puzzleDifficulty = Double.parseDouble(tokenizer.nextToken());
        loadBoard(tokenizer.nextToken());
    }

    private void loadBoard(String str){
        ArrayList<ArrayList<Integer>> board = new ArrayList<ArrayList<Integer>>();
        String strWithoutDots = str.replace(".", "0");

        ArrayList<Integer> row = new ArrayList<Integer>();
        for (char c: strWithoutDots.toCharArray()) {
            if (row.size() == 9){
                board.add(row);
                row = new ArrayList<Integer>();
            }
            else
                row.add(Character.getNumericValue(c));
        }
        this.board = new Board(board);
    }
}
