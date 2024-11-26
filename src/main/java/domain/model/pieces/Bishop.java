package domain.model.pieces;

import domain.model.ChessBoard;
import domain.model.ChessColor;
import domain.model.ChessPiece;
import domain.model.ChessPieceType;

public class Bishop extends ChessPiece {

    public Bishop(ChessColor color) {
        super(color, ChessPieceType.BISHOP);
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

        if (Math.abs(toLine - line) == Math.abs(toColumn - column)) {
            int rowStep = (toLine - line) > 0 ? 1 : -1;
            int colStep = (toColumn - column) > 0 ? 1 : -1;
            int row = line + rowStep;
            int col = column + colStep;
            while (row != toLine && col != toColumn) {
                if (chessBoard.getPiece(row, col) != null) {
                    return false;
                }
                row += rowStep;
                col += colStep;
            }
            return true;
        }
        return false;
    }
}
