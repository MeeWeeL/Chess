import androidx.compose.ui.graphics.Color
import domain.model.ChessColor
import domain.model.ChessPiece
import domain.model.ChessPieceType

fun ChessPiece.imagePath(): String {
    return when (type) {
        ChessPieceType.PAWN -> {
            when (color) {
                ChessColor.WHITE -> "pawn_white.svg"
                ChessColor.BLACK -> "pawn_black.svg"
            }
        }

        ChessPieceType.HORSE -> {
            when (color) {
                ChessColor.WHITE -> "horse_white.svg"
                ChessColor.BLACK -> "horse_black.svg"
            }
        }

        ChessPieceType.BISHOP -> {
            when (color) {
                ChessColor.WHITE -> "bishop_white.svg"
                ChessColor.BLACK -> "bishop_black.svg"
            }
        }

        ChessPieceType.ROOK -> {
            when (color) {
                ChessColor.WHITE -> "rook_white.svg"
                ChessColor.BLACK -> "rook_black.svg"
            }
        }

        ChessPieceType.QUEEN -> {
            when (color) {
                ChessColor.WHITE -> "queen_white.svg"
                ChessColor.BLACK -> "queen_black.svg"
            }
        }

        ChessPieceType.KING -> {
            when (color) {
                ChessColor.WHITE -> "king_white.svg"
                ChessColor.BLACK -> "king_black.svg"
            }
        }
    }
}

fun ChessPiece.composeColor() = if (color == ChessColor.WHITE) Color.White else Color.Black

fun ChessColor.toCompose() = if (this == ChessColor.WHITE) Color.White else Color.Black