package app.chris.aurawalls.screens.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import app.chris.aurawalls.ui.theme.MontserratAlternatesBold
import app.chris.aurawalls.ui.theme.White
import app.chris.aurawalls.util.Category
import app.chris.aurawalls.util.Constants
import app.chris.aurawalls.widgets.ProgressIndicator

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun CategoryItem(onItemClick: (String) -> Unit) {

    val showLoading = remember {
        mutableStateOf(false)
    }
    val categories = Constants.categories

    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center,
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .padding(start = 15.dp, end = 10.dp)
    ) {

        categories.forEach { category ->
            Card(onClick = {
                onItemClick(category.name)
            },
                backgroundColor = White,
                shape = RoundedCornerShape(15.dp),
                elevation = 5.dp,
                modifier = Modifier
                    .height(150.dp)
                    .width(200.dp)
                    .padding(10.dp),
                content = {
                    Box(
                        modifier = Modifier.fillMaxSize(),
                        contentAlignment = Alignment.BottomStart
                    ) {
                        Contents(
                            category = category,
                            colors = Constants.shadowView,
                            showLoadingIndicator = showLoading
                        )
                    }
                })

        }
    }

}

@Composable
fun Contents(category: Category, colors: List<Color>, showLoadingIndicator: MutableState<Boolean>) {
    val image: Painter = painterResource(id = category.imageResId)

    Image(
        painter = image,
        contentDescription = null,
        contentScale = ContentScale.Crop,
        modifier = Modifier.fillMaxSize()
    )
    if (showLoadingIndicator.value) ProgressIndicator()
    Spacer(
        modifier = Modifier
            .background(
                brush = Brush.verticalGradient(
                    colors = colors,
                    startY = Float.POSITIVE_INFINITY,
                    endY = 0f
                )
            )
            .fillMaxSize()
    )
    Text(
        text = category.name,
        color = White,
        fontSize = 13.sp,
        fontFamily = MontserratAlternatesBold,
        fontWeight = FontWeight.Bold,
        modifier = Modifier
            .fillMaxWidth()
            .padding(17.dp),
        textAlign = TextAlign.Center
    )
}