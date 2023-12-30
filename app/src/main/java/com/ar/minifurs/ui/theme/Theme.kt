package com.ar.minifurs.ui.theme

import android.app.Activity
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalView
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.core.view.WindowCompat
import com.ar.minifurs.R

val appFontFamily = FontFamily(

    Font(
        resId = R.font.lufga_bold,
        weight = FontWeight.Bold
    ),
    Font(
        resId = R.font.lufga_semi_bold,
        weight = FontWeight.SemiBold
    ),
    Font(
        resId = R.font.lufga_regular,
        weight = FontWeight.Normal
    ),
    Font(
        resId = R.font.lufga_medium,
        weight = FontWeight.Medium
    ),
    Font(
        resId = R.font.lufga_light,
        weight = FontWeight.Light
    )
)

private val lightColorScheme = lightColors(
    primary = primaryLight,
    secondary = secondaryLight,
    background = backgroundLight,
    surface = backgroundLight,
    onPrimary = backgroundLight,
    onSecondary = secondaryTextLight,
    onBackground = primaryTextLight,
    onSurface = primaryTextLight
)

private val darkColorScheme = lightColors(
    primary = primary,
    secondary = secondary,
    background = background,
    surface = background,
    onPrimary = primaryText,
    onSecondary = secondaryText,
    onBackground = primaryText,
    onSurface = primaryText
)

@Composable
fun MinifursArAppTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val configuration = LocalConfiguration.current
    val dimensions = if (configuration.screenWidthDp <= 360) smallDimensions else sw360Dimensions

    val colorScheme = if (darkTheme) darkColorScheme else lightColorScheme
    val view = LocalView.current
    if (!view.isInEditMode) {
        SideEffect {
            val window = (view.context as Activity).window
            window.statusBarColor = colorScheme.primary.toArgb()
            window.navigationBarColor = colorScheme.background.toArgb()
            WindowCompat.getInsetsController(window, view).isAppearanceLightStatusBars = darkTheme
        }
    }
    ProvideDimens(dimensions = dimensions) {
        MaterialTheme(
            colors = colorScheme,
            typography = Typography,
            content = content
        )
    }
}

object AppTheme {
    val dimens: Dimensions
        @Composable
        get() = LocalAppDimens.current
}