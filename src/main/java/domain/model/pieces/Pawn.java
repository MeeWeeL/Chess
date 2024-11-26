package domain.model.pieces;

import domain.model.ChessBoard;
import domain.model.ChessColor;
import domain.model.ChessPiece;
import domain.model.ChessPieceType;

public class Pawn extends ChessPiece {

    public Pawn(ChessColor color) {
        super(color, ChessPieceType.PAWN);
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

        if (this.getColor() == ChessColor.BLACK) {
            if (toLine == line + 1 && column == toColumn) {
                if (targetPiece == null) {
                    return true;
                }
            }
            if (line == 1 && toLine == line + 2 && column == toColumn) {
                if (targetPiece == null && chessBoard.getPiece(line + 1, column) == null) {
                    return true;
                }
            }
            if (toLine == line + 1 && Math.abs(column - toColumn) == 1) {
                return targetPiece != null && !targetPiece.getColor().equals(this.getColor());
            }
        } else {
            if (toLine == line - 1 && column == toColumn) {
                if (targetPiece == null) {
                    return true;
                }
            }
            if (line == 6 && toLine == line - 2 && column == toColumn) {
                if (targetPiece == null && chessBoard.getPiece(line - 1, column) == null) {
                    return true;
                }
            }
            if (toLine == line - 1 && Math.abs(column - toColumn) == 1) {
                return targetPiece != null && !targetPiece.getColor().equals(this.getColor());
            }
        }
        return false;
    }
}
