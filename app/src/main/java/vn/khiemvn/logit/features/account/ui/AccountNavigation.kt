package vn.khiemvn.logit.features.account.ui

import androidx.compose.material3.Text
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import vn.khiemvn.logit.core.AccountDestination
import vn.khiemvn.logit.core.TransactionDestination

fun NavController.navigateToAccount(navOptions: NavOptions? = null) {
    this.navigate(AccountDestination, navOptions)
}

fun NavGraphBuilder.accountGraph(
    navController: NavController
) {
    composable<AccountDestination> {
        // Here we would call the Account Screen composable
        Text("Account Screen")
    }
    composable<TransactionDestination> { backStackEntry ->
        // val destination = backStackEntry.toRoute<TransactionDestination>()
        Text("Transaction Screen for account: TODO")
    }
}
