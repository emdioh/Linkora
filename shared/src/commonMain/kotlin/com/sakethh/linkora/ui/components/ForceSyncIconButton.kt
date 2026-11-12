package com.sakethh.linkora.ui.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CloudSync
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.pointer.PointerIcon
import androidx.compose.ui.input.pointer.pointerHoverIcon
import androidx.compose.ui.unit.dp
import com.sakethh.linkora.domain.AppPreferences
import com.sakethh.linkora.ui.LocalForceSyncController
import com.sakethh.linkora.ui.LocalizedStrings
import com.sakethh.linkora.ui.utils.pressScaleEffect
import com.sakethh.linkora.utils.isServerConfigured
import com.sakethh.linkora.utils.supportsWideDisplay

/**
 * One-tap "sync now" button for top bars. Only shown on narrow layouts, since wide layouts already
 * have the same button in the navigation rail.
 */
@Composable
fun ForceSyncIconButton(preferences: AppPreferences) {
    val controller = LocalForceSyncController.current ?: return
    if (!preferences.isServerConfigured() || supportsWideDisplay()) return
    Box(contentAlignment = Alignment.Center) {
        IconButton(
            enabled = !controller.isSyncing,
            modifier = Modifier.pointerHoverIcon(icon = PointerIcon.Hand).pressScaleEffect(),
            onClick = controller.forceSync,
        ) {
            Icon(
                imageVector = Icons.Default.CloudSync,
                contentDescription = LocalizedStrings.current.InitiateManualSync,
            )
        }
        if (controller.isSyncing) {
            CircularProgressIndicator(modifier = Modifier.size(36.dp), strokeWidth = 2.dp)
        }
    }
}
