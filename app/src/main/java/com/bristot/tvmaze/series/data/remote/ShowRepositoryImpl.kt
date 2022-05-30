package com.bristot.tvmaze.series.data.remote

import androidx.paging.PagingData
import com.bristot.tvmaze.series.data.remote.datasource.detail.ShowDetailRemoteDataSource
import com.bristot.tvmaze.series.data.remote.datasource.paging.ShowPagingDataSource
import com.bristot.tvmaze.series.data.remote.datasource.search.SearchShowRemoteDataSource
import com.bristot.tvmaze.series.data.remote.mapper.SearchShowMapper
import com.bristot.tvmaze.series.data.remote.mapper.ShowMapper
import com.bristot.tvmaze.series.data.remote.networkmanager.NetworkManager
import com.bristot.tvmaze.series.data.remote.networkmanager.NoConnectionException
import com.bristot.tvmaze.series.series.model.SearchShow
import com.bristot.tvmaze.series.series.model.Show
import com.bristot.tvmaze.series.series.repository.ShowRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class ShowRepositoryImpl(
    private val showPagingRemoteDataSource: ShowPagingDataSource,
    private val searchShowRemoteDataSource: SearchShowRemoteDataSource,
    private val detailRemoteDataSource: ShowDetailRemoteDataSource,
    private val searchShowMapper: SearchShowMapper,
    private val showMapper: ShowMapper,
    private val networkManager: NetworkManager
) : ShowRepository {

    override fun getShowsByName(name: String): Flow<List<SearchShow>> {
        return if (networkManager.isConnected()) {
            flow {
                try {
                    val result = searchShowRemoteDataSource.getShowByName(name)
                    if (result.isSuccessful) {
                        val shows = result.body().orEmpty()
                        emit(searchShowMapper(shows))
                    }
                } catch (e: Exception) {
                    throw e
                }

            }
        } else {
            throw NoConnectionException()
        }
    }

    override fun getShowById(id: Long): Flow<Show> {
        return flow {
            try {
                if (networkManager.isConnected()) {
                    val result = detailRemoteDataSource.getShowById(id)
                    if (result.isSuccessful) {
                        emit(
                            showMapper(
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

    override fun getShows(): Flow<PagingData<Show>> = showPagingRemoteDataSource.getShows()
}
