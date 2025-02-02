package com.microsoft.fluentui.theme.token.controlTokens

import android.os.Parcelable
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.microsoft.fluentui.theme.token.*
import kotlinx.parcelize.Parcelize

enum class AvatarGroupStyle {
    Stack,
    Pile
}

data class AvatarGroupInfo(
    val size: AvatarSize = AvatarSize.Size32,
    val style: AvatarGroupStyle = AvatarGroupStyle.Stack
) : ControlInfo

@Parcelize
open class AvatarGroupTokens : ControlToken, Parcelable {

    @Composable
    open fun avatarSize(avatarGroupInfo: AvatarGroupInfo): Dp {
        return when (avatarGroupInfo.size) {
            AvatarSize.Size16 -> 16.dp
            AvatarSize.Size20 -> 24.dp
            AvatarSize.Size24 -> 24.dp
            AvatarSize.Size32 -> 32.dp
            AvatarSize.Size40 -> 40.dp
            AvatarSize.Size56 -> 56.dp
            AvatarSize.Size72 -> 72.dp
        }
    }

    @Composable
    open fun fontInfo(avatarInfo: AvatarInfo): FontInfo {
        return when (avatarInfo.size) {
            AvatarSize.Size16 -> FontInfo(
                fontSize = FontSize(9.sp, 12.sp),
                weight = GlobalTokens.fontWeight(GlobalTokens.FontWeightTokens.Regular)
            )
            AvatarSize.Size20 -> FontInfo(
                fontSize = FontSize(9.sp, 12.sp),
                weight = GlobalTokens.fontWeight(GlobalTokens.FontWeightTokens.Regular)
            )
            AvatarSize.Size24 -> FontInfo(
                fontSize = GlobalTokens.fontSize(GlobalTokens.FontSizeTokens.Size100),
                weight = GlobalTokens.fontWeight(GlobalTokens.FontWeightTokens.Regular)
            )
            AvatarSize.Size32 -> FontInfo(
                fontSize = GlobalTokens.fontSize(GlobalTokens.FontSizeTokens.Size200),
                weight = GlobalTokens.fontWeight(GlobalTokens.FontWeightTokens.Regular)
            )
            AvatarSize.Size40 -> FontInfo(
                fontSize = GlobalTokens.fontSize(GlobalTokens.FontSizeTokens.Size300),
                weight = GlobalTokens.fontWeight(GlobalTokens.FontWeightTokens.Regular)
            )
            AvatarSize.Size56 -> FontInfo(
                fontSize = GlobalTokens.fontSize(GlobalTokens.FontSizeTokens.Size500),
                weight = GlobalTokens.fontWeight(GlobalTokens.FontWeightTokens.Medium)
            )
            AvatarSize.Size72 -> FontInfo(
                fontSize = GlobalTokens.fontSize(GlobalTokens.FontSizeTokens.Size700),
                weight = GlobalTokens.fontWeight(GlobalTokens.FontWeightTokens.Medium)
            )
        }
    }

    @Composable
    open fun spacing(avatarGroupInfo: AvatarGroupInfo, isActive: Boolean): Dp {
        return when (avatarGroupInfo.style) {
            AvatarGroupStyle.Stack -> when (avatarGroupInfo.size) {
                AvatarSize.Size16 -> if (isActive) 0.dp else (-2).dp
                AvatarSize.Size20 -> if (isActive) 0.dp else (-2).dp
                AvatarSize.Size24 -> if (isActive) 0.dp else (-2).dp
                AvatarSize.Size32 -> if (isActive) (-4).dp else (-4).dp
                AvatarSize.Size40 -> if (isActive) (-4).dp else (-8).dp
                AvatarSize.Size56 -> if (isActive) (-8).dp else (-12).dp
                AvatarSize.Size72 -> if (isActive) (-6).dp else (-12).dp
            }

            AvatarGroupStyle.Pile -> when (avatarGroupInfo.size) {
                AvatarSize.Size16 -> GlobalTokens.spacing(
                    GlobalTokens.SpacingTokens.XXSmall
                )
                AvatarSize.Size20 -> GlobalTokens.spacing(
                    GlobalTokens.SpacingTokens.XXSmall
                )
                AvatarSize.Size24 -> GlobalTokens.spacing(
                    GlobalTokens.SpacingTokens.XXSmall
                )
                AvatarSize.Size32 -> GlobalTokens.spacing(
                    GlobalTokens.SpacingTokens.XSmall
                )
                AvatarSize.Size40 -> GlobalTokens.spacing(
                    GlobalTokens.SpacingTokens.XSmall
                )
                AvatarSize.Size56 -> GlobalTokens.spacing(
                    GlobalTokens.SpacingTokens.XSmall
                )
                AvatarSize.Size72 -> GlobalTokens.spacing(
                    GlobalTokens.SpacingTokens.XSmall
                )
            }
        }
    }

    @Composable
    open fun pilePadding(avatarGroupInfo: AvatarGroupInfo): Dp {
        return when (avatarGroupInfo.size) {
            AvatarSize.Size16 -> GlobalTokens.spacing(
                GlobalTokens.SpacingTokens.XXSmall
            )
            AvatarSize.Size20 -> GlobalTokens.spacing(
                GlobalTokens.SpacingTokens.XXXSmall
            )
            AvatarSize.Size24 -> GlobalTokens.spacing(
                GlobalTokens.SpacingTokens.XXXSmall
            )
            AvatarSize.Size32 -> GlobalTokens.spacing(
                GlobalTokens.SpacingTokens.XXXSmall
            )
            AvatarSize.Size40 -> GlobalTokens.spacing(
                GlobalTokens.SpacingTokens.XXXSmall
            )
            AvatarSize.Size56 -> GlobalTokens.spacing(
                GlobalTokens.SpacingTokens.XXSmall
            )
            AvatarSize.Size72 -> GlobalTokens.spacing(
                GlobalTokens.SpacingTokens.XSmall
            )
        }
    }

    @Composable
    open fun avatarSize(avatarInfo: AvatarInfo): IconSize {
        return when (avatarInfo.size) {
            AvatarSize.Size16 -> GlobalTokens.iconSize(GlobalTokens.IconSizeTokens.XXSmall)
            AvatarSize.Size20 -> GlobalTokens.iconSize(GlobalTokens.IconSizeTokens.XSmall)
            AvatarSize.Size24 -> GlobalTokens.iconSize(GlobalTokens.IconSizeTokens.XSmall)
            AvatarSize.Size32 -> GlobalTokens.iconSize(GlobalTokens.IconSizeTokens.Small)
            AvatarSize.Size40 -> GlobalTokens.iconSize(GlobalTokens.IconSizeTokens.Medium)
            AvatarSize.Size56 -> GlobalTokens.iconSize(GlobalTokens.IconSizeTokens.Large)
            AvatarSize.Size72 -> GlobalTokens.iconSize(GlobalTokens.IconSizeTokens.XXXLarge)
        }
    }
}
