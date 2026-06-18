package vn.khiemvn.logit.core

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import vn.khiemvn.logit.features.account.ui.accountGraph

@Composable
fun LogitNavHost(
    navController: NavHostController,
    innerPadding: PaddingValues,
    modifier: Modifier = Modifier
) {
    NavHost(
        navController = navController,
        startDestination = HomeDestination,
        modifier = modifier.padding(innerPadding)
    ) {
        composable<HomeDestination> {
            Text("Home Screen")
        }
        composable<AccountDestination> {
            Text("Account Screen")
        }
        composable<CreateDestination> {
            Text("Create Screen")
        }
        composable<ReportDestination> {
            Text("Report Screen")
        }
        composable<SettingsDestination> {
            Text("Settings Screen")
        }
        accountGraph(navController)
    }
}
