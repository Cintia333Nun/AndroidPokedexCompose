package com.example.androidpokedexcompose.data.local

import android.app.Application
import androidx.room.Room
import androidx.room.RoomDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object Room {
    @Provides
    @Singleton
    fun provideRoomDatabase(application: Application): RoomDB {
        return Room.databaseBuilder(
            application,
            RoomDB::class.java,
            DB.NAME
        ).setJournalMode(RoomDatabase.JournalMode.TRUNCATE)
            .allowMainThreadQueries()
            .fallbackToDestructiveMigration()
            .build()
    }
}