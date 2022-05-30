package com.bristot.tvmaze.series.data.remote.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.bristot.tvmaze.series.data.remote.api.ShowsAPI
import com.bristot.tvmaze.series.data.remote.model.ShowResponse
import com.bristot.tvmaze.series.data.remote.networkmanager.NetworkManager
import com.bristot.tvmaze.series.data.remote.networkmanager.NoConnectionException
import retrofit2.HttpException
import java.io.IOException

private const val DEFAULT_PAGE_INDEX = 1

class ShowPagingSource(
    private val api: ShowsAPI,
    private val networkManager: NetworkManager
) : PagingSource<Int, ShowResponse>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, ShowResponse> {
        return if (networkManager.isConnected()) {
            return try {
                val result = api.getShows(params.key ?: DEFAULT_PAGE_INDEX).body().orEmpty()
                LoadResult.Page(
                    data = result,
                    prevKey = params.key,
                    nextKey = params.key?.plus(1) ?: DEFAULT_PAGE_INDEX.plus(1)
                )
            } catch (e: IOException) {
                LoadResult.Error(e)
            } catch (e: HttpException) {
                LoadResult.Error(e)
            }
        } else {
            LoadResult.Error(NoConnectionException())
        }
    }

    override fun getRefreshKey(state: PagingState<Int, ShowResponse>): Int? {
        return state.anchorPosition?.let { mostRecentlyAccessedIndex ->
            val page = state.closestPageToPosition(mostRecentlyAccessedIndex)
            val refreshKey = page?.prevKey?.plus(1) ?: page?.nextKey?.minus(1)
            refreshKey
        }
    }
}