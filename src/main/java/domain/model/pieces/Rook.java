package domain.model.pieces;

import domain.model.ChessBoard;
import domain.model.ChessColor;
import domain.model.ChessPiece;
import domain.model.ChessPieceType;

public class Rook extends ChessPiece {

    public Rook(ChessColor color) {
        super(color, ChessPieceType.ROOK);
    }

    @Override
    public boolean canMoveToPosition(ChessBoard chessBoard, int line, int column, int toLine, int toColumn) {
        if (toLine < 0 || toLine >= 8 || toColumn < 0 || toColumn >= 8) {
            return false;
        }
        if (line == toLine && column == toColumn) {
            return false;
        }

        ChessPiece targetPiece = chessBoard.getPiece(toLine, toColumn);
        if (targetPiece != null && targetPiece.getColor().equals(this.getColor())) {
            return false;
        }

        if (line == toLine) {
            int step = column < toColumn ? 1 : -1;
            for (int col = column + step; col != toColumn; col += step) {
                if (chessBoard.getPiece(line, col) != null) {
                    return false;
                }
            }
            return true;
        }
        if (column == toColumn) {
            int step = line < toLine ? 1 : -1;
            for (int row = line + step; row != toLine; row += step) {
                if (chessBoard.getPiece(row, column) != null) {
                    return false;
                }
            }
            return true;
        }
        return false;
    }
}
