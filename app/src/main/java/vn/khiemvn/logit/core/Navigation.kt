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
import vn.khiemvn.logit.features.log.logScreen

@Composable
fun LogitNavHost(
    navController: NavHostController,
    innerPadding: PaddingValues,
    modifier: Modifier = Modifier,
    hazeState: HazeState
) {
    NavHost(
        navController = navController,
        startDestination = ViewDestination,
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
        logScreen()
        composable<ChartDestination> {
            Text("Report Screen")
        }
        composable<MiscDestination> {
            Text("Misc Screen")
        }
    }
}
