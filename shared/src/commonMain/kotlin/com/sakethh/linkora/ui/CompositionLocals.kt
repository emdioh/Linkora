package com.sakethh.linkora.ui

import LocalizedStrings
import androidx.compose.runtime.compositionLocalOf
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.navigation.NavHostController
import com.sakethh.linkora.domain.Platform

val LocalNavController =
    staticCompositionLocalOf<NavHostController> {
        error("LocalNavController isn't provided")
    }

val LocalFabController =
    staticCompositionLocalOf<FabStateController> {
        error("LocalFabController isn't provided")
    }

val LocalPlatform =
    compositionLocalOf<Platform> {
        error("LocalPlatform isn't provided")
    }

val LocalizedStrings =
    compositionLocalOf<LocalizedStrings> {
        error("LocalizedStrings isn't provided")
    }

/**
 * Lets any screen trigger a manual push/pull with the configured sync server. `null` when no host
 * (e.g. share-to-save) provides it, in which case sync UI is hidden.
 */
data class ForceSyncController(val isSyncing: Boolean, val forceSync: () -> Unit)

val LocalForceSyncController = compositionLocalOf<ForceSyncController?> { null }
