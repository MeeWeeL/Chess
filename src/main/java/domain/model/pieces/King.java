package domain.model.pieces;

import domain.model.ChessBoard;
import domain.model.ChessColor;
import domain.model.ChessPiece;
import domain.model.ChessPieceType;

public class King extends ChessPiece {

    public King(ChessColor color) {
        super(color, ChessPieceType.KING);
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

        if ((Math.abs(toLine - line) <= 1 && Math.abs(toColumn - column) <= 1)) {
            if (targetPiece != null && targetPiece.getColor().equals(this.getColor())) {
                new King(this.getColor().equals(ChessColor.WHITE) ? ChessColor.BLACK : ChessColor.WHITE);
            }
            chessBoard.move(this, line, column, toLine, toColumn);
            chessBoard.board[line][column] = null;
            boolean canMove = !isUnderAttack(chessBoard, toLine, toColumn);
            chessBoard.move(this, toLine, toColumn, line, column);
            chessBoard.board[toLine][toColumn] = targetPiece;
            return canMove;
        }

        return false;
    }

    public boolean canCastle(ChessBoard board, int fromLine, int line, int column) {
        if (this.hasMoved) {
            return false;
        }
        if (fromLine != line) return false;

        boolean isShortCastle = column == 7;
        int rookColumn;
        int kingColumn;

        if (isShortCastle) {
            if (board.getPiece(fromLine, 5) != null || board.getPiece(fromLine, 6) != null) return false;
            rookColumn = 7;
            kingColumn = 6;
        } else {
            if (board.getPiece(fromLine, 2) != null
                || board.getPiece(fromLine, 2) != null
                || board.getPiece(fromLine, 3) != null) return false;
            rookColumn = 0;
            kingColumn = 2;
        }

        ChessPiece rook = board.getPiece(line, rookColumn);
        if (rook instanceof Rook) {
            if (rook.hasMoved) {
                return false;
            }

            return !isUnderAttack(board, line, column) &&
                !isUnderAttack(board, line, kingColumn) &&
                !isUnderAttack(board, line, kingColumn - 1);
        } else return false;
    }

    public void performCastle(ChessBoard board, int line, int column) {
        int rookColumn;
        int kingColumn;

        if (column == 7) {
            rookColumn = 7;
            kingColumn = 6;
        } else {
            rookColumn = 0;
            kingColumn = 2;
        }

        Rook rook = (Rook) board.getPiece(line, rookColumn);
        board.move(this, line, 4, line, kingColumn);
        board.move(rook, line, column, line, column == 7 ? kingColumn - 1 : kingColumn + 1);

        this.hasMoved = true;
        rook.hasMoved = true;
    }

    public boolean isUnderAttack(ChessBoard board, int line, int column) {
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                ChessPiece piece = board.getPiece(i, j);
                if (piece != null && piece.getColor() != this.color) {
                    if (piece.canMoveToPosition(board, i, j, line, column)) {
                        return true;
                    }
                }
            }
        }
        return false;
    }
}
