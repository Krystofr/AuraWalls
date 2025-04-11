package app.chris.aurawalls.util

import app.chris.aurawalls.R
import app.chris.aurawalls.ui.theme.Bottom
import app.chris.aurawalls.ui.theme.MiddleColor
import app.chris.aurawalls.ui.theme.TopColor

object Constants {
    val categories = listOf(
        Category("Nature", R.drawable.nature),
        Category("Cities", R.drawable.cities),
        Category("Animals", R.drawable.animals),
        Category("Technology", R.drawable.technology),
        Category("Travel", R.drawable.travel),
        /*"Nature",
        "Cities",
        "Animals",
        "Technology",
        "Travel",

        "People",
        "Fashion",
        "Food",
        "Sports",
        "Music",
        "Health",
        "Abstract",
        "Vehicles"*/
    )
    val allCategories = listOf(
        Category("Nature", R.drawable.nature),
        Category("Cities", R.drawable.cities),
        Category("Animals", R.drawable.animals),
        Category("Tech", R.drawable.technology),
        Category("Travel", R.drawable.travel),
        Category("Abstract", R.drawable.wallpaper),
        Category("Sports", R.drawable.sports),
        Category("Fashion", R.drawable.fashion),
        Category("Food", R.drawable.food),
        Category("Music", R.drawable.music),
        Category("Health", R.drawable.health),
        Category("Cars", R.drawable.cars),

    )

    val shadowView = listOf(
        TopColor,
        MiddleColor,
        Bottom
    )
}
data class Category(val name: String, val imageResId: Int)