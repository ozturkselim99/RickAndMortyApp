package com.selimozturk.rickandmortyapp.di

import com.selimozturk.rickandmortyapp.data.network.RickAndMortyApi
import com.selimozturk.rickandmortyapp.util.Constants
import com.squareup.moshi.Moshi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {
    @Singleton
    @Provides
    fun provideMoshi(): Moshi {
        return Moshi.Builder().build()
    }
    @Singleton
    @Provides
    fun provideRetrofit(moshi: Moshi): Retrofit.Builder {
        return Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(MoshiConverterFactory.create())
    }
    @Singleton
    @Provides
    fun provideRickAndMortyApi(retrofit: Retrofit.Builder): RickAndMortyApi {
        return retrofit.build().create(RickAndMortyApi::class.java)
    }
}