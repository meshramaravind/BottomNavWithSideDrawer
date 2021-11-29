package com.arvind.bottomnavwithsidebar.navigation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Person
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import com.arvind.bottomnavwithsidebar.R
import com.arvind.bottomnavwithsidebar.viewmodel.MainViewModel

sealed class Screens(val route: String, val title: String) {

    sealed class HomeScreens(
        route: String,
        title: String,
        val icon: ImageVector
    ) : Screens(
        route,
        title
    ) {
        object Favorite : HomeScreens("favorite", "Favorite", Icons.Filled.Favorite)
        object NearBy : HomeScreens("nearby", "Nearby", Icons.Filled.Notifications)
        object Reserved : HomeScreens("reserved", "Reserved", Icons.Filled.Person)
        object Saved : HomeScreens("saved", "Saved", Icons.Filled.Person)

    }

    sealed class DrawerScreens(
        route: String,
        val icon: Int,
        title: String
    ) : Screens(route, title) {
        object Home : DrawerScreens("home", R.drawable.ic_baseline_home_24, "Home")
        object MyProfile :
            DrawerScreens("my_profile", R.drawable.ic_baseline_person_24, "My Profile")

        object MyReviews :
            DrawerScreens("my_reviews", R.drawable.ic_baseline_rate_review_24, "My Reviews")

        object VisitsHistory :
            DrawerScreens("visit_history", R.drawable.ic_baseline_location_on_24, "Visits History")

        object Notifications :
            DrawerScreens("notifications", R.drawable.ic_baseline_notifications_24, "Notifications")

        object AppSettings :
            DrawerScreens("settings", R.drawable.ic_baseline_settings_24, "Settings")

        object QRCode : DrawerScreens("qr-code", R.drawable.ic_baseline_qr_code_24, "QR Code")

        object Help : DrawerScreens("help", R.drawable.ic_baseline_help_24, "Help")

        object AboutUs : DrawerScreens("about_us", R.drawable.ic_baseline_info_24, "About Us")

        object Logout : DrawerScreens("logout", R.drawable.ic_baseline_logout_24, "Logout")

    }
}

val screensInHomeFromBottomNav = listOf(
    Screens.HomeScreens.Favorite,
    Screens.HomeScreens.NearBy,
    Screens.HomeScreens.Reserved,
    Screens.HomeScreens.Saved
)

val screensFromDrawer = listOf(
    Screens.DrawerScreens.Home,
    Screens.DrawerScreens.MyProfile,
    Screens.DrawerScreens.MyReviews,
    Screens.DrawerScreens.VisitsHistory,
    Screens.DrawerScreens.Notifications,
    Screens.DrawerScreens.AppSettings,
    Screens.DrawerScreens.QRCode,
    Screens.DrawerScreens.Help,
    Screens.DrawerScreens.AboutUs,
    Screens.DrawerScreens.Logout
)


