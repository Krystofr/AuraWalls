package app.chris.aurawalls.screens.account

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.net.Uri
import android.util.Log
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
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
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Icon
import androidx.compose.material.ModalBottomSheetLayout
import androidx.compose.material.ModalBottomSheetValue
import androidx.compose.material.Surface
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBox
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.outlined.Create
import androidx.compose.material.rememberModalBottomSheetState
import androidx.compose.material.rememberScaffoldState
import androidx.compose.material3.Divider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.content.ContextCompat
import androidx.core.content.FileProvider
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import app.chris.aurawalls.R
import app.chris.aurawalls.navigation.NavRoutes
import app.chris.aurawalls.room.Profile
import app.chris.aurawalls.ui.theme.Background
import app.chris.aurawalls.ui.theme.BackgroundDarker
import app.chris.aurawalls.ui.theme.BackgroundLight
import app.chris.aurawalls.ui.theme.DividerColor
import app.chris.aurawalls.ui.theme.RalewayBold
import app.chris.aurawalls.ui.theme.RalewayMedium
import app.chris.aurawalls.ui.theme.White
import app.chris.aurawalls.widgets.IconWithBackground
import coil.compose.rememberAsyncImagePainter
import kotlinx.coroutines.launch
import java.io.File
import java.io.FileOutputStream

@Composable
fun AccountScreen(navController: NavController, profileViewModel: ProfileViewModel) {

    Surface(color = Background, modifier = Modifier.fillMaxSize()) {

        Column(
            Modifier
                .fillMaxSize()
                .padding(top = 50.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Top
        ) {
            ProfileImageSection(navController, profileViewModel)
        }
    }
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun ProfileImageSection(navController: NavController, profileViewModel: ProfileViewModel) {

    val context = LocalContext.current
    var selectedImageUri by remember { mutableStateOf<Uri?>(null) }
    val selectedImageBitmap by remember { mutableStateOf<Bitmap?>(null) }

    val profileState by profileViewModel.profileStateFlow.collectAsStateWithLifecycle()

    val scope = rememberCoroutineScope()
    val interactionSource = remember { MutableInteractionSource() }
    val bottomSheetState =
        rememberModalBottomSheetState(initialValue = ModalBottomSheetValue.Hidden)
    val scaffoldState = rememberScaffoldState()
    val cameraImageUri by remember {
        mutableStateOf(
            createImageFile(
                context,
                "profile_${System.currentTimeMillis()}"
            )
        )
    }


    val imagePickerLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent(),
        onResult = { uri ->
            uri?.let {
                Log.d("AccountScreen", "Selected image URI: $uri")
                selectedImageUri =
                    copyUriToFile(context, uri, "profile_${System.currentTimeMillis()}")
                //selectedImageBitmap = null
                val profile =
                    Profile(id = 0, name = "Riyadh Mahrez", imageUri = selectedImageUri.toString())
                profileViewModel.saveProfile(profile)
            }
        }
    )

    val cameraLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.TakePicture()
    ) { success ->
        if (success) {
            selectedImageUri = cameraImageUri
            //selectedImageBitmap = null
            val profile =
                Profile(id = 0, name = "Riyadh Mahrez", imageUri = selectedImageUri.toString())
            profileViewModel.saveProfile(profile)
        }
    }

    val requestPermissionLauncher =
        rememberLauncherForActivityResult(
            contract = ActivityResultContracts.RequestPermission()
        )
        { isGranted ->
            if (isGranted) {
                cameraLauncher.launch(cameraImageUri)
            } else {
                scope.launch {
                    scaffoldState.snackbarHostState.showSnackbar("Camera permission denied\"")
                }
            }
        }

    LaunchedEffect(profileState) {
        profileState?.let {
            selectedImageUri = it.imageUri?.let { uri ->
                Log.d("AccountScreen", "Loaded image URI: $uri")
                Uri.parse(uri)
            }
        }
    }

    ModalBottomSheetLayout(
        sheetState = bottomSheetState,
        sheetBackgroundColor = BackgroundDarker,
        sheetShape = RoundedCornerShape(topStart = 20.dp, topEnd = 20.dp),
        modifier = Modifier.fillMaxSize(),
        sheetContent = {
            BottomSheetContent(
                onPickFromGallery = {
                    imagePickerLauncher.launch("image/*")
                    scope.launch { bottomSheetState.hide() }
                },
                onCaptureFromCamera = {
                    checkCameraPermissionAndLaunch(
                        context,
                        requestPermissionLauncher
                    )
                    scope.launch { bottomSheetState.hide() }
                })
        }) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Background),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Card(
                modifier = Modifier
                    .fillMaxWidth(0.94f)
                    .wrapContentHeight(),
                shape = RoundedCornerShape(size = 15.dp),
                backgroundColor = BackgroundDarker,
                elevation = 10.dp
            ) {
                Column(
                    Modifier
                        .fillMaxWidth()
                        .wrapContentHeight(),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    Box(
                        modifier = Modifier
                            .wrapContentHeight()
                            .fillMaxWidth()
                            .padding(end = 5.dp, top = 5.dp),
                        contentAlignment = Alignment.TopEnd
                    ) {
                        IconWithBackground(
                            onClick = { navController.popBackStack() },
                            icon = Icons.Filled.Clear
                        )
                    }
                    Box(
                        modifier = Modifier
                            .size(150.dp)
                            .shadow(elevation = 20.dp, shape = CircleShape),
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
                    Icon(
                        imageVector = Icons.Outlined.Create,
                        contentDescription = null,
                        modifier = Modifier
                            .size(30.dp)
                            .clickable(
                                interactionSource = interactionSource,
                                indication = null,
                            )
                            {
                                scope.launch { bottomSheetState.show() }
                            }
                            .offset(y = (-15).dp),
                        tint = White
                    )
                    Spacer(modifier = Modifier.height(20.dp))
                    Text(
                        text = "Riyadh Mahrez",
                        color = White,
                        fontSize = 23.sp,
                        fontFamily = RalewayMedium
                    )
                    Spacer(modifier = Modifier.height(30.dp))
                }
            }
            Spacer(modifier = Modifier.height(15.dp))
            AccountSection(navController)
        }
    }
}

