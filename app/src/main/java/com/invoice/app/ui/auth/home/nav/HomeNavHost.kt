package com.invoice.app.ui.auth.home.nav

import android.content.res.Configuration
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.invoice.app.ui.AppScreen
import com.example.invoiceapp.ui.auth.AuthViewModel
import com.invoice.app.ui.auth.drawer.Drawer
import com.invoice.app.ui.auth.home.TopBar
import com.invoice.app.ui.auth.home.dashboard.DashboardScreen
import com.invoice.app.ui.theme.AppTheme
import com.invoice.app.ui.utils.startNewActivity
import com.invoice.app.ui.auth.AuthActivity
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeNavHost() {
    val context = LocalContext.current
    val title = remember { mutableStateOf(AppScreen.Dashboard.title) }
    val navController = rememberNavController()

    val viewModel: AuthViewModel = hiltViewModel()

    Surface(color = MaterialTheme.colorScheme.background) {
        val drawerState = rememberDrawerState(DrawerValue.Closed)
        val scope = rememberCoroutineScope()

        ModalNavigationDrawer(
            drawerState = drawerState,
            gesturesEnabled = drawerState.isOpen,
            drawerContent = {
                Drawer(
                    navController = navController,
                    onDestinationClicked = { route ->
                        scope.launch { drawerState.close() }
                        if (route == AppScreen.Logout.route) {
                            viewModel.logout()
                            context.startNewActivity(AuthActivity::class.java)
                        } else {
                            navController.navigate(route) {
                                launchSingleTop = true
                            }
                        }
                    }
                )
            }
        ) {
            TopBar(
                title = title.value,
                onButtonClicked = {
                    scope.launch {
                        drawerState.open()
                    }
                }, content = {
                    NavHost(
                        navController = navController,
                        startDestination = AppScreen.Dashboard.route
                    ) {
                        composable(AppScreen.Dashboard.route) { DashboardScreen(hiltViewModel()) }
                        invoiceNav(navController)
                        taxNav(navController)
                        businessNav(navController)
                        customersNav(navController)
                    }
                }
            )
        }
    }

    LaunchedEffect(navController) {
        navController.currentBackStackEntryFlow.collect { backStackEntry ->
            title.value = backStackEntry.getTitle()
        }
    }
}

@Preview(showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_NO)
@Composable
fun AppMainScreenPreviewLight() {
    AppTheme {
        HomeNavHost()
    }
}

@Preview(showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun AppMainScreenPreviewDark() {
    AppTheme {
        HomeNavHost()
    }
}