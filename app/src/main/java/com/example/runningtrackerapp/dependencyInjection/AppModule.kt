package com.example.runningtrackerapp.dependencyInjection

import android.content.Context
import androidx.room.Room
import com.example.runningtrackerapp.database.RunDatabase
import com.example.runningtrackerapp.other.CONSTANTS.RUN_DATABASE_NAME
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Singleton


@Module
@InstallIn(ApplicationComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun provideRunDB(
        @ApplicationContext app: Context
    ) = Room.databaseBuilder(
        app,
        RunDatabase::class.java,
        RUN_DATABASE_NAME
    ).build()

    @Singleton
    @Provides
    fun provideRunDao(db: RunDatabase) = db.getRunDao()
}