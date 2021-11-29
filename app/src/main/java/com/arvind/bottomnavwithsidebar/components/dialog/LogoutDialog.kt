package com.arvind.bottomnavwithsidebar.components.dialog

import androidx.compose.material.AlertDialog
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import com.arvind.bottomnavwithsidebar.R
import com.arvind.bottomnavwithsidebar.ui.theme.*
import com.arvind.bottomnavwithsidebar.ui.theme.dark_gray


@Composable
fun LogoutDialog() {
    val context = LocalContext.current
    val openDialog = remember { mutableStateOf(true) }

    if (openDialog.value) {
        AlertDialog(
            onDismissRequest = { openDialog.value = false },

            title = { Text(text = stringResource(id = R.string.logout), color = dark_gray) },
            text = { Text(stringResource(id = R.string.wouldyouliketologout), color = dark_gray) },
            confirmButton = {
                TextButton(
                    onClick = {
                        openDialog.value = false
                    }
                ) {

                    Text(stringResource(id = R.string.logoutok), color = Purple500)
                }
            },
            dismissButton = {
                TextButton(
                    onClick = {
                        openDialog.value = false

                    }
                ) {
                    Text(stringResource(id = R.string.cancel), color = ColorGray)
                }
            },
            backgroundColor = ColorWhite,
            contentColor = dark_gray
        )
    }
}