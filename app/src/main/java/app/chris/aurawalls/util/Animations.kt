package app.chris.aurawalls.util

import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.tween
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally

fun customEnterTransition(): EnterTransition {
    return slideInHorizontally(
        animationSpec = tween(durationMillis = 600, easing = LinearOutSlowInEasing),
        initialOffsetX = { fullWidth -> fullWidth  }
    )
}

fun customExitTransition(): ExitTransition {
    return slideOutHorizontally(
        animationSpec = tween(durationMillis = 600, easing = LinearOutSlowInEasing),
        targetOffsetX = { fullWidth -> fullWidth  }
    )
}

fun customPopEnterTransition(): EnterTransition {
    return slideInHorizontally(
        animationSpec = tween(durationMillis = 600, easing = LinearOutSlowInEasing),
        initialOffsetX = { fullWidth -> fullWidth  }
    )
}

fun customPopExitTransition(): ExitTransition {
    return slideOutHorizontally(
        animationSpec = tween(durationMillis = 600, easing = LinearOutSlowInEasing),
        targetOffsetX = { fullWidth -> fullWidth  }
    )
}