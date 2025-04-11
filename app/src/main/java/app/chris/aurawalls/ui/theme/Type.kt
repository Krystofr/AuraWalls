package app.chris.aurawalls.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import app.chris.aurawalls.R

// Set of Material typography styles to start with
val Typography = Typography(
    bodyLarge = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp,
        lineHeight = 24.sp,
        letterSpacing = 0.5.sp
    )
)

val LobsterRegular = FontFamily(Font(R.font.lobster_regular))
val MontserratMedium = FontFamily(Font(R.font.montserrat_medium))
val RalewayRegular = FontFamily(Font(R.font.raleway_regular))
val MontserratAlternatesMedium = FontFamily(Font(R.font.montserrat_alternates_medium))
val MontserratAlternatesBold = FontFamily(Font(R.font.montserrat_alternates_bold))

val RalewayMedium = FontFamily(Font(R.font.raleway_medium))
val RalewayBold = FontFamily(Font(R.font.raleway_bold))

/* Other default text styles to override
titleLarge = TextStyle(
    fontFamily = FontFamily.Default,
    fontWeight = FontWeight.Normal,
    fontSize = 22.sp,
    lineHeight = 28.sp,
    letterSpacing = 0.sp
),
labelSmall = TextStyle(
    fontFamily = FontFamily.Default,
    fontWeight = FontWeight.Medium,
    fontSize = 11.sp,
    lineHeight = 16.sp,
    letterSpacing = 0.5.sp
)
*/