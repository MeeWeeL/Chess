package presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import imagePath

@Composable
fun Screen(
    state: ChessState,
    service: StateService,
) {
    Row(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color.Black),
    ) {
        (0..7).forEach { j ->
            Column(modifier = Modifier.weight(1f)) {
                (0..7).forEach { i ->
                    Row(modifier = Modifier.weight(1f)) {
                        Box(
                            modifier = Modifier
                                .fillMaxSize()
                                .padding(2.dp)
                                .background(
                                    color = when {
                                        state.selectedPiece?.first == i && state.selectedPiece.second == j -> Color.Magenta.copy(
                                            alpha = 0.7f
                                        )

                                        state.availableMoves.contains(Pair(i, j)) -> Color.Green.copy(alpha = 0.5f)
                                        (i + j) % 2 == 0 -> Color.White.copy(alpha = 0.5f)
                                        else -> Color.Yellow.copy(alpha = 0.7f)
                                    }
                                )
                                .clickable(
                                    onClick = {
                                        val selectedPiece = state.selectedPiece
                                        if (selectedPiece != null) {
                                            if (selectedPiece.first == i && selectedPiece.second == j) {
                                                service.unselectPiece()
                                            } else if (state.availableMoves.contains(Pair(i, j))) {
                                                service.moveTo(i, j)
                                            }
                                        } else {
                                            service.selectPiece(i, j)
                                        }
                                    }
                                )
                                .padding(16.dp),
                            contentAlignment = Alignment.Center,
                        ) {
                            state.board.getPiece(i, j)?.let { piece ->
                                Icon(
                                    painter = painterResource(piece.imagePath()),
                                    contentDescription = null,
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}