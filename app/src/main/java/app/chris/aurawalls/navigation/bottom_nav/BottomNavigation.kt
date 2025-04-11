package app.chris.aurawalls.navigation.bottom_nav

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Menu
import androidx.compose.material.icons.rounded.Search
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import app.chris.aurawalls.R
import app.chris.aurawalls.navigation.AuraWallsNavigation
import app.chris.aurawalls.navigation.NavRoutes
import app.chris.aurawalls.ui.theme.Background
import app.chris.aurawalls.ui.theme.BackgroundDarker
import app.chris.aurawalls.ui.theme.BackgroundLight
import app.chris.aurawalls.ui.theme.MontserratMedium
import app.chris.aurawalls.ui.theme.Pink
import app.chris.aurawalls.ui.theme.Transparent

@Composable
fun BottomNavigationBar() {
    val navController = rememberNavController()
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route

    Scaffold(
        bottomBar = {
            AnimatedVisibility(
                visible = currentRoute in listOf(
                    NavRoutes.HomeScreen.route,
                    NavRoutes.SearchScreen.route,
                    NavRoutes.VideosScreen.route
                ),
                enter = slideInVertically(initialOffsetY = { it }) + fadeIn(
                    animationSpec = tween(
                        durationMillis = 300
                    )
                ),
                exit = slideOutVertically(targetOffsetY = { it }) + fadeOut(
                    animationSpec = tween(
                        durationMillis = 300
                    )
                )
                //enter = fadeIn(animationSpec = tween(durationMillis = 300)),
                //exit = fadeOut(animationSpec = tween(durationMillis = 300))
            ) {
                BottomNavigation(
                    navController = navController,
                    currentRoute = currentRoute
                )
            }
        }
    ) { paddingValues ->
        AuraWallsNavigation(navController, modifier = Modifier.padding(paddingValues))
    }
}

@Composable
fun RowScope.BottomNavItems(
    modifier: Modifier = Modifier,
    label: @Composable (() -> Unit),
    enabled: Boolean,
    isSelected: Boolean,
    selectedContentColor: Color,
    unselectedContentColor: Color,
    alwaysShowLabel: Boolean,
    onItemClick: () -> Unit,
    icon: @Composable (() -> Unit)
) {

    BottomNavigationItem(modifier = modifier,
        enabled = enabled,
        label = label,
        selected = isSelected,
        selectedContentColor = selectedContentColor,
        unselectedContentColor = unselectedContentColor,
        alwaysShowLabel = alwaysShowLabel,
        onClick = { onItemClick() },
        icon = { icon() })
}

@Composable
fun BottomNavigation(navController: NavController, currentRoute: String?) {
    BottomNavigation(
        elevation = 50.dp,
        backgroundColor = Background,
        modifier = Modifier
            .fillMaxWidth()
            .height(50.dp)
            .clip(shape = RoundedCornerShape(topStart = 10.dp, topEnd = 10.dp))
    ) {
        //Home Screen
        BottomNavItems(
            label = {
                Text(
                    text = "Walls", fontSize = 10.sp, fontFamily = MontserratMedium
                )
            },
            enabled = true,
            isSelected = currentRoute == NavRoutes.HomeScreen.route,
            selectedContentColor = Pink,
            unselectedContentColor = BackgroundLight,
            alwaysShowLabel = true,
            onItemClick = {
                navController.navigate(NavRoutes.HomeScreen.route) {
                    popUpTo(NavRoutes.HomeScreen.route) {
                        saveState = true
                        //inclusive = true
                    }
                    launchSingleTop = true
                    restoreState = true
                }
            },
            icon = {
                Image(
                    painter = painterResource(id = R.drawable.app_icon_round),
                    contentDescription = "Home screen",
                    modifier = Modifier
                        .padding(5.dp)
                        .size(25.dp)
                )
            }
        )

        //Search Screen
        BottomNavItems(
            label = {
                Text(
                    text = "Search", fontSize = 10.sp, fontFamily = MontserratMedium
                )
            },
            enabled = true,
            isSelected = currentRoute == NavRoutes.SearchScreen.route,
            selectedContentColor = Pink,
            unselectedContentColor = BackgroundLight,
            alwaysShowLabel = true,
            onItemClick = {
                navController.navigate(NavRoutes.SearchScreen.route) {
                    popUpTo(NavRoutes.HomeScreen.route) {
                        saveState = true
                        //inclusive = true
                    }
                    launchSingleTop = true
                    restoreState = true
                }
            },
            icon = {
                Icon(
                    imageVector = Icons.Rounded.Search,
                    contentDescription = "SearchSection",
                    modifier = Modifier
                        .padding(5.dp)
                        .size(25.dp)
                )
            }
        )

        //Videos Screen
        BottomNavItems(
            label = {
                Text(
                    text = "Videos", fontSize = 10.sp, fontFamily = MontserratMedium
                )
            },
            enabled = true,
            isSelected = currentRoute == NavRoutes.VideosScreen.route,
            selectedContentColor = Pink,
            unselectedContentColor = BackgroundLight,
            alwaysShowLabel = true,
            onItemClick = {
                navController.navigate(NavRoutes.VideosScreen.route) {
                    popUpTo(NavRoutes.HomeScreen.route) {
                        saveState = true
                        //inclusive = true
                    }
                    launchSingleTop = true
                    restoreState = true
                }
            },
            icon = {
                Icon(
                    imageVector = Icons.Rounded.Menu,
                    contentDescription = "AccountSection",
                    modifier = Modifier
                        .padding(5.dp)
                        .size(25.dp)
                )
            }
        )
    }
}