package domain.model.pieces;

import domain.model.ChessBoard;
import domain.model.ChessColor;
import domain.model.ChessPiece;
import domain.model.ChessPieceType;

public class Queen extends ChessPiece {

    public Queen(ChessColor color) {
        super(color, ChessPieceType.QUEEN);
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

        boolean isDiagonalMove = Math.abs(toLine - line) == Math.abs(toColumn - column);
        boolean isStraightMove = (line == toLine || column == toColumn);

        if (isDiagonalMove) {
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
        } else if (isStraightMove) {
            int step;
            if (line == toLine) {
                step = column < toColumn ? 1 : -1;
                for (int col = column + step; col != toColumn; col += step) {
                    if (chessBoard.getPiece(line, col) != null) {
                        return false;
                    }
                }
            } else {
                step = line < toLine ? 1 : -1;
                for (int row = line + step; row != toLine; row += step) {
                    if (chessBoard.getPiece(row, column) != null) {
                        return false;
                    }
                }
            }
            return true;
        }
        return false;
    }
}
