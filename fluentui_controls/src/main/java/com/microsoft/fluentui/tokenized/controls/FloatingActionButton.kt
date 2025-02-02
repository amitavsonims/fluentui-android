package com.microsoft.fluentui.tokenized.controls

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.*
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.compositionLocalOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.semantics.clearAndSetSemantics
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.microsoft.fluentui.theme.FluentTheme
import com.microsoft.fluentui.theme.token.ControlTokens.ControlType
import com.microsoft.fluentui.theme.token.controlTokens.FABInfo
import com.microsoft.fluentui.theme.token.controlTokens.FABSize
import com.microsoft.fluentui.theme.token.controlTokens.FABState
import com.microsoft.fluentui.theme.token.controlTokens.FABTokens

val LocalFABTokens = compositionLocalOf { FABTokens() }
val LocalFABInfo = compositionLocalOf { FABInfo() }

@Composable
fun FloatingActionButton(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    state: FABState = FABState.Expanded,
    size: FABSize = FABSize.Large,
    enabled: Boolean = true,
    interactionSource: MutableInteractionSource = remember { MutableInteractionSource() },
    icon: ImageVector? = null,
    text: String? = null,
    fabTokens: FABTokens? = null
) {
    if (icon == null && (text == null || text == ""))
        return

    val token = fabTokens
        ?: FluentTheme.controlTokens.tokens[ControlType.FloatingActionButton] as FABTokens

    CompositionLocalProvider(
        LocalFABTokens provides token,
        LocalFABInfo provides FABInfo(state, size)
    ) {
        val clickAndSemanticsModifier = Modifier.clickable(
            interactionSource = interactionSource,
            indication = LocalIndication.current,
            enabled = enabled,
            onClickLabel = null,
            role = Role.Button,
            onClick = onClick
        )
        val isFabExpanded: Boolean =
            (text != null && text != "" && getFABInfo().state == FABState.Expanded)
        val backgroundColor = getFABToken().backgroundColor(fabInfo = getFABInfo()).getColorByState(
            enabled = enabled,
            selected = false,
            interactionSource = interactionSource
        )
        val contentPadding = if (isFabExpanded) getFABToken().textPadding(getFABInfo())
        else getFABToken().iconPadding(getFABInfo())
        val iconSpacing = if (isFabExpanded) getFABToken().spacing(getFABInfo()) else 0.dp
        val shape = CircleShape
        val borders: List<BorderStroke> =
            getFABToken().borderStroke(fabInfo = getFABInfo()).getBorderStrokeByState(
                enabled = enabled,
                selected = false,
                interactionSource = interactionSource
            )

        var borderModifier: Modifier = Modifier
        var borderWidth = 0.dp
        for (border in borders) {
            borderWidth += border.width
            borderModifier = borderModifier.border(borderWidth, border.brush, shape)
        }

        Box(
            modifier
                .height(getFABToken().fixedHeight(getFABInfo()))
                .defaultMinSize(minWidth = getFABToken().minWidth(getFABInfo()))
                .shadow(
                    elevation = getFABToken()
                        .elevation(fabInfo = getFABInfo())
                        .getElevationByState(
                            enabled = enabled,
                            selected = false,
                            interactionSource = interactionSource
                        ),
                    shape = CircleShape
                )
                .background(
                    color = backgroundColor,
                    shape = shape
                )
                .clip(shape)
                .semantics(mergeDescendants = true) { contentDescription = text ?: "" }
                .then(clickAndSemanticsModifier)
                .then(borderModifier),
            propagateMinConstraints = true
        ) {
            Row(
                Modifier.padding(contentPadding),
                horizontalArrangement = Arrangement.spacedBy(
                    iconSpacing,
                    Alignment.CenterHorizontally
                ),
                verticalAlignment = Alignment.CenterVertically
            ) {

                if (icon != null)
                    Icon(
                        imageVector = icon,
                        contentDescription = text,
                        modifier = Modifier
                            .size(
                                getFABToken().iconSize(getFABInfo()).size
                            )
                            .clearAndSetSemantics { },
                        tint = getFABToken().iconColor(fabInfo = getFABInfo()).getColorByState(
                            enabled = enabled,
                            selected = false,
                            interactionSource = interactionSource
                        )
                    )

                AnimatedVisibility(isFabExpanded) {
                    Text(
                        text = text!!,
                        modifier = Modifier.clearAndSetSemantics { },
                        fontSize = getFABToken().fontInfo(getFABInfo()).fontSize.size,
                        lineHeight = getFABToken().fontInfo(getFABInfo()).fontSize.lineHeight,
                        fontWeight = getFABToken().fontInfo(getFABInfo()).weight,
                        color = getFABToken().textColor(fabInfo = getFABInfo()).getColorByState(
                            enabled = enabled,
                            selected = false,
                            interactionSource = interactionSource
                        ),
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )
                }
            }
        }
    }
}


@Composable
fun getFABToken(): FABTokens {
    return LocalFABTokens.current
}

@Composable
fun getFABInfo(): FABInfo {
    return LocalFABInfo.current
}
