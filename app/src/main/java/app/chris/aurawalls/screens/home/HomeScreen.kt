package app.chris.aurawalls.screens.home

import android.widget.Toast
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Icon
import androidx.compose.material.Surface
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.outlined.Info
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import app.chris.aurawalls.R
import app.chris.aurawalls.navigation.NavRoutes
import app.chris.aurawalls.ui.theme.LightGray
import app.chris.aurawalls.ui.theme.Purple
import app.chris.aurawalls.ui.theme.RalewayBold
import app.chris.aurawalls.ui.theme.White
import app.chris.aurawalls.util.LightItemsStatusLightNavBars
import app.chris.aurawalls.widgets.BackgroundOverlay
import app.chris.aurawalls.widgets.IconWithBackground
import app.chris.aurawalls.wrapper.Resource

@Composable
fun HomeScreen(navController: NavController, viewModel: HomeScreenViewModel) {

    val context = LocalContext.current
    val photoState by viewModel.photos.collectAsStateWithLifecycle()

    val showProgressIndicator = remember {
        mutableStateOf(false)
    }
    LightItemsStatusLightNavBars(context = context)

    Surface(modifier = Modifier.fillMaxSize()) {
        BackgroundOverlay()
        Column(
            Modifier
                .fillMaxSize()
                .padding(vertical = 50.dp),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Row(
                Modifier
                    .fillMaxWidth()
                    .wrapContentHeight()
                    .padding(end = 10.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.End
            ) {
                /*Image(
                    painter = painterResource(id = R.drawable.logo),
                    contentDescription = "Logo",
                    Modifier
                        .height(50.dp)
                        .width(150.dp),
                    contentScale = ContentScale.Fit
                )*/
                IconButton(
                    onClick = {
                        navController.navigate(NavRoutes.SearchScreen.route)
                    },
                    modifier = Modifier
                        .size(35.dp)
                        .clip(shape = CircleShape)
                ) {
                    Icon(
                        imageVector = Icons.Filled.Search, contentDescription = "Search icon",
                        modifier = Modifier.size(25.dp),
                        tint = White
                    )
                }
                Spacer(modifier = Modifier.width(20.dp))
                IconWithBackground(
                    onClick = { navController.navigate(NavRoutes.AccountScreen.route) },
                    icon = Icons.Filled.Person
                )
            }
            Spacer(modifier = Modifier.height(10.dp))
            Text(
                text = stringResource(R.string.categories),
                fontSize = 17.sp,
                color = White,
                fontFamily = RalewayBold,
                modifier = Modifier
                    .fillMaxWidth(0.86f)
            )
            Spacer(modifier = Modifier.height(10.dp))
            CategoriesRow(onAllItemClick = {
                navController.navigate(NavRoutes.AllCategoriesScreen.route)
            },
                onItemClick = { category ->
                    viewModel.saveSelectedCategory(category)
                    navController.navigate(NavRoutes.WallpaperByCategory.route)
                }
            )
            Spacer(modifier = Modifier.height(20.dp))
            Row(
                Modifier
                    .fillMaxWidth(0.94f)
                    .wrapContentHeight(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Start
            ) {
                Text(
                    text = stringResource(R.string.curated_wallpapers),
                    fontSize = 17.sp,
                    color = White,
                    fontFamily = RalewayBold,
                    modifier = Modifier
                        .wrapContentSize()
                )
                Spacer(modifier = Modifier.width(10.dp))
                Icon(
                    imageVector = Icons.Outlined.Info,
                    contentDescription = "Curated Info",
                    modifier = Modifier
                        .size(15.dp)
                        .clickable { navController.navigate(NavRoutes.VideosScreen.route) },
                    tint = LightGray
                )
            }

            Spacer(modifier = Modifier.height(8.dp))
            if (showProgressIndicator.value) {
                CircularProgressIndicator(
                    strokeWidth = 5.dp,
                    strokeCap = StrokeCap.Round,
                    color = Purple,
                    modifier = Modifier.size(25.dp)
                )
            }
            when (val state = photoState) {
                is Resource.Success -> {
                    state.data?.let { photo ->
                        PhotoList(photos = photo, navController, viewModel)
                    }
                    showProgressIndicator.value = false
                }

                is Resource.Error -> {
                    Toast.makeText(context, state.message, Toast.LENGTH_SHORT).show()
                    showProgressIndicator.value = false
                }

                is Resource.Loading -> {
                    showProgressIndicator.value = true
                }

                is Resource.Idle -> {

                }
            }
        }

    }

}