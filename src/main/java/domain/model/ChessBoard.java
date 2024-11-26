package domain.model;

import domain.model.pieces.Bishop;
import domain.model.pieces.Horse;
import domain.model.pieces.King;
import domain.model.pieces.Pawn;
import domain.model.pieces.Queen;
import domain.model.pieces.Rook;

public class ChessBoard {
    public final ChessPiece[][] board = new ChessPiece[8][8];

    public ChessBoard() {
        initializeBoard();
    }

    private void initializeBoard() {
        board[0][0] = new Rook(ChessColor.BLACK);
        board[0][1] = new Horse(ChessColor.BLACK);
        board[0][2] = new Bishop(ChessColor.BLACK);
        board[0][3] = new Queen(ChessColor.BLACK);
        board[0][4] = new King(ChessColor.BLACK);
        board[0][5] = new Bishop(ChessColor.BLACK);
        board[0][6] = new Horse(ChessColor.BLACK);
        board[0][7] = new Rook(ChessColor.BLACK);
        for (int i = 0; i < 8; i++) {
            board[1][i] = new Pawn(ChessColor.BLACK);
            board[6][i] = new Pawn(ChessColor.WHITE);
        }
        board[7][0] = new Rook(ChessColor.WHITE);
        board[7][1] = new Horse(ChessColor.WHITE);
        board[7][2] = new Bishop(ChessColor.WHITE);
        board[7][3] = new Queen(ChessColor.WHITE);
        board[7][4] = new King(ChessColor.WHITE);
        board[7][5] = new Bishop(ChessColor.WHITE);
        board[7][6] = new Horse(ChessColor.WHITE);
        board[7][7] = new Rook(ChessColor.WHITE);
    }

    public boolean isKingInCheck(ChessColor color) {
        int kingLine = -1;
        int kingColumn = -1;

        for (int line = 0; line < 8; line++) {
            for (int column = 0; column < 8; column++) {
                ChessPiece piece = board[line][column];
                if (piece instanceof King && piece.getColor().equals(color)) {
                    kingLine = line;
                    kingColumn = column;
                    break;
                }
            }
            if (kingLine != -1) {
                break;
            }
        }

        for (int line = 0; line < 8; line++) {
            for (int column = 0; column < 8; column++) {
                ChessPiece piece = board[line][column];
                if (piece != null && !piece.getColor().equals(color)) {
                    if (piece.canMoveToPosition(this, line, column, kingLine, kingColumn)) {
                        return true;
                    }
                }
            }
        }

        return false;
    }

    public ChessPiece getPiece(int line, int column) {
        return board[line][column];
    }

    public void move(ChessPiece piece, int startLine, int startColumn, int endLine, int endColumn) {
        board[startLine][startColumn] = null;
        board[endLine][endColumn] = piece;
    }
}
