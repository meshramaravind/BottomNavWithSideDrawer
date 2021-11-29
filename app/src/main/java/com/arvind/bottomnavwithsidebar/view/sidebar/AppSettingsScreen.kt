package com.arvind.bottomnavwithsidebar.view.sidebar

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.arvind.bottomnavwithsidebar.navigation.Screens
import com.arvind.bottomnavwithsidebar.viewmodel.MainViewModel

@Composable
fun AppSettingsScreen(
    modifier: Modifier = Modifier,
    viewModel: MainViewModel
    ) {
    viewModel.setCurrentScreen(Screens.DrawerScreens.AppSettings)
    Column(
        modifier = modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "App Settings.", style = MaterialTheme.typography.h4)
    }
}