package com.arvind.bottomnavwithsidebar

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Menu
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.arvind.bottomnavwithsidebar.components.BottomBar
import com.arvind.bottomnavwithsidebar.components.Drawer
import com.arvind.bottomnavwithsidebar.components.TopBar
import com.arvind.bottomnavwithsidebar.components.dialog.LogoutDialog
import com.arvind.bottomnavwithsidebar.navigation.*
import com.arvind.bottomnavwithsidebar.ui.theme.BottomNavWithSideBarTheme
import com.arvind.bottomnavwithsidebar.utils.BackPressHandler
import com.arvind.bottomnavwithsidebar.utils.LocalBackPressedDispatcher
import com.arvind.bottomnavwithsidebar.view.bottom.FavoriteScreen
import com.arvind.bottomnavwithsidebar.view.bottom.NearbyScreen
import com.arvind.bottomnavwithsidebar.view.bottom.ReservedScreen
import com.arvind.bottomnavwithsidebar.view.bottom.SavedScreen
import com.arvind.bottomnavwithsidebar.view.sidebar.*
import com.arvind.bottomnavwithsidebar.viewmodel.MainViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            BottomNavWithSideBarTheme {
                // A surface container using the 'background' color from the theme
                CompositionLocalProvider(LocalBackPressedDispatcher provides this.onBackPressedDispatcher) {
                    AppScaffold()
                }
            }
        }
    }

    @Composable
    fun AppScaffold() {
        val viewModel: MainViewModel = viewModel()
        val navController = rememberNavController()
        val scaffoldState = rememberScaffoldState()
        val scope = rememberCoroutineScope()
        val currentScreen by viewModel.currentScreen.observeAsState()

        if (scaffoldState.drawerState.isOpen) {
            BackPressHandler {
                scope.launch {
                    scaffoldState.drawerState.close()
                }
            }
        }

        var topBar: @Composable () -> Unit = {
            TopBar(
                title = currentScreen!!.title,
                buttonIcon = Icons.Filled.Menu,
                onButtonClicked = {
                    scope.launch {
                        scaffoldState.drawerState.open()
                    }
                }
            )
        }
        if (currentScreen == Screens.DrawerScreens.QRCode) {
            topBar = {
                TopBar(
                    title = Screens.DrawerScreens.MyProfile.title,
                    buttonIcon = Icons.Filled.ArrowBack,
                    onButtonClicked = {
                        navController.popBackStack()
                    }
                )
            }
        }

        val bottomBar: @Composable () -> Unit = {
            if (currentScreen == Screens.DrawerScreens.Home || currentScreen is Screens.HomeScreens) {
                BottomBar(
                    navController = navController,
                    screens = screensInHomeFromBottomNav
                )
            }
        }

        Scaffold(
            topBar = {
                topBar()
            },
            bottomBar = {
                bottomBar()
            },
            scaffoldState = scaffoldState,
            drawerContent = {
                Drawer { route ->
                    scope.launch {
                        scaffoldState.drawerState.close()
                    }
                    navController.navigate(route) {
                        popUpTo = navController.graph.startDestinationId
                        launchSingleTop = true
                    }
                }
            },
            drawerGesturesEnabled = scaffoldState.drawerState.isOpen,
        ) { innerPadding ->
            NavigationHost(navController = navController, viewModel = viewModel)
        }
    }

    @Composable
    fun NavigationHost(navController: NavController, viewModel: MainViewModel) {
        NavHost(
            navController = navController as NavHostController,
            startDestination = Screens.DrawerScreens.Home.route
        ) {
            composable(Screens.DrawerScreens.Home.route) { HomeScreen(viewModel = viewModel) }
            composable(Screens.HomeScreens.Favorite.route) { FavoriteScreen(viewModel = viewModel) }
            composable(Screens.HomeScreens.NearBy.route) { NearbyScreen(viewModel = viewModel) }
            composable(Screens.HomeScreens.Reserved.route) { ReservedScreen(viewModel = viewModel) }
            composable(Screens.HomeScreens.Saved.route) { SavedScreen(viewModel = viewModel) }
            composable(Screens.DrawerScreens.MyProfile.route) { MyProfileScreen(viewModel = viewModel) }
            composable(Screens.DrawerScreens.MyReviews.route) { MyReviewsScreen(viewModel = viewModel) }
            composable(Screens.DrawerScreens.VisitsHistory.route) { VisitHistoryScreen(viewModel = viewModel) }
            composable(Screens.DrawerScreens.Notifications.route) { NotificationsScreen(viewModel = viewModel) }
            composable(Screens.DrawerScreens.AppSettings.route) { AppSettingsScreen(viewModel = viewModel) }
            composable(Screens.DrawerScreens.QRCode.route) { QRCodeScreen(viewModel = viewModel) }
            composable(Screens.DrawerScreens.Help.route) { HelpScreen(viewModel = viewModel) }
            composable(Screens.DrawerScreens.AboutUs.route) { AboutUsScreen(viewModel = viewModel) }
            composable(Screens.DrawerScreens.Logout.route) { LogoutDialog() }

        }
    }

    @Preview(showBackground = true)
    @Composable
    fun DefaultPreview() {
        BottomNavWithSideBarTheme {
            AppScaffold()
        }
    }
}

