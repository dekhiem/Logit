package vn.khiemvn.logit.core

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import dev.chrisbanes.haze.HazeState
import dev.chrisbanes.haze.hazeSource

@Composable
fun LogitNavHost(
    navController: NavHostController,
    innerPadding: PaddingValues,
    modifier: Modifier = Modifier,
    hazeState: HazeState
) {
    NavHost(
        navController = navController,
        startDestination = LogDestination,
        // set this as HazeSource
        modifier = modifier
            .padding(innerPadding)
            .hazeSource(state = hazeState)
    ) {
        composable<ViewDestination> {
            Text("Home Screen")
        }
        composable<AssetsDestination> {
            Text("Assets Screen")
        }
        composable<LogDestination> {
            Text("Create Screen")
        }
        composable<ChartDestination> {
            Text("Report Screen")
        }
        composable<MiscDestination> {
            Text("Misc Screen")
        }
    }
}
