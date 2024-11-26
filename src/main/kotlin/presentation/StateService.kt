package presentation

import domain.model.ChessBoard
import domain.model.ChessColor
import domain.model.pieces.King
import domain.model.pieces.Rook
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class StateService {
    private val board: ChessBoard = ChessBoard()
    private val scope = CoroutineScope(Dispatchers.IO + Job())

    private val _state = MutableStateFlow(ChessState(board))
    val state = _state.asStateFlow()

    fun selectPiece(i: Int, j: Int) {
        if (board.isKingInCheck(_state.value.move)) return
        val piece = board.getPiece(i, j)
        if (piece.color != _state.value.move) return
        val list: MutableList<Pair<Int, Int>> = mutableListOf()
        board.board.forEachIndexed { newJ, line ->
            line.forEachIndexed { newI, chessPiece ->
                if (piece.canMoveToPosition(board, i, j, newI, newJ)) {
                    list.add(Pair(newI, newJ))
                } else if (piece is King && board.getPiece(newI, newJ) is Rook && piece.canCastle(
                        board,
                        i,
                        newI,
                        newJ
                    )
                ) {
                    list.add(Pair(newI, newJ))
                }
            }
        }
        produce {
            copy(
                selectedPiece = Pair(i, j),
                availableMoves = list,
            )
        }
    }

    fun moveTo(i: Int, j: Int) {
        _state.value.selectedPiece?.let { piece ->
            val startPiece = board.getPiece(piece.first, piece.second)
            if (startPiece.canMoveToPosition(board, piece.first, piece.second, i, j)) {
                board.move(startPiece, piece.first, piece.second, i, j)
                updateState()
            } else if (startPiece is King && board.getPiece(i, j) is Rook) {
                startPiece.performCastle(board, i, j)
                updateState()
            }
        }
    }

    private fun updateState() {
        produce {
            copy(
                board = board,
                selectedPiece = null,
                availableMoves = emptyList(),
                move = if (_state.value.move == ChessColor.WHITE) ChessColor.BLACK else ChessColor.WHITE
            )
        }
    }

    fun unselectPiece() {
        produce { copy(selectedPiece = null, availableMoves = emptyList()) }
    }

    private fun produce(producer: ChessState.() -> ChessState) {
        scope.launch {
            _state.emit(_state.value.producer())
        }
    }
}

data class ChessState(
    val board: ChessBoard,
    val move: ChessColor = ChessColor.WHITE,
    val selectedPiece: Pair<Int, Int>? = null,
    val availableMoves: List<Pair<Int, Int>> = emptyList(),
)