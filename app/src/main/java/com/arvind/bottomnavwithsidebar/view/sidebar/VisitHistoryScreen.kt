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
fun VisitHistoryScreen(
    modifier: Modifier = Modifier,
    viewModel: MainViewModel
) {
    viewModel.setCurrentScreen(Screens.DrawerScreens.VisitsHistory)
    Column(
        modifier = modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "Visit History.", style = MaterialTheme.typography.h4)
    }
}