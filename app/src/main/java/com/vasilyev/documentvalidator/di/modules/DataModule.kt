package com.vasilyev.documentvalidator.di.modules

import android.content.Context
import androidx.room.Room
import com.skydoves.retrofit.adapters.result.ResultCallAdapterFactory
import com.vasilyev.documentvalidator.data.source.local.room.ValidatorDatabase
import com.vasilyev.documentvalidator.data.source.remote.retrofit.ApiService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface DataModule {

    companion object{

        @Provides
        @Singleton
        fun provideValidatorDatabase(
            @ApplicationContext context: Context
        ) = Room.databaseBuilder(
            context  = context,
            klass = ValidatorDatabase::class.java,
            name = "validator_app.db"
        ).build()

        @Provides
        @Singleton
        fun provideResentChecksDao(
            database: ValidatorDatabase
        ) = database.resentChecksDao()

        @Provides
        @Singleton
        fun provideRetrofit(): Retrofit {
            return Retrofit
                .Builder()
                .baseUrl("https://SOME_BASE_URL")
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(ResultCallAdapterFactory.create())
                .build()
        }

        @Provides
        @Singleton
        fun provideApiService(retrofit: Retrofit): ApiService {
            return retrofit.create(ApiService::class.java)
        }
    }
}