package com.example.rickandmorty.data.base

import android.net.Uri
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.rickandmorty.data.mapper.Mapper
import retrofit2.HttpException
import java.io.IOException

abstract class BasePagingSource<ValueDto : Mapper<Value>, Value : Any>(
    private val request: suspend (position: Int) -> PaginationInfo<ValueDto>,
) : PagingSource<Int, Value>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Value> {
        val position = params.key ?: Companion.BASE_STARTING_INDEX
        return try {
            val response = request(position)
            val nextPage = when (response.info.next) {
                null -> null
                else -> Uri.parse(response.info.next).getQueryParameter("page")?.toInt()
            }

            LoadResult.Page(
                data = response.results.map { it.toDomain() },
                prevKey = null,
                nextKey = nextPage
            )
        } catch (exception: IOException) {
            LoadResult.Error(exception)
        } catch (exception: HttpException) {
            LoadResult.Error(exception)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, Value>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }
    }

    companion object {
        private const val BASE_STARTING_INDEX = 1
    }
}