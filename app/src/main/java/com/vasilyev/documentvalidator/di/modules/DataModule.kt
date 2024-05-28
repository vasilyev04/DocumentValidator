package com.vasilyev.documentvalidator.di.modules

import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase
import com.vasilyev.documentvalidator.data.source.local.room.ValidatorDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface DataModule {


    companion object{

        @Provides
        @Singleton
        fun validatorDatabase(
            @ApplicationContext context: Context
        ) = Room.databaseBuilder(
            context  = context,
            klass = ValidatorDatabase::class.java,
            name = "validator_app.db"
        ).build()

        @Provides
        @Singleton
        fun resentCheckDao(
            database: ValidatorDatabase
        ) = database.resentChecksDao()

    }
}