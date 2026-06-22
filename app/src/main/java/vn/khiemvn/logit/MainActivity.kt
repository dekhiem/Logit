package vn.khiemvn.logit

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavDestination.Companion.hasRoute
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import dev.chrisbanes.haze.blur.blurEffect
import dev.chrisbanes.haze.blur.materials.HazeMaterials
import dev.chrisbanes.haze.hazeEffect
import dev.chrisbanes.haze.rememberHazeState
import vn.khiemvn.logit.core.AccountDestination
import vn.khiemvn.logit.core.CreateDestination
import vn.khiemvn.logit.core.HomeDestination
import vn.khiemvn.logit.core.LogitNavHost
import vn.khiemvn.logit.core.ReportDestination
import vn.khiemvn.logit.core.SettingsDestination
import vn.khiemvn.logit.core.theme.LogitTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            LogitTheme {
                LogitApp()
            }
        }
    }
}

@Composable
fun LogitApp() {
    val navController = rememberNavController()
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination

    // Haze state
    val hazeState = rememberHazeState()
    // for which style to choose: https://chrisbanes.github.io/haze/latest/blur/materials/
    val hazeStyle = HazeMaterials.regular()

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        bottomBar = {
            NavigationBar(
                // register as HazeEffect with defined style
                modifier = Modifier.hazeEffect(state=hazeState) {
                    blurEffect {
                        style = hazeStyle
                    }
                },
                // let the blur go through
                containerColor = Color.Transparent
            ) {
                AppDestinations.entries.forEach { appDestination ->
                    val isSelected = currentDestination?.hierarchy?.any {
                        it.hasRoute(appDestination.route::class)
                    } == true

                    NavigationBarItem(
                        selected = isSelected,
                        onClick = {
                            navController.navigate(appDestination.route) {
                                // pop up to the start destination to avoid a large stack of dest when press back
                                popUpTo(navController.graph.findStartDestination().id) {
                                    saveState = true
                                }
                                // don't invoke the same dest when presses the same item
                                launchSingleTop = true
                                // restore state when reselecting a previously selected item
                                restoreState = true
                            }
                        },
                        icon = {
                            Icon(
                                painter = painterResource(
                                    if (isSelected) appDestination.iconSelected else appDestination.icon
                                ),
                                contentDescription = appDestination.label,
                            )
                        },
                        label = {
                            Text(
                                text = appDestination.label,
                                fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Normal,
                                style = MaterialTheme.typography.labelSmall
                            )
                        },
                        colors = NavigationBarItemDefaults.colors(
                            indicatorColor = Color.Transparent,
                            selectedIconColor = MaterialTheme.colorScheme.primary,
                            unselectedIconColor = MaterialTheme.colorScheme.onSurfaceVariant,
                            selectedTextColor = MaterialTheme.colorScheme.primary,
                            unselectedTextColor = MaterialTheme.colorScheme.onSurfaceVariant
                        )
                    )
                }
            }
        }
    ) { innerPadding ->
        LogitNavHost(
            navController = navController,
            innerPadding = innerPadding,
            // pass the hazeState to master so that all child node can use it
            hazeState = hazeState
        )
    }
}

enum class AppDestinations(
    val label: String,
    val icon: Int,
    val iconSelected: Int,
    val route: Any
) {
    HOME("Tổng quan", R.drawable.ic_home, R.drawable.ic_home_selected, HomeDestination),
    ACCOUNT("Tài khoản", R.drawable.ic_account, R.drawable.ic_account_selected, AccountDestination),
    CREATE("", R.drawable.ic_create, R.drawable.ic_create_selected, CreateDestination),
    REPORT("Báo cáo", R.drawable.ic_report, R.drawable.ic_report_selected, ReportDestination),
    SETTINGS("Cài đặt", R.drawable.ic_settings, R.drawable.ic_settings_selected, SettingsDestination)
}
