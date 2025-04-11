package app.chris.aurawalls.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import app.chris.aurawalls.R
import app.chris.aurawalls.navigation.NavRoutes
import app.chris.aurawalls.ui.theme.Background
import app.chris.aurawalls.ui.theme.DarkBackgroundLight
import app.chris.aurawalls.ui.theme.Pink
import app.chris.aurawalls.ui.theme.RalewayBold
import app.chris.aurawalls.ui.theme.White
import app.chris.aurawalls.util.LightItemsStatusLightNavBars

@Composable
fun LaunchScreen(navController: NavController) {
    val context = LocalContext.current

    LightItemsStatusLightNavBars(context)

    Surface(modifier = Modifier.fillMaxSize(), color = Background) {
        Box(modifier = Modifier.fillMaxSize()) {
            Image(
                painter = painterResource(id = R.drawable.wall2),
                contentDescription = "Wallpaper",
                modifier = Modifier.fillMaxSize(),
                contentScale = ContentScale.FillBounds
            )
        }
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.BottomCenter
        ) {
            Column(
                Modifier
                    .fillMaxHeight(0.35f)
                    .fillMaxWidth()
                    .background(color = DarkBackgroundLight),
                verticalArrangement = Arrangement.spacedBy(40.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                Text(
                    text = "Browse a plethora of 4k wallpapers crafted by top designers.",
                    fontSize = 22.sp,
                    color = White,
                    fontFamily = RalewayBold,
                    textAlign = TextAlign.Start,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(30.dp)
                )
                Button(
                    onClick = {
                        navController.navigate(NavRoutes.SignInScreen.route)
                    },
                    colors = ButtonDefaults.buttonColors(backgroundColor = Pink),
                    modifier = Modifier
                        .fillMaxWidth(0.86f)
                        .height(55.dp),
                    shape = CircleShape
                ) {
                    Text(
                        text = "Get Started",
                        color = White,
                        fontSize = 13.sp,
                        fontFamily = RalewayBold
                    )
                }
            }
        }
    }
}