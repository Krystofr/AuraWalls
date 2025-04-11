package app.chris.aurawalls.widgets

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Icon
import androidx.compose.material.TextButton
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.DialogProperties
import androidx.compose.ui.window.SecureFlagPolicy
import app.chris.aurawalls.R
import app.chris.aurawalls.ui.theme.Background
import app.chris.aurawalls.ui.theme.Black
import app.chris.aurawalls.ui.theme.MontserratAlternatesBold
import app.chris.aurawalls.ui.theme.MontserratMedium
import app.chris.aurawalls.ui.theme.RalewayBold
import app.chris.aurawalls.ui.theme.White

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SetWallpaperPopup(
    onDismissRequest: () -> Unit,
    onHomeScreenClick: () -> Unit,
    onLockScreenClick: () -> Unit,
    onBothScreenClick: () -> Unit,
    title: String,
    subTitle: String,
    icon: ImageVector,
) {

    AlertDialog(
        onDismissRequest = onDismissRequest,
        properties = DialogProperties(
            dismissOnClickOutside = true,
            dismissOnBackPress = true
        ),
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight(0.5f)
    ) {
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight()
                .background(Background, shape = RoundedCornerShape(25.dp))
        ) {
            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier
                    .size(35.dp)
                    .background(color = Background, shape = CircleShape)
            ) {
                Icon(
                    imageVector = icon,
                    contentDescription = "Dropdown icon",
                    Modifier.size(20.dp),
                    tint = White
                )
            }

            Text(
                text = buildAnnotatedString {
                    withStyle(
                        SpanStyle(
                            fontFamily = RalewayBold,
                            fontSize = 18.sp,
                            color = White,
                        )
                    ) {
                        append(title)
                    }
                    withStyle(
                        SpanStyle(
                            fontFamily = MontserratMedium,
                            fontWeight = FontWeight.Bold,
                            fontSize = 17.sp
                        )
                    ) {
                        append(subTitle)
                    }
                },
                modifier = Modifier.fillMaxWidth(),
                textAlign = TextAlign.Center,
                fontSize = 16.sp
            )
            Spacer(modifier = Modifier.height(60.dp))
            Button(
                onClick = {
                    onHomeScreenClick()
                }, shape = RoundedCornerShape(15.dp),
                colors = ButtonDefaults.buttonColors(backgroundColor = White),
                modifier = Modifier
                    .fillMaxWidth(0.6f)
                    .height(50.dp)
            )
            {
                Text(
                    text = stringResource(R.string.set_as_home_screen),
                    fontSize = 13.sp,
                    color = Black,
                    textAlign = TextAlign.Center,
                    fontFamily = MontserratAlternatesBold
                )
            }
            Spacer(modifier = Modifier.height(30.dp))
            TextButton(
                onClick = {
                    onLockScreenClick()
                }, shape = RoundedCornerShape(15.dp),
                colors = ButtonDefaults.buttonColors(backgroundColor = White),
                modifier = Modifier
                    .fillMaxWidth(0.6f)
                    .height(50.dp)
            )
            {
                Text(
                    text = stringResource(R.string.set_as_lock_screen),
                    fontSize = 13.sp,
                    color = Black,
                    textAlign = TextAlign.Center,
                    fontFamily = MontserratAlternatesBold
                )
            }
            Spacer(modifier = Modifier.height(30.dp))
            TextButton(
                onClick = {
                    onBothScreenClick()
                }, shape = RoundedCornerShape(15.dp),
                colors = ButtonDefaults.buttonColors(backgroundColor = White),
                modifier = Modifier
                    .fillMaxWidth(0.9f)
                    .height(50.dp)
            )
            {
                Text(
                    text = stringResource(R.string.set_as_home_and_lock_screen),
                    fontSize = 13.sp,
                    color = Black,
                    textAlign = TextAlign.Center,
                    fontFamily = MontserratAlternatesBold
                )
            }
        }
    }
}

