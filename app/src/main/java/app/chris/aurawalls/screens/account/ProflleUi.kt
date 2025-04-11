package app.chris.aurawalls.screens.account

import android.graphics.Bitmap
import android.net.Uri
import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Icon
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Info
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.asImageBitmap
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
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import app.chris.aurawalls.R
import app.chris.aurawalls.ui.theme.Background
import app.chris.aurawalls.ui.theme.BackgroundDarker
import app.chris.aurawalls.ui.theme.MontserratAlternatesBold
import app.chris.aurawalls.ui.theme.MontserratMedium
import app.chris.aurawalls.ui.theme.RalewayBold
import app.chris.aurawalls.ui.theme.RalewayMedium
import app.chris.aurawalls.ui.theme.Transparent
import app.chris.aurawalls.ui.theme.White
import app.chris.aurawalls.util.LightItemsStatusLightNavBars
import app.chris.aurawalls.util.NavBar
import coil.compose.rememberAsyncImagePainter

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun ProfileUi(navController: NavController, profileViewModel: ProfileViewModel) {

    val context = LocalContext.current
    val profileState by profileViewModel.profileStateFlow.collectAsStateWithLifecycle()
    val emailIdState by profileViewModel.emailStateFlow.collectAsStateWithLifecycle()
    val firstName = rememberSaveable {
        mutableStateOf("")
    }
    val lastName = rememberSaveable {
        mutableStateOf("")
    }
    val email = rememberSaveable {
        mutableStateOf(emailIdState?.email)
    }
    val showBioCheckIcon = rememberSaveable { mutableStateOf(false) }
    val showFirstNameCheckIcon = rememberSaveable { mutableStateOf(false) }
    val showLastNameCheckIcon = rememberSaveable { mutableStateOf(false) }

    val bioText = rememberSaveable { mutableStateOf("") }

    val keyboardController = LocalSoftwareKeyboardController.current
    val focusManager = LocalFocusManager.current
    var isTextFieldFocused by remember { mutableStateOf(false) }
    val focusRequester = remember { FocusRequester() }
    val interactionSource = remember {
        MutableInteractionSource()
    }
    var selectedImageUri by remember { mutableStateOf<Uri?>(null) }
    val selectedImageBitmap by remember { mutableStateOf<Bitmap?>(null) }

    LightItemsStatusLightNavBars(context)

    LaunchedEffect(profileState) {
        profileState?.let {
            selectedImageUri = it.imageUri?.let { uri ->
                Log.d("AccountScreen", "Loaded image URI: $uri")
                Uri.parse(uri)
            }
        }
    }

    Surface(
        modifier = Modifier
            .fillMaxSize()
            .clickable(
                indication = null,
                interactionSource = interactionSource
            ) {
                focusManager.clearFocus()
                keyboardController?.hide()
            },
        color = Background
    ) {
        Column(
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.Start,
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 45.dp)
        ) {

            NavBar(
                icon = Icons.Filled.ArrowBack,
                startPadding = 10.dp,
                onClick = { navController.popBackStack() })
            Spacer(modifier = Modifier.height(20.dp))
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(10.dp),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 35.dp),
            ) {
                Box(
                    modifier = Modifier
                        .clip(shape = CircleShape)
                        .size(90.dp),
                    contentAlignment = Alignment.Center
                ) {
                    if (selectedImageUri != null) {
                        Image(
                            painter = rememberAsyncImagePainter(model = selectedImageUri),
                            contentDescription = null,
                            contentScale = ContentScale.Crop,
                            modifier = Modifier.fillMaxSize()
                        )
                    } else if (selectedImageBitmap != null) {
                        Image(
                            bitmap = selectedImageBitmap!!.asImageBitmap(),
                            contentDescription = null,
                            contentScale = ContentScale.Crop,
                            modifier = Modifier.fillMaxSize()
                        )
                    } else {
                        Image(
                            painter = painterResource(id = R.drawable.profile_img_placeholder),
                            contentDescription = null,
                            contentScale = ContentScale.Crop,
                            modifier = Modifier.fillMaxSize()
                        )
                    }
                }
                Column(
                    Modifier
                        .fillMaxWidth()
                        .wrapContentHeight(),
                    horizontalAlignment = Alignment.Start,
                    verticalArrangement = Arrangement.Center
                ) {
                    Text(
                        text = "Pikachu, Pokemon",
                        fontSize = 21.sp,
                        fontFamily = MontserratAlternatesBold,
                        color = White
                    )
                }
            }
            TextField(
                value = bioText.value,
                onValueChange = {
                    showBioCheckIcon.value = it.isNotEmpty()
                    bioText.value = it
                },
                placeholder = {
                    Text(
                        text = stringResource(R.string.bio_placeholder_text),
                        fontSize = 15.sp,
                        color = Color.LightGray,
                        fontFamily = RalewayMedium
                    )
                },
                textStyle = TextStyle(fontSize = 14.sp, fontFamily = RalewayMedium),
                keyboardActions = KeyboardActions(),
                keyboardOptions = KeyboardOptions(
                    imeAction = ImeAction.Default,
                    keyboardType = KeyboardType.Text,
                    autoCorrect = true
                ),
                visualTransformation = VisualTransformation.None,
                singleLine = false,
                shape = CircleShape,
                colors = TextFieldDefaults.textFieldColors(
                    textColor = White,
                    backgroundColor = Transparent,
                    cursorColor = White,
                    focusedIndicatorColor = Transparent,
                    unfocusedIndicatorColor = Transparent
                ),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(140.dp)
                    .padding(start = 10.dp, top = 15.dp)
                    .focusRequester(focusRequester)
                    .onFocusChanged {
                        isTextFieldFocused = it.isFocused
                    },
                trailingIcon = {
                    if (showBioCheckIcon.value) {
                        Icon(
                            imageVector = Icons.Filled.Check,
                            contentDescription = "Circle icon",
                            tint = White,
                            modifier = Modifier
                                .size(25.dp)
                                .clickable {
                                    if (bioText.value.isNotEmpty())
                                        showBioCheckIcon.value = false
                                    keyboardController?.hide()
                                    focusManager.clearFocus()
                                }
                        )
                    }
                }
            )

            Card(
                colors = CardDefaults.cardColors(containerColor = BackgroundDarker),
                shape = RoundedCornerShape(15.dp),
                elevation = CardDefaults.cardElevation(defaultElevation = 1.dp),
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight()
                    .padding(20.dp)
            ) {
                Column(
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier
                        .fillMaxWidth()
                        .wrapContentHeight()
                ) {
                    //First name
                    TextField(
                        value = firstName.value,
                        onValueChange = {
                            showFirstNameCheckIcon.value = it.isNotEmpty()
                            firstName.value = it
                        },
                        label = {
                            Text(
                                text = stringResource(id = R.string.first_name_label),
                                fontSize = 12.sp,
                                color = Color.LightGray,
                                fontFamily = MontserratMedium
                            )
                        },
                        placeholder = {
                            Text(
                                text = firstName.value,
                                fontSize = 13.sp,
                                color = Color.Gray,
                                fontFamily = MontserratMedium
                            )
                        },
                        visualTransformation = VisualTransformation.None,
                        singleLine = true,
                        colors = TextFieldDefaults.textFieldColors(
                            textColor = White,
                            backgroundColor = BackgroundDarker,
                            unfocusedLabelColor = Color.LightGray,
                            focusedLabelColor = White,
                            cursorColor = White,
                            unfocusedIndicatorColor = Transparent,
                            focusedIndicatorColor = Transparent
                        ),
                        trailingIcon = {
                            if (showFirstNameCheckIcon.value)
                                Icon(
                                    imageVector = Icons.Filled.CheckCircle,
                                    contentDescription = "Circle icon",
                                    tint = White,
                                    modifier = Modifier
                                        .size(20.dp)
                                        .clickable {
                                            if (firstName.value.isNotEmpty() && lastName.value.isNotEmpty())
                                                showFirstNameCheckIcon.value = false
                                            keyboardController?.hide()
                                            focusManager.clearFocus()
                                        }
                                )
                        },
                        keyboardOptions = KeyboardOptions(
                            keyboardType = KeyboardType.Text,
                            imeAction = ImeAction.Next
                        ),
                        modifier = Modifier
                            .fillMaxWidth()
                            .focusRequester(focusRequester)
                            .onFocusChanged {
                                isTextFieldFocused = it.isFocused
                            },
                        textStyle = TextStyle(fontFamily = RalewayBold, fontSize = 15.sp)
                    )
                    Spacer(modifier = Modifier.height(5.dp))
                    //Last Name
                    TextField(
                        value = lastName.value,
                        onValueChange = {
                            showLastNameCheckIcon.value = it.isNotEmpty()
                            lastName.value = it
                        },
                        label = {
                            Text(
                                text = stringResource(id = R.string.last_name_label),
                                fontSize = 12.sp,
                                color = Color.LightGray,
                                fontFamily = MontserratMedium
                            )
                        },
                        placeholder = {
                            Text(
                                text = lastName.value,
                                fontSize = 13.sp,
                                color = Color.Gray,
                                fontFamily = MontserratMedium
                            )
                        },
                        visualTransformation = VisualTransformation.None,
                        singleLine = true,
                        colors = TextFieldDefaults.textFieldColors(
                            textColor = White,
                            backgroundColor = BackgroundDarker,
                            unfocusedLabelColor = Color.LightGray,
                            focusedLabelColor = White,
                            cursorColor = White,
                            unfocusedIndicatorColor = Transparent,
                            focusedIndicatorColor = Transparent
                        ),
                        trailingIcon = {
                            if (showLastNameCheckIcon.value)
                                Icon(
                                    imageVector = Icons.Filled.CheckCircle,
                                    contentDescription = "Circle icon",
                                    tint = White,
                                    modifier = Modifier
                                        .size(20.dp)
                                        .clickable {
                                            if (firstName.value.isNotEmpty() && lastName.value.isNotEmpty())
                                                showLastNameCheckIcon.value = false
                                            keyboardController?.hide()
                                            focusManager.clearFocus()
                                        }

                                )
                        },
                        keyboardOptions = KeyboardOptions(
                            keyboardType = KeyboardType.Text,
                            imeAction = ImeAction.Done
                        ),
                        keyboardActions = KeyboardActions(
                            onDone = {
                                focusManager.clearFocus()
                                keyboardController?.hide()
                            }
                        ),
                        modifier = Modifier
                            .fillMaxWidth()
                            .focusRequester(focusRequester)
                            .onFocusChanged {
                                isTextFieldFocused = it.isFocused
                            },
                        textStyle = TextStyle(fontFamily = RalewayBold, fontSize = 15.sp)
                    )
                    Spacer(modifier = Modifier.height(5.dp))
                    //Email
                    TextField(
                        value = email.value ?: "No email supplied",
                        onValueChange = {
                            email.value = it
                        },
                        readOnly = true,
                        label = {
                            Text(
                                text = stringResource(id = R.string.email_label),
                                fontSize = 12.sp,
                                color = Color.DarkGray,
                                fontFamily = MontserratMedium
                            )
                        },
                        visualTransformation = VisualTransformation.None,
                        singleLine = true,
                        colors = TextFieldDefaults.textFieldColors(
                            textColor = White,
                            backgroundColor = BackgroundDarker,
                            unfocusedLabelColor = Color.LightGray,
                            focusedLabelColor = White,
                            cursorColor = White,
                            unfocusedIndicatorColor = Transparent,
                            focusedIndicatorColor = Transparent
                        ),
                        trailingIcon = {
                            Icon(
                                imageVector = Icons.Filled.Info,
                                contentDescription = "Circle icon",
                                tint = Color.Gray,
                                modifier = Modifier
                                    .size(20.dp)
                                    .clickable {
                                        keyboardController?.hide()
                                        focusManager.clearFocus()
                                    }
                            )
                        },
                        placeholder = {
                            Text(
                                text = email.value ?: "no email supplied",
                                fontSize = 13.sp,
                                color = Color.Gray,
                                fontFamily = MontserratMedium
                            )
                        },
                        keyboardOptions = KeyboardOptions(
                            keyboardType = KeyboardType.Email,
                            imeAction = ImeAction.Done
                        ),
                        keyboardActions = KeyboardActions(
                            onDone = {
                                focusManager.clearFocus()
                                keyboardController?.hide()
                            }
                        ),
                        modifier = Modifier
                            .fillMaxWidth()
                            .focusRequester(focusRequester)
                            .onFocusChanged {
                                isTextFieldFocused = it.isFocused
                            }
                    )
                }

            }

        }
    }
}