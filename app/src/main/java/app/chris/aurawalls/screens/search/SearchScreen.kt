package app.chris.aurawalls.screens.search

import android.widget.Toast
import androidx.compose.animation.core.tween
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
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
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.focus.FocusManager
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.platform.SoftwareKeyboardController
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import app.chris.aurawalls.screens.home.HomeScreenViewModel
import app.chris.aurawalls.ui.theme.Background
import app.chris.aurawalls.ui.theme.BackgroundDarker
import app.chris.aurawalls.ui.theme.FocusedBackground
import app.chris.aurawalls.ui.theme.IconBackground
import app.chris.aurawalls.ui.theme.RalewayMedium
import app.chris.aurawalls.ui.theme.Transparent
import app.chris.aurawalls.ui.theme.White
import app.chris.aurawalls.util.LightItemsStatusLightNavBars
import app.chris.aurawalls.widgets.BackgroundOverlay
import app.chris.aurawalls.wrapper.Resource

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun SearchScreen(
    navController: NavController,
    searchViewModel: SearchViewModel,
    homeScreenViewModel: HomeScreenViewModel
) {

    val context = LocalContext.current
    val mutableInteractionSource = remember { MutableInteractionSource() }
    val keyboardController = LocalSoftwareKeyboardController.current
    val focusManager = LocalFocusManager.current
    val suggestionsState by searchViewModel.suggestionsStateflow.collectAsStateWithLifecycle()

    LightItemsStatusLightNavBars(context = context)

    Surface(
        modifier = Modifier
            .fillMaxSize()
            .clickable(
                interactionSource = mutableInteractionSource,
                indication = null
            ) {
                focusManager.clearFocus()
                keyboardController?.hide()
            },
        color = Background
    ) {
        BackgroundOverlay()
        Column(
            Modifier
                .fillMaxSize()
                .padding(top = 40.dp, bottom = 70.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Top
        ) {
            Row(
                Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 5.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Start
            ) {
                IconButton(
                    onClick = {
                        keyboardController?.hide()
                        focusManager.clearFocus()
                        navController.popBackStack()
                    },
                    colors = IconButtonDefaults.iconButtonColors(containerColor = IconBackground),
                    modifier = Modifier
                        .size(35.dp)
                ) {
                    Icon(
                        imageVector = Icons.Filled.ArrowBack,
                        contentDescription = null,
                        tint = White,
                        modifier = Modifier
                            .size(20.dp)
                    )
                }
                SearchBar(focusManager, keyboardController!!, searchViewModel)
            }

            when (val state = suggestionsState) {
                is Resource.Success -> {
                    state.data?.let {
                        SearchList(photos = it, navController, homeScreenViewModel)
                    }
                }

                is Resource.Error -> {
                    Toast.makeText(context, state.message.toString(), Toast.LENGTH_SHORT).show()
                }

                is Resource.Loading -> {

                }

                is Resource.Idle -> {

                }
            }
        }
    }
}

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun SearchBar(
    focusManager: FocusManager,
    keyboardController: SoftwareKeyboardController,
    viewModel: SearchViewModel
) {
    val searchQueryState by viewModel.searchQueryStateFlow.collectAsStateWithLifecycle()
    val searchQuery = remember {
        mutableStateOf(searchQueryState)
    }

    var isTextFieldFocused by remember { mutableStateOf(false) }
    val focusRequester = remember {
        FocusRequester()
    }

    Box(
        modifier = Modifier
            .fillMaxWidth(0.97f)
            .wrapContentHeight()
            .padding(start = 5.dp),
        contentAlignment = Alignment.TopStart
    ) {
        TextField(
            value = searchQuery.value,
            onValueChange = { query ->
                searchQuery.value = query
                viewModel.setSearchQuery(query)
            },
            placeholder = {
                Text(
                    text = "Search photo categories",
                    fontSize = 13.sp,
                    fontFamily = RalewayMedium,
                    color = Color.Gray
                )
            },
            leadingIcon = {
                Icon(
                    imageVector = Icons.Filled.Search,
                    contentDescription = "Search icon",
                    tint = White,
                    modifier = Modifier.size(25.dp)
                )
            },
            trailingIcon = {
                androidx.compose.animation.AnimatedVisibility(
                    visible = searchQuery.value.isNotEmpty(),
                    enter = slideInHorizontally(
                        initialOffsetX = { fullWidth -> fullWidth },
                        animationSpec = tween(300)
                    ),
                    exit = slideOutHorizontally(
                        targetOffsetX = { fullWidth -> fullWidth },
                        animationSpec = tween(300)
                    )
                ) {
                    IconButton(
                        onClick = {
                            searchQuery.value = ""
                        }, colors = IconButtonDefaults.iconButtonColors(
                            containerColor = IconBackground
                        ),
                        modifier = Modifier.size(25.dp)
                    ) {
                        Icon(
                            imageVector = Icons.Filled.Clear,
                            contentDescription = "Search icon",
                            tint = White,
                            modifier = Modifier
                                .size(15.dp)
                        )
                    }

                }
            },
            visualTransformation = VisualTransformation.None,
            colors = TextFieldDefaults.textFieldColors(
                backgroundColor = if (isTextFieldFocused) FocusedBackground else BackgroundDarker,
                focusedIndicatorColor = Transparent,
                unfocusedIndicatorColor = Transparent,
                cursorColor = White
            ),
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
                .focusRequester(focusRequester)
                .onFocusChanged {
                    isTextFieldFocused = it.isFocused
                }
                .shadow(
                    elevation = if (isTextFieldFocused) 20.dp else 0.dp,
                    shape = RoundedCornerShape(15.dp)
                ),
            keyboardActions = KeyboardActions(
                onSearch = {
                    keyboardController.hide()
                    focusManager.clearFocus()
                }),
            keyboardOptions = KeyboardOptions(
                imeAction = ImeAction.Search,
                keyboardType = KeyboardType.Text,
                autoCorrect = true
            ),
            shape = RoundedCornerShape(15.dp),
            singleLine = true,
            textStyle = TextStyle(
                color = White,
                fontFamily = RalewayMedium,
                fontSize = 14.sp
            )
        )

    }
}