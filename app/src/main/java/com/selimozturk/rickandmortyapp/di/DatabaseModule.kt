package com.selimozturk.rickandmortyapp.di

import android.content.Context
import androidx.room.Room
import com.selimozturk.rickandmortyapp.data.local.RickAndMortyDao
import com.selimozturk.rickandmortyapp.data.local.RickAndMortyDatabase
import com.selimozturk.rickandmortyapp.util.Constants
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object DatabaseModule {
    @Provides
    @Singleton
    fun provideRickAndMortyDatabase(@ApplicationContext context: Context): RickAndMortyDatabase {
        return Room.databaseBuilder(
            context,
            RickAndMortyDatabase::class.java,
            Constants.DATABASE_NAME
        ).allowMainThreadQueries().build()
    }

    @Singleton
    @Provides
    fun provideNewsDao(rickAndMortyDatabase: RickAndMortyDatabase): RickAndMortyDao {
        return rickAndMortyDatabase.rickAndMortyDao
    }
}