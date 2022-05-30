package com.github.puddleglumm;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/* TODO:
 *
 */
public class MinesweeperTest {

    @Test
    public void testCharAtTopLeftCorner() {
        String board =  "*---\n" +
                        "----\n" +
                        "----\n" +
                        "----\n" +
                        "----";
        Minesweeper minesweeper = new Minesweeper(board);
        char charAtTopLeft = minesweeper.charAt( 0, 4);
        assertEquals('*', charAtTopLeft);
    }

    @Test
    public void testCharAtTopRightCorner() {
        String board =  "---*\n" +
                        "----\n" +
                        "----\n" +
                        "----\n" +
                        "----";
        Minesweeper minesweeper = new Minesweeper(board);
        char charAtTopLeft = minesweeper.charAt( 3, 4);
        assertEquals('*', charAtTopLeft);
    }

    @Test
    public void testCharAtBottomRightCorner() {
        String board =  "----\n" +
                        "----\n" +
                        "----\n" +
                        "----\n" +
                        "---*";
        Minesweeper minesweeper = new Minesweeper(board);
        char charAtTopLeft = minesweeper.charAt( 3, 0);
        assertEquals('*', charAtTopLeft);
    }

    @Test
    public void testCharAtBottomLeftCorner() {
        String board =  "----\n" +
                        "----\n" +
                        "----\n" +
                        "----\n" +
                        "*---";
        Minesweeper minesweeper = new Minesweeper(board);
        char charAtTopLeft = minesweeper.charAt( 0, 0);
        assertEquals('*', charAtTopLeft);
    }

    @Test
    public void testCharAtMiddle() {
        String board =  "----\n" +
                        "----\n" +
                        "--*-\n" +
                        "----\n" +
                        "----";
        Minesweeper minesweeper = new Minesweeper(board);
        char charAtTopLeft = minesweeper.charAt( 1, 2);
        assertEquals('-', charAtTopLeft);
    }

    @Test
    public void testParsedBoardHeight() {
        String board =  "*---\n" +
                        "----\n" +
                        "----\n" +
                        "----\n" +
                        "----";
        Minesweeper minesweeper = new Minesweeper(board);
        assertEquals(5, minesweeper.getBoardHeight());
    }

    @Test
    public void testParsedBoardWidth() {
        String board =  "*---\n" +
                        "----\n" +
                        "----\n" +
                        "----\n" +
                        "----";
        Minesweeper minesweeper = new Minesweeper(board);
        int parsedBoardWidth = minesweeper.getBoardWidth();
        assertEquals(4, parsedBoardWidth);
    }

    @Test
    public void testCountBombsNoneMiddle() {
        String board =  "*---\n" +
                        "----\n" +
                        "----\n" +
                        "----\n" +
                        "----";
        Minesweeper minesweeper = new Minesweeper(board);
        int bombCount = minesweeper.countBombsInArea(1, 1);

        assertEquals(0, bombCount);
    }

    @Test
    public void testInBoundsNegativeX() {
        String board =  "*---\n" +
                        "----\n" +
                        "----\n" +
                        "----\n" +
                        "----";
        Minesweeper minesweeper = new Minesweeper(board);
        boolean isInBounds = minesweeper.isInBounds(-1, 0);

        assertFalse(isInBounds);
    }

    @Test
    public void testInBoundsOverflowX() {
        String board =  "*---\n" +
                        "----\n" +
                        "----\n" +
                        "----\n" +
                        "----";
        Minesweeper minesweeper = new Minesweeper(board);
        boolean isInBounds = minesweeper.isInBounds(4, 0);

        assertFalse(isInBounds);
    }

    @Test
    public void testInBoundsNegativeY() {
        String board =  "*---\n" +
                        "----\n" +
                        "----\n" +
                        "----\n" +
                        "----";
        Minesweeper minesweeper = new Minesweeper(board);
        boolean isInBounds = minesweeper.isInBounds(0, -1);

        assertFalse(isInBounds);
    }

    @Test
    public void testInBoundsOverflowY() {
        String board =  "*---\n" +
                        "----\n" +
                        "----\n" +
                        "----\n" +
                        "----";
        Minesweeper minesweeper = new Minesweeper(board);
        boolean isInBounds = minesweeper.isInBounds(0, 5);

        assertFalse(isInBounds);
    }

    @Test
    public void testCountBombsOneMiddle() {
        String board =  "*---\n" +
                        "----\n" +
                        "----\n" +
                        "----\n" +
                        "----";
        Minesweeper minesweeper = new Minesweeper(board);
        int bombCount = minesweeper.countBombsInArea(1, 3);

        assertEquals(1, bombCount);
    }

    @Test
    public void testCountBombsOneTopLeft() {
        String board =  "----\n" +
                        "-*--\n" +
                        "----\n" +
                        "----\n" +
                        "----";
        Minesweeper minesweeper = new Minesweeper(board);
        int bombCount = minesweeper.countBombsInArea(0, 3);

        assertEquals(1, bombCount);
    }

    @Test
    public void testCountBombsOneOnSquareWithBomb() {
        String board =  "----\n" +
                        "-*--\n" +
                        "-*--\n" +
                        "----\n" +
                        "----";
        Minesweeper minesweeper = new Minesweeper(board);
        int bombCount = minesweeper.countBombsInArea(1, 3);

        assertEquals(2, bombCount);
    }

    @Test
    public void testCountBombsSeven() {
        String board =  "***-\n" +
                        "*-*-\n" +
                        "**--\n" +
                        "----\n" +
                        "----";
        Minesweeper minesweeper = new Minesweeper(board);
        int bombCount = minesweeper.countBombsInArea(1, 3);

        assertEquals(7, bombCount);
    }

    @Test
    public void testGenerateHintMap() {
        String board =  "--*-\n" +
                        "*-*-\n" +
                        "*---\n" +
                        "----\n" +
                        "--*-";

        String expectedHints =  "13*2\n" +
                                "*4*2\n" +
                                "*311\n" +
                                "1211\n" +
                                "-1*1\n";

        Minesweeper minesweeper = new Minesweeper(board);
        String hints = minesweeper.generateHintMap();
        assertEquals(expectedHints, hints);
    }
}
