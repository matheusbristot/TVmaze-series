package com.bristot.tvmaze.series.data.remote

import com.bristot.tvmaze.series.data.remote.datasource.detail.EpisodeRemoteDataSource
import com.bristot.tvmaze.series.data.remote.mapper.EpisodeMapper
import com.bristot.tvmaze.series.data.remote.networkmanager.NetworkManager
import com.bristot.tvmaze.series.data.remote.networkmanager.NoConnectionException
import com.bristot.tvmaze.series.series.model.Episode
import com.bristot.tvmaze.series.series.repository.EpisodeRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class EpisodeRepositoryImpl(
    private val remoteDataSource: EpisodeRemoteDataSource,
    private val episodeMapper: EpisodeMapper,
    private val networkManager: NetworkManager
) : EpisodeRepository {

    override fun getAllEpisodesByShowId(showId: Long): Flow<List<Episode>> {
        return flow {
            try {
                if (networkManager.isConnected()) {
                    val result = remoteDataSource.getAllEpisodesByShowId(showId)
                    if (result.isSuccessful) {
                        emit(
                            episodeMapper(
                                result.body()
                                    ?: throw IllegalStateException("Body is nullable")
                            )
                        )
                    }
                } else {
                    throw NoConnectionException()
                }
            } catch (e: Exception) {
                throw e
            }
        }
    }

    override fun getEpisodeDetails(episodeId: Long): Flow<Episode> {
        return flow {
            try {
                if (networkManager.isConnected()) {
                    val result = remoteDataSource.getEpisodeDetails(episodeId)
                    if (result.isSuccessful) {
                        emit(
                            episodeMapper(
                                result.body()
                                    ?: throw IllegalStateException("Body is nullable")
                            )
                        )
                    }
                } else {
                    throw NoConnectionException()
                }
            } catch (e: Exception) {
                throw e
            }
        }
    }
}