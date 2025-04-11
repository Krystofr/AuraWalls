package app.chris.aurawalls

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.compose.rememberNavController
import app.chris.aurawalls.navigation.AuraWallsNavigation
import app.chris.aurawalls.navigation.bottom_nav.BottomNavigationBar
import app.chris.aurawalls.ui.theme.AuraWallsTheme
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AuraWallsTheme {
                BottomNavigationBar()
            }
        }
    }
}