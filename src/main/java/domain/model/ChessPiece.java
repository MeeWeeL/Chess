package domain.model;

import org.jetbrains.annotations.NotNull;

public abstract class ChessPiece {
    protected final ChessColor color;
    protected final ChessPieceType type;
    public boolean hasMoved = false;

    protected ChessPiece(ChessColor color, ChessPieceType type) {
        this.color = color;
        this.type = type;
    }

    @NotNull
    public ChessColor getColor() {
        return color;
    }

    @NotNull
    public ChessPieceType getType() {
        return type;
    }

    public abstract boolean canMoveToPosition(ChessBoard chessBoard, int line, int column, int toLine, int toColumn);
}
