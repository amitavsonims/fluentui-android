//
//  Copyright (c) Microsoft Corporation. All rights reserved.
//  Licensed under the MIT License.
//

package com.microsoft.fluentui.theme.token

import androidx.compose.runtime.compositionLocalOf
import com.microsoft.fluentui.theme.token.controlTokens.*

interface ControlInfo

interface ControlToken

class ControlTokens {

    enum class ControlType {
        Avatar,
        AvatarCarousel,
        AvatarGroup,
        BottomSheet,
        Button,
        CheckBox,
        CircularProgressIndicator,
        ContextualCommandBar,
        Drawer,
        Divider,
        FloatingActionButton,
        LinearProgressIndicator,
        ListItem,
        PersonaChip,
        PillButton,
        PillBar,
        PillSwitch,
        PillTabs,
        RadioButton,
        SearchBarPersonaChip,
        SearchBar,
        Shimmer,
        TabItem,
        ToggleSwitch
    }

    val tokens: TokenSet<ControlType, ControlToken> by lazy {
        TokenSet { token ->
            when (token) {
                ControlType.Avatar -> AvatarTokens()
                ControlType.AvatarCarousel -> AvatarCarouselTokens()
                ControlType.AvatarGroup -> AvatarGroupTokens()
                ControlType.BottomSheet -> BottomSheetTokens()
                ControlType.Button -> ButtonTokens()
                ControlType.CheckBox -> CheckBoxTokens()
                ControlType.CircularProgressIndicator -> CircularProgressIndicatorTokens()
                ControlType.ContextualCommandBar -> ContextualCommandBarTokens()
                ControlType.Drawer -> DrawerTokens()
                ControlType.Divider -> DividerTokens()
                ControlType.FloatingActionButton -> FABTokens()
                ControlType.LinearProgressIndicator -> LinearProgressIndicatorTokens()
                ControlType.ListItem -> ListItemTokens()
                ControlType.PersonaChip -> PersonaChipTokens()
                ControlType.PillButton -> PillButtonTokens()
                ControlType.PillBar -> PillBarTokens()
                ControlType.PillSwitch -> PillSwitchTokens()
                ControlType.PillTabs -> PillTabsTokens()
                ControlType.RadioButton -> RadioButtonTokens()
                ControlType.SearchBarPersonaChip -> SearchBarPersonaChipTokens()
                ControlType.SearchBar -> SearchBarTokens()
                ControlType.Shimmer -> ShimmerTokens()
                ControlType.TabItem -> TabItemTokens()
                ControlType.ToggleSwitch -> ToggleSwitchTokens()
            }
        }
    }

    fun updateTokens(type: ControlType, updatedToken: ControlToken): ControlTokens {
        tokens[type] = updatedToken
        return this
    }

}

internal val LocalControlTokens = compositionLocalOf { ControlTokens() }