@Composable
fun BottomSheetContent(
    onPickFromGallery: () -> Unit,
    onCaptureFromCamera: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        Text(
            text = "Choose Option",
            fontSize = 15.sp,
            modifier = Modifier.padding(bottom = 16.dp),
            fontFamily = RalewayBold,
            color = White
        )
        Row(
            Modifier
                .fillMaxWidth()
                .clickable { onPickFromGallery() },
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Start
        ) {
            Image(
                painter = painterResource(id = R.drawable.image_icon),
                contentDescription = "Image icon",
                contentScale = ContentScale.Crop,
                modifier = Modifier.size(30.dp)
            )
            Text(
                text = "Pick from Gallery",
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                fontFamily = RalewayMedium,
                color = White
            )

        }
        Row(
            Modifier
                .fillMaxWidth()
                .clickable { onCaptureFromCamera() },
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Start
        ) {
            Image(
                painter = painterResource(id = R.drawable.camera_icon),
                contentDescription = "Image icon",
                contentScale = ContentScale.Crop,
                modifier = Modifier.size(30.dp)
            )
            Text(
                text = "Capture from Camera",
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                fontFamily = RalewayMedium,
                color = White
            )
        }
    }
}

@Composable
fun AccountSection(navController: NavController) {

    Card(
        modifier = Modifier
            .fillMaxWidth(0.94f)
            .fillMaxHeight(),
        shape = RoundedCornerShape(size = 15.dp),
        backgroundColor = BackgroundDarker,
        elevation = 10.dp
    ) {
        LazyColumn(
            Modifier
                .fillMaxSize()
                .padding(top = 10.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Top,
            state = rememberLazyListState()
        ) {
            item {
                AccountItem(
                    title = "Profile",
                    onClick = { navController.navigate(NavRoutes.ProfileUi.route) },
                    icon = Icons.Filled.Person
                )
                AccountItem(title = "Testimonials", onClick = {}, icon = Icons.Filled.List)
                AccountItem(
                    title = "Favorite Wallpapers",
                    onClick = { navController.navigate(NavRoutes.FavoritesUi.route) },
                    icon = Icons.Filled.Favorite
                )
                AccountItem(
                    title = "Notifications",
                    onClick = {},
                    icon = Icons.Filled.Notifications
                )
                AccountItem(title = "About", onClick = {}, icon = Icons.Filled.Person)
                AccountItem(title = "Contact us", onClick = {}, icon = Icons.Filled.AccountBox)
                Text(
                    "v1.0.1-12001",
                    color = White,
                    fontSize = 13.sp,
                    fontFamily = RalewayMedium,
                    modifier = Modifier.padding(top = 20.dp)
                )
            }
        }
    }
}

@Composable
fun AccountItem(title: String, onClick: () -> Unit, icon: ImageVector) {
    Column(
        Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .clickable { onClick() },
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
    ) {
        Row(
            Modifier
                .fillMaxWidth()
                .wrapContentHeight()
                .padding(start = 20.dp, top = 10.dp, bottom = 10.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Start
        ) {
            Icon(
                imageVector = icon,
                contentDescription = null,
                Modifier.size(25.dp),
                tint = BackgroundLight
            )
            Spacer(modifier = Modifier.width(20.dp))
            Text(
                text = title,
                color = White,
                fontSize = 15.sp,
                fontFamily = RalewayMedium
            )
        }
        Spacer(modifier = Modifier.height(15.dp))
        Divider(color = DividerColor, thickness = 1.dp, modifier = Modifier.fillMaxWidth(0.9f))
    }
}

fun checkCameraPermissionAndLaunch(
    context: Context,
    requestPermissionLauncher: androidx.activity.result.ActivityResultLauncher<String>
) {
    when {
        ContextCompat.checkSelfPermission(
            context,
            Manifest.permission.CAMERA
        ) == PackageManager.PERMISSION_GRANTED -> {
            requestPermissionLauncher.launch(Manifest.permission.CAMERA)
        }

        else -> {
            requestPermissionLauncher.launch(Manifest.permission.CAMERA)
        }
    }
}

fun copyUriToFile(context: Context, uri: Uri, fileName: String): Uri? {
    val inputStream = context.contentResolver.openInputStream(uri)
    val file = File(context.getExternalFilesDir(null), "$fileName.jpg")
    val outputStream = FileOutputStream(file)
    inputStream.use { input ->
        outputStream.use { output ->
            input?.copyTo(output)
        }
    }
    return Uri.fromFile(file)
}

fun createImageFile(context: Context, fileName: String): Uri {
    val file = File(context.getExternalFilesDir(null), "$fileName.jpg")
    return FileProvider.getUriForFile(context, "${context.packageName}.provider", file)
}
