package app.chris.aurawalls.screens.home.all_categories

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material.Surface
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import app.chris.aurawalls.R
import app.chris.aurawalls.navigation.NavRoutes
import app.chris.aurawalls.screens.home.HomeScreenViewModel
import app.chris.aurawalls.ui.theme.Background
import app.chris.aurawalls.ui.theme.RalewayBold
import app.chris.aurawalls.ui.theme.White
import app.chris.aurawalls.util.Constants.allCategories
import app.chris.aurawalls.util.LightItemsStatusLightNavBars
import app.chris.aurawalls.util.NavBar

@Composable
fun AllCategoriesScreen(navController: NavController, viewModel: HomeScreenViewModel) {

    val context = LocalContext.current

    LightItemsStatusLightNavBars(context)
    Surface(color = Background, modifier = Modifier.fillMaxSize()) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Top,
            modifier = Modifier
                .fillMaxSize()
                .padding(top = 50.dp)
        ) {
            Row(
                Modifier
                    .fillMaxWidth()
                    .padding(start = 10.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Start
            ) {
                NavBar(icon = Icons.Default.ArrowBack) {
                    navController.popBackStack()
                }
                Spacer(modifier = Modifier.width(10.dp))
                Text(
                    text = stringResource(R.string.all_categories),
                    fontSize = 16.sp,
                    color = White,
                    fontFamily = RalewayBold,
                    modifier = Modifier.wrapContentSize(),
                    textAlign = TextAlign.Start
                )
            }
            Spacer(modifier = Modifier.height(20.dp))
            Column(
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight()
            ) {
                CategoryGrid(categories = allCategories,
                    onItemClick = { category ->
                        viewModel.saveSelectedCategory(category)
                        navController.navigate(NavRoutes.WallpaperByCategory.route)
                    })
            }

        }
    }

}