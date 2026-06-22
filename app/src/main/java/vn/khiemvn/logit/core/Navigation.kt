package vn.khiemvn.logit.core

import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectDragGestures
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import dev.chrisbanes.haze.HazeState
import dev.chrisbanes.haze.hazeSource
import vn.khiemvn.logit.features.account.ui.accountGraph
import kotlin.math.roundToInt

@Composable
fun LogitNavHost(
    navController: NavHostController,
    innerPadding: PaddingValues,
    modifier: Modifier = Modifier,
    hazeState: HazeState
) {
    NavHost(
        navController = navController,
        startDestination = HomeDestination,
        // set this as HazeSource
        modifier = modifier.padding(innerPadding).hazeSource(state = hazeState)
    ) {
        composable<HomeDestination> {
            Text("Home Screen")
        }
        accountGraph(navController)
        composable<CreateDestination> {
            Text("Create Screen")
        }
        composable<ReportDestination> {
            Text("Report Screen")
        }
        composable<SettingsDestination> {
            DraggableCircleScreen()
        }
    }
}

@Composable
fun DraggableCircleScreen() {
    var offsetX by remember { mutableStateOf(0f) }
    var offsetY by remember { mutableStateOf(0f) }

    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Box(
            Modifier
                .offset { IntOffset(offsetX.roundToInt(), offsetY.roundToInt()) }
                .size(100.dp)
                .background(Color.Blue.copy(alpha = 0.7f), CircleShape)
                .pointerInput(Unit) {
                    detectDragGestures { change, dragAmount ->
                        change.consume()
                        offsetX += dragAmount.x
                        offsetY += dragAmount.y
                    }
                }
        )
        Text(
            text = "Kéo hình tròn này để test độ trong suốt của NavBar",
            modifier = Modifier.align(Alignment.TopCenter).padding(top = 24.dp),
            style = MaterialTheme.typography.bodyMedium
        )
    }
}
