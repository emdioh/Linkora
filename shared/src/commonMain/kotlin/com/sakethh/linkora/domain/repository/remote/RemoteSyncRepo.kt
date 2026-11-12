package com.sakethh.linkora.domain.repository.remote

import com.sakethh.linkora.domain.Result
import com.sakethh.linkora.domain.dto.server.Correlation
import kotlinx.coroutines.channels.SendChannel
import kotlinx.coroutines.flow.Flow

interface RemoteSyncRepo {
    suspend fun readSocketEvents(currentCorrelation: Correlation)

    suspend fun applyUpdatesBasedOnRemoteTombstones(timeStampAfter: Long): Flow<Result<Unit>>

    suspend fun <T> SendChannel<Result<T>>.pushPendingSyncQueueToServer(): Flow<Result<Unit>>

    suspend fun applyUpdatesFromRemote(timeStampAfter: Long): Flow<Result<Unit>>

    suspend fun <T> SendChannel<Result<T>>.pushNonSyncedDataToServer()

    /**
     * Sends every local link to the server, regardless of its sync status:
     * - links that were never synced are created (along with any other never-synced items),
     * - links the app thinks are synced but that no longer exist on the server are re-created,
     * - all other links are overwritten on the server with the local version.
     */
    fun forcePushAllLinksToServer(): Flow<Result<Unit>>

    suspend fun deleteEverything(deleteOnRemote: Boolean): Flow<Result<Unit>>
}
