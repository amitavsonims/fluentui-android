package com.microsoft.fluentui.tokenized.progress

import android.widget.ProgressBar
import androidx.compose.animation.core.*
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.requiredHeight
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.compositionLocalOf
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.unit.LayoutDirection
import com.microsoft.fluentui.theme.FluentTheme
import com.microsoft.fluentui.theme.token.ControlTokens
import com.microsoft.fluentui.theme.token.controlTokens.LinearProgressIndicatorHeight
import com.microsoft.fluentui.theme.token.controlTokens.LinearProgressIndicatorInfo
import com.microsoft.fluentui.theme.token.controlTokens.LinearProgressIndicatorTokens
import com.microsoft.fluentui.util.dpToPx

val LocalLinearProgressIndicatorTokens = compositionLocalOf { LinearProgressIndicatorTokens() }
val LocalLinearProgressIndicatorInfo = compositionLocalOf { LinearProgressIndicatorInfo() }

@Composable
fun getLinearProgressIndicatorTokens(): LinearProgressIndicatorTokens {
    return LocalLinearProgressIndicatorTokens.current
}

@Composable
fun getLinearProgressIndicatorInfo(): LinearProgressIndicatorInfo {
    return LocalLinearProgressIndicatorInfo.current
}

/**
 * Create a Determinate Linear Progress Indicator
 *
 * @param progress Progress of the progress indicator. 0.0 represents no progress and 1.0 represents full progress.
 * @param linearProgressIndicatorHeight Optional width of the progress indicator
 * @param modifier Modifier for linear progress indicator
 * @param linearProgressIndicatorTokens Token values for linear progress indicator
 *
 */
@Composable
fun LinearProgressIndicator(
    progress: Float,
    linearProgressIndicatorHeight: LinearProgressIndicatorHeight = LinearProgressIndicatorHeight.XXXSmall,
    modifier: Modifier = Modifier,
    linearProgressIndicatorTokens: LinearProgressIndicatorTokens? = null
) {
    val tokens = linearProgressIndicatorTokens
        ?: FluentTheme.controlTokens.tokens[ControlTokens.ControlType.LinearProgressIndicator] as LinearProgressIndicatorTokens
    CompositionLocalProvider(
        LocalLinearProgressIndicatorTokens provides tokens,
        LocalLinearProgressIndicatorInfo provides LinearProgressIndicatorInfo(
            linearProgressIndicatorHeight = linearProgressIndicatorHeight
        )
    ) {
        val currentProgress = animateFloatAsState(
            targetValue = progress.coerceIn(0f..1f),
            animationSpec = tween(
                delayMillis = 0,
                durationMillis = 1000,
                easing = LinearOutSlowInEasing
            )
        )
        val linearProgressIndicatorHeight =
            getLinearProgressIndicatorTokens().strokeWidth(
                getLinearProgressIndicatorInfo()
            )
        val linearProgressIndicatorBackgroundColor =
            getLinearProgressIndicatorTokens().backgroundColor(
                getLinearProgressIndicatorInfo()
            )
        val linearProgressIndicatorColor =
            getLinearProgressIndicatorTokens().color(
                getLinearProgressIndicatorInfo()
            )
        Canvas(
            modifier = modifier
                .fillMaxWidth()
                .requiredHeight(linearProgressIndicatorHeight)
        ) {
            val strokeWidth = dpToPx(linearProgressIndicatorHeight)
            val yOffset = strokeWidth / 2
            val isLtr = layoutDirection == LayoutDirection.Ltr
            val barStart = (if (isLtr) 0f else size.width)
            val barEnd = (if (isLtr) size.width else 0f)
            drawLine(
                linearProgressIndicatorBackgroundColor,
                Offset(barStart, yOffset),
                Offset(barEnd, yOffset),
                strokeWidth
            )
            val progressIndicatorWidth = currentProgress.value * size.width
            val indicatorLineEnd = if(isLtr) progressIndicatorWidth else size.width - progressIndicatorWidth
            drawLine(
                linearProgressIndicatorColor,
                Offset(barStart, yOffset),
                Offset(indicatorLineEnd, yOffset),
                strokeWidth
            )
        }
    }
}

