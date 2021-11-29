package com.arvind.bottomnavwithsidebar.components

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.arvind.bottomnavwithsidebar.R
import com.arvind.bottomnavwithsidebar.navigation.screensFromDrawer
import com.arvind.bottomnavwithsidebar.ui.theme.BottomNavWithSideBarTheme
import com.arvind.bottomnavwithsidebar.ui.theme.ColorPurplePrimary
import com.arvind.bottomnavwithsidebar.ui.theme.Purple500
import com.arvind.bottomnavwithsidebar.ui.theme.Purple700

@Composable
fun Drawer(
    modifier: Modifier = Modifier,
    onDestinationClicked: (route: String) -> Unit
) {
    Box(
        modifier = modifier
            .verticalScroll(rememberScrollState())
    ) {
        Column(
            modifier
                .fillMaxSize()
        ) {

            Row(
                modifier = modifier
                    .fillMaxWidth()
                    .height(200.dp)
                    .background(
                        brush = Brush.horizontalGradient(
                            listOf(
                                Color(0xFF966DE7),
                                Color(0xFF755CD4),
                                Color(0xFF4C48C1)
                            )
                        )
                    ),
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Column(modifier = modifier.padding(15.dp)) {
                    Text(
                        text = "Arvind Meshram",
                        style = MaterialTheme.typography.h5,
                        color = Purple500
                    )
                    Spacer(Modifier.height(4.dp))
                    Text(
                        text = "meshramaravind@gmail.com",
                        style = MaterialTheme.typography.body2,
                        color = Purple500
                    )
                }

                Image(
                    painter = painterResource(R.drawable.ic_baseline_person_pin_24),
                    contentDescription = "avatar",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .size(64.dp)
                        .clip(CircleShape)
                )

            }

            screensFromDrawer.forEach { screen ->
                Spacer(Modifier.height(14.dp))
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable(onClick = { onDestinationClicked(screen.route) })
                        .height(40.dp)
                        .background(color = Color.Transparent)

                ) {
                    Image(
                        painter = painterResource(id = screen.icon),
                        contentDescription = screen.title,
                        contentScale = ContentScale.Fit,
                        modifier = Modifier
                            .height(35.dp)
                            .width(35.dp)
                            .padding(start = 10.dp)
                    )
                    Spacer(modifier = Modifier.width(7.dp))
                    Text(
                        text = screen.title,
                        fontSize = 16.sp,
                        color = Purple700,
                        modifier = Modifier.padding(start = 10.dp)
                    )
                }
            }
        }
    }

}

@Preview
@Composable
fun DrawerPreview() {
    BottomNavWithSideBarTheme {
        Drawer {}
    }
}