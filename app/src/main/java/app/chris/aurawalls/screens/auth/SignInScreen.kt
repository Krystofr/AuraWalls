package app.chris.aurawalls.screens.auth

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Icon
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import app.chris.aurawalls.R
import app.chris.aurawalls.navigation.NavRoutes
import app.chris.aurawalls.room.EmailTable
import app.chris.aurawalls.screens.account.ProfileViewModel
import app.chris.aurawalls.ui.theme.Background
import app.chris.aurawalls.ui.theme.BackgroundDarker
import app.chris.aurawalls.ui.theme.DarkBackgroundLight
import app.chris.aurawalls.ui.theme.LightGray
import app.chris.aurawalls.ui.theme.MontserratAlternatesMedium
import app.chris.aurawalls.ui.theme.Pink
import app.chris.aurawalls.ui.theme.RalewayBold
import app.chris.aurawalls.ui.theme.RalewayMedium
import app.chris.aurawalls.ui.theme.Transparent
import app.chris.aurawalls.ui.theme.White
import app.chris.aurawalls.util.LightItemsStatusLightNavBars

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun SignInScreen(navController: NavController) {
    val viewModel : ProfileViewModel = hiltViewModel()
    val context = LocalContext.current
    val email = remember {
        mutableStateOf("")
    }
    val keyboardController = LocalSoftwareKeyboardController.current
    val focusManager = LocalFocusManager.current
    var isTextFieldFocused by remember { mutableStateOf(false) }
    val focusRequester = remember { FocusRequester() }
    val interactionSource = remember {
        MutableInteractionSource()
    }

    LightItemsStatusLightNavBars(context)

    Surface(
        modifier = Modifier
            .fillMaxSize()
            .clickable(
                interactionSource = interactionSource,
                indication = null
            ) {
                keyboardController?.hide()
                focusManager.clearFocus()
            }, color = Background
    ) {
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
                    .fillMaxHeight(0.45f)
                    .fillMaxWidth()
                    .background(color = DarkBackgroundLight),
                verticalArrangement = Arrangement.spacedBy(40.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                Text(
                    text = "Sign-in with,",
                    color = White,
                    fontSize = 20.sp,
                    fontFamily = RalewayMedium,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 15.dp, top = 10.dp),
                    textAlign = TextAlign.Start
                )
                TextField(value = email.value,
                    onValueChange = {
                        email.value = it
                    },
                    singleLine = true,
                    placeholder = {
                        Text(
                            text = "mary.poppins@gmail.com",
                            fontSize = 13.sp,
                            color = LightGray,
                            fontFamily = RalewayMedium
                        )
                    },
                    label = {
                        Text(
                            text = "Email address",
                            fontSize = 13.sp,
                            color = LightGray,
                            fontFamily = RalewayMedium
                        )
                    },
                    leadingIcon = {
                        Icon(
                            imageVector = Icons.Filled.Email,
                            contentDescription = null,
                            tint = LightGray,
                            modifier = Modifier.size(25.dp)
                        )
                    },
                    colors = TextFieldDefaults.textFieldColors(
                        focusedIndicatorColor = Transparent,
                        unfocusedIndicatorColor = Transparent,
                        textColor = White,
                        backgroundColor = BackgroundDarker,
                        cursorColor = LightGray
                    ),
                    visualTransformation = VisualTransformation.None,
                    modifier = Modifier
                        .fillMaxWidth(0.86f)
                        .height(55.dp)
                        .focusRequester(focusRequester)
                        .onFocusChanged {
                            isTextFieldFocused = it.isFocused
                        }
                        .clip(shape = RoundedCornerShape(16.dp)),
                    textStyle = TextStyle(fontSize = 14.sp, fontFamily = RalewayMedium),
                    keyboardActions = KeyboardActions(
                        onDone = {
                            keyboardController?.hide()
                            focusManager.clearFocus()
                        }
                    ),
                    keyboardOptions = KeyboardOptions(
                        imeAction = ImeAction.Done,
                        keyboardType = KeyboardType.Email
                    )
                )
                Button(
                    onClick = {
                        if (email.value.isNotEmpty()) {
                            val userEmail = EmailTable(email = email.value)
                            viewModel.saveEmail(email = userEmail)
                            navController.navigate(NavRoutes.HomeScreen.route) {
                                popUpTo(NavRoutes.HomeScreen.route) {
                                    inclusive = true
                                }
                            }
                        } else if (email.value.isEmpty()) {
                            Toast.makeText(context, "Enter a valid email above", Toast.LENGTH_LONG)
                                .show()
                        }
                    },
                    colors = ButtonDefaults.buttonColors(backgroundColor = Pink),
                    modifier = Modifier
                        .fillMaxWidth(0.86f)
                        .height(55.dp),
                    shape = CircleShape
                ) {
                    Text(
                        text = "Sign in",
                        color = White,
                        fontSize = 16.sp,
                        fontFamily = RalewayBold
                    )
                }
                TextButton(
                    onClick = {
                        navController.navigate(NavRoutes.HomeScreen.route) {
                            popUpTo(NavRoutes.HomeScreen.route) {
                                inclusive = true
                            }
                        }
                    },
                    colors = androidx.compose.material3.ButtonDefaults.textButtonColors(
                        containerColor = Background
                    ),
                    modifier = Modifier
                        .fillMaxWidth(0.7f)
                        .height(55.dp),
                    shape = CircleShape,
                    elevation = androidx.compose.material3.ButtonDefaults.elevatedButtonElevation(
                        defaultElevation = 1.dp,
                        pressedElevation = 6.dp
                    )
                )
                {
                    Row(
                        horizontalArrangement = Arrangement.Center,
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.google_icon),
                            contentDescription = stringResource(
                                R.string.google_icon_content_desc
                            ),
                            modifier = Modifier.size(25.dp)
                        )
                        Spacer(modifier = Modifier.width(15.dp))
                        Text(
                            text = stringResource(R.string.signin_with_google_btn_text),
                            fontFamily = MontserratAlternatesMedium,
                            color = White,
                            fontSize = 15.sp
                        )
                    }
                }
            }
        }
    }
}