package com.example.rickandmorty.data.base


data class PaginationInfo<T>(
    val info: BaseInfo,
    val results: List<T>
)
data class BaseInfo(
    val count: Int,
    val pages: Int,
    val next: String?,
    val prev: String?
)