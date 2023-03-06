package com.example.rickandmorty.data.api

import com.example.rickandmorty.data.model.CharacterEntity
import retrofit2.http.GET
import retrofit2.http.Query

interface RickAndMortyApi {

    @GET("character")
    fun getCharacters(
        @Query("name") name:String,
        @Query("status") status:String,
        @Query("species") species:String,
        @Query("gender") gender:String,
    ):List<CharacterEntity.Result>
}