/**
 * Create an Indeterminate Linear Progress Indicator
 *
 * @param linearProgressIndicatorHeight Optional width of the progress indicator
 * @param modifier Modifier for linear progress indicator
 * @param linearProgressIndicatorTokens Token values for linear progress indicator
 *
 */
@Composable
fun LinearProgressIndicator(
    linearProgressIndicatorHeight: LinearProgressIndicatorHeight = LinearProgressIndicatorHeight.XXXSmall,
    modifier: Modifier = Modifier,
    linearProgressIndicatorTokens: LinearProgressIndicatorTokens? = null
) {
    val tokens = linearProgressIndicatorTokens
        ?: FluentTheme.controlTokens.tokens[ControlTokens.ControlType.LinearProgressIndicator] as LinearProgressIndicatorTokens
    CompositionLocalProvider(
        LocalLinearProgressIndicatorTokens provides tokens,
        LocalLinearProgressIndicatorInfo provides LinearProgressIndicatorInfo(
            linearProgressIndicatorHeight = linearProgressIndicatorHeight
        )
    ) {
        val linearProgressIndicatorHeight =
            getLinearProgressIndicatorTokens().strokeWidth(
                getLinearProgressIndicatorInfo()
            )
        val linearProgressIndicatorBackgroundColor =
            getLinearProgressIndicatorTokens().backgroundColor(
                getLinearProgressIndicatorInfo()
            )
        val linearProgressIndicatorColor =
            getLinearProgressIndicatorTokens().color(
                getLinearProgressIndicatorInfo()
            )
        val infiniteTransition = rememberInfiniteTransition()
        val animationDuration = (1750 * 0.5f).toInt()
        val headAnimationDelay = 0
        val tailAnimationDelay = (animationDuration * 0.5f).toInt()
        val indicatorHead by infiniteTransition.animateFloat(
            0f,
            1f,
            infiniteRepeatable(
                animation = keyframes {
                    durationMillis = animationDuration + 500
                    0f at headAnimationDelay with FastOutSlowInEasing
                    1f at animationDuration + headAnimationDelay
                }
            )
        )
        val indicatorTail by infiniteTransition.animateFloat(
            0f,
            1f,
            infiniteRepeatable(
                animation = keyframes {
                    durationMillis = animationDuration + 500
                    0f at tailAnimationDelay with FastOutSlowInEasing
                    1f at animationDuration + tailAnimationDelay
                }
            )
        )
        Canvas(
            modifier = modifier
                .fillMaxWidth()
                .requiredHeight(linearProgressIndicatorHeight)
        ) {
            val strokeWidth = dpToPx(linearProgressIndicatorHeight)
            val yOffset = strokeWidth / 2
            val isLtr = layoutDirection == LayoutDirection.Ltr
            val barStart = (if (isLtr) 0f else size.width)
            val barEnd = (if (isLtr) size.width else 0f)
            drawLine(
                linearProgressIndicatorBackgroundColor,
                Offset(barStart, yOffset),
                Offset(barEnd, yOffset),
                strokeWidth
            )
            val progressIndicatorStart = (if (isLtr) indicatorHead * size.width else (size.width) - indicatorHead * size.width)
            val progressIndicatorEnd = (if (isLtr) indicatorTail * (size.width) else (size.width) - indicatorTail * (size.width))
            drawLine(
                Brush.linearGradient(
                    0f to linearProgressIndicatorBackgroundColor,
                    0.5f to linearProgressIndicatorColor,
                    1.0f to linearProgressIndicatorBackgroundColor,
                    start = Offset(progressIndicatorStart, yOffset),
                    end = Offset(progressIndicatorEnd, yOffset)
                ),
                Offset(progressIndicatorStart, yOffset),
                Offset(progressIndicatorEnd, yOffset),
                strokeWidth
            )
        }
    }
}