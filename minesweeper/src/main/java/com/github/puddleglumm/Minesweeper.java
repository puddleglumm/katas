package com.github.puddleglumm;

public class Minesweeper {

    private String board;

    public int getBoardWidth() {
        return boardWidth;
    }

    private final int boardWidth;

    public int getBoardHeight() {
        return boardHeight;
    }

    private final int boardHeight;

    public static void main(String[] args) {
        System.out.println("It's alive!");
        StringBuilder hintMap = new StringBuilder(25);
        hintMap.insert(0, "-1*1\n");
        hintMap.insert(0, "1211\n");
        hintMap.insert(0, "*311\n");
        hintMap.insert(0, "*4*2\n");
        hintMap.insert(0,"13*2\n");

        String expectedHints =  "13*2\n" +
                "*4*2\n" +
                "*311\n" +
                "1211\n" +
                "-1*1\n";

        System.out.println(hintMap.toString());

        if( expectedHints.equals(hintMap.toString())) {
            System.out.println("Strings matched!");
        }
    }

    public Minesweeper(String board) {
        String[] boardRows = board.split("\n");
        this.boardHeight = boardRows.length;
        this.boardWidth = boardRows[0].length();
        this.board = String.join("", boardRows);
    }

    public char charAt(int x, int y) {
        return board.charAt(boardWidth * (boardHeight - (y + 1)) + x);
    }

    public int countBombsInArea(int x, int y) {
        int surroundingBombs = 0;
        for (int i_x = x - 1; i_x <= x + 1; i_x++) {
            for (int i_y = y -1; i_y <= y + 1; i_y++) {
                if (isInBounds(i_x, i_y) && this.charAt(i_x, i_y) == '*') {
                    surroundingBombs++;
                }
            }
        }
        return surroundingBombs;
    }

    public boolean isInBounds(int x, int y) {
        return  (0 <= x && x < boardWidth) &&
                (0 <= y && y < boardHeight);
    }

    public String generateHintMap() {

        StringBuilder hintMap = new StringBuilder ((boardWidth + 1) * boardHeight);

        for (int i_y = 0; i_y < boardHeight; i_y++) {
            StringBuilder row = new StringBuilder(boardWidth + 1);
            for (int i_x = 0; i_x < boardWidth; i_x++) {
                if (this.charAt(i_x, i_y) != '*') {
                    int bombCount = countBombsInArea(i_x, i_y);
                    if (bombCount == 0) {
                        row.append('-');
                    } else {
                        row.append(bombCount);
                    }
                } else {
                    row.append('*');
                }
            }
            row.append("\n");
            hintMap.insert(0, row);
        }
        return  hintMap.toString();
    }
}
