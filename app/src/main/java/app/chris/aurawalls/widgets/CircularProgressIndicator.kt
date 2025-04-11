package app.chris.aurawalls.widgets

import androidx.compose.foundation.layout.size
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.unit.dp
import app.chris.aurawalls.ui.theme.Purple
import app.chris.aurawalls.ui.theme.Purple40

@Composable
fun ProgressIndicator() {
    CircularProgressIndicator(strokeWidth = 4.dp,
        trackColor = Purple,
        strokeCap = StrokeCap.Round,
        color = Purple40,
        modifier = Modifier.size(35.dp))
}