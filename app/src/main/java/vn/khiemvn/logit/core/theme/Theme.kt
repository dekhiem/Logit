package vn.khiemvn.logit.core.theme

import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext

private val LightColorScheme = lightColorScheme(
    primary = Black,
    onPrimary = White,
    primaryContainer = LightGray,
    onPrimaryContainer = Black,

    secondary = Black,
    onSecondary = White,
    secondaryContainer = LightGray,
    onSecondaryContainer = Black,

    background = White,
    onBackground = Black,

    surface = White,
    onSurface = Black,
    surfaceVariant = OffWhite,
    onSurfaceVariant = Black,
//    onSurfaceVariant = MidGray,

    outline = LightGray,
    outlineVariant = OffWhite,
)

private val DarkColorScheme = darkColorScheme(
    primary = White,
    onPrimary = Black,
    primaryContainer = DarkGray,
    onPrimaryContainer = White,

    secondary = White,
    onSecondary = Black,
    secondaryContainer = DarkGray,
    onSecondaryContainer = White,

    background = Black,
    onBackground = White,

    surface = NearBlack,
    onSurface = White,
    surfaceVariant = DarkGray,
    onSurfaceVariant = MidGray,

    outline = DarkGray,
    outlineVariant = NearBlack,
)

@Composable
fun LogitTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colorScheme = if (darkTheme) DarkColorScheme else LightColorScheme

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}

@Composable
fun LogitTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    dynamicColor: Boolean = true,
    content: @Composable () -> Unit
) {
    val colorScheme = when {
        dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
            val context = LocalContext.current
            if (darkTheme) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)
        }

        darkTheme -> DarkColorScheme
        else -> LightColorScheme
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}