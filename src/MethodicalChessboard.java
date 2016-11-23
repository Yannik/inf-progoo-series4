import acm.graphics.*;
import acm.program.*;

import java.awt.*;

public class MethodicalChessboard extends GraphicsProgram {

    public static int chessfieldWidth = 100;
    public static int colNameWidth = 50;
    public static String[] columns = {"A", "B", "C", "D", "E", "F", "G", "H"};
    public static String[] rows = {"8", "7", "6", "5", "4", "3", "2", "1" };
    public static String[] pieces_player0 = {"\u2659", "\u2658", "\u2657", "\u2656", "\u2655", "\u2654"};
    public static String[] pieces_player1 = {"\u265F", "\u265E", "\u265D", "\u265C", "\u265B", "\u265A"};
    public static int[] default_chessboard = { 3, 1, 2, 4, 5, 2, 1, 3 };

    public void run() {
        setSize(chessfieldWidth * columns.length + colNameWidth * 2, chessfieldWidth * rows.length + colNameWidth * 2);
        drawLabels();
        drawChessboard();

        // draw pieces
        for (int columnNumber = 0; columnNumber < columns.length; columnNumber++) {
            // pawns
            drawPiece(colNameWidth + chessfieldWidth * columnNumber,
                    colNameWidth + chessfieldWidth,
                    0,
                    1);
            drawPiece(colNameWidth + chessfieldWidth * columnNumber,
                    colNameWidth + chessfieldWidth * (rows.length - 2),
                    0,
                    0);

            // other pieces
            drawPiece(colNameWidth + chessfieldWidth * columnNumber,
                    colNameWidth,
                    default_chessboard[columnNumber],
                    1);
            drawPiece(colNameWidth + chessfieldWidth * columnNumber,
                    colNameWidth + chessfieldWidth * (rows.length - 1),
                    default_chessboard[columnNumber],
                    0);
        }
    }

    public void drawChessboard() {
        for (int rowNumber = 0; rowNumber < rows.length; rowNumber++) {
            for (int columnNumber = 0; columnNumber < columns.length; columnNumber++) {
                Color color = Color.WHITE;

                if ((columnNumber + rowNumber) % 2 == 1) {
                    color = Color.LIGHT_GRAY;
                }

                drawSquare(colNameWidth + chessfieldWidth * columnNumber,
                        colNameWidth + chessfieldWidth * rowNumber,
                        color);
            }

        }
    }

    public void drawLabels() {
        printColumnNames(0);
        printColumnNames(colNameWidth + chessfieldWidth * rows.length);

        for (int rowNumber = 0; rowNumber < rows.length; rowNumber++) {
            GLabel preLabel = new GLabel(rows[rowNumber]);
            GLabel postLabel = new GLabel(rows[rowNumber]);
            add(preLabel,
                    elementHorizontalCenter(preLabel, colNameWidth),
                    colNameWidth + chessfieldWidth * rowNumber + elementVerticalCenter(preLabel, chessfieldWidth));
            add(postLabel,
                    elementHorizontalCenter(postLabel, colNameWidth) + colNameWidth + chessfieldWidth * columns.length,
                    colNameWidth + chessfieldWidth * rowNumber + elementVerticalCenter(postLabel, chessfieldWidth));

        }
    }

    /**
     * Draws the square identified by {@code x} and {@code y}
     * in the color {@code color}.
     *
     * @param x
     *            file index
     * @param y
     *            rank index
     * @param color
     *            chosen color
     */
    public void drawSquare(int x, int y, Color color) {
        GRect field = new GRect(chessfieldWidth, chessfieldWidth);
        field.setFilled(true);
        field.setFillColor(color);
        add(field, x, y);
    }

    /**
     * Draws a specific chess {@code piece} of a {@code player} at the position of a specific square
     * identified by {@code x} and {@code y}.
     * Pieces are: 0 pawn, 1 Knight, 2 Bishop, 3 Rook, 4 Queen, 5 King
     * The player playing white is identified by 0.
     * The player playing with the black pieces is identified by 1.
     *
     * @param x
     *            file index
     * @param y
     *            rank index
     * @param piece
     *            the piece
     * @param player
     *            player 1 or player 2
     */
    public void drawPiece(int x, int y, int piece, int player) {
        String unicodeChar = pieces_player0[piece];
        if (player == 1) {
            unicodeChar = pieces_player1[piece];
        }

        GLabel labelPiece = new GLabel(unicodeChar);
        labelPiece.setFont(Font.decode("SansSerif-44"));

        // we need to subtract "labelPiece.getBounds().getY() from the y value
        // to fix a really weird bug: apparantly, the labelPiece has a y value
        // that is negative (-41 for pawns) by default?!
        add(labelPiece,
                x + elementHorizontalCenter(labelPiece, chessfieldWidth),
                y + elementVerticalCenter(labelPiece, chessfieldWidth) - labelPiece.getBounds().getY());
    }


    public void printColumnNames(int height) {
        for (int i=0; i < columns.length; i++) {
            GLabel label = new GLabel(columns[i]);
            add(label,
                    colNameWidth + chessfieldWidth * i + elementHorizontalCenter(label, chessfieldWidth),
                    height + elementVerticalCenter(label, colNameWidth));
        }
    }

    public double elementHorizontalCenter(GObject object, int width) {
        return (width - object.getWidth()) / 2;
    }

    public double elementVerticalCenter(GObject object, int height) {
        return (height - object.getHeight()) / 2;
    }

}
