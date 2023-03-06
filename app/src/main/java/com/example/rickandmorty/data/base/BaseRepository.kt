package com.example.rickandmorty.data.base

import androidx.paging.Pager
import androidx.paging.PagingConfig
import com.example.rickandmorty.data.mapper.Mapper

open class BaseRepository() {

    companion object {
        internal fun <ValueDto : Mapper<Value>, Value : Any> doPagingRequest(
            pagingSource: BasePagingSource<ValueDto, Value>,
            pageSize: Int = 10,
            prefetchDistance: Int = pageSize,
            enablePlaceholders: Boolean = true,
            initialLoadSize: Int = pageSize * 3,
            maxSize: Int = Int.MAX_VALUE,
            jumpThreshold: Int = Int.MIN_VALUE
        ) = Pager(
            config = PagingConfig(
                pageSize,
                prefetchDistance,
                enablePlaceholders,
                initialLoadSize,
                maxSize,
                jumpThreshold
            ),
            pagingSourceFactory = {
                pagingSource
            }
        ).flow
    }
}

