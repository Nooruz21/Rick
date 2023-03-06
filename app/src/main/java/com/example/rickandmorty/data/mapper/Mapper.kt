package com.example.rickandmorty.data.mapper

interface Mapper<T> {
    fun toDomain(): T
}