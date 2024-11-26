package domain.model.pieces;

import domain.model.ChessBoard;
import domain.model.ChessColor;
import domain.model.ChessPiece;
import domain.model.ChessPieceType;

public class Horse extends ChessPiece {

    public Horse(ChessColor color) {
        super(color, ChessPieceType.HORSE);
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

        return (Math.abs(toLine - line) == 2 && Math.abs(toColumn - column) == 1) ||
            (Math.abs(toLine - line) == 1 && Math.abs(toColumn - column) == 2);
    }
}
