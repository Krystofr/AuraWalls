package app.chris.aurawalls.screens.home

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import app.chris.aurawalls.ui.theme.RalewayMedium
import app.chris.aurawalls.ui.theme.White
import app.chris.aurawalls.util.NavBar

@Composable
fun CategoriesRow(
    onAllItemClick: () -> Unit,
    onItemClick: (String) -> Unit
) {
    LazyRow(
        state = rememberLazyListState(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center,
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
    ) {
        item {
            CategoryItem(onItemClick = onItemClick)
            Row(
                modifier = Modifier
                    .wrapContentSize()
                    .clip(shape = CircleShape)
                    .clickable { onAllItemClick() }
                    .padding(5.dp),
                horizontalArrangement = Arrangement.spacedBy(5.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "view all",
                    color = White,
                    fontFamily = RalewayMedium,
                    fontSize = 13.sp,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.wrapContentSize()
                )
                NavBar(icon = Icons.Filled.ArrowForward, onClick = onAllItemClick)
            }
            Spacer(modifier = Modifier.width(15.dp))
        }

    }

}