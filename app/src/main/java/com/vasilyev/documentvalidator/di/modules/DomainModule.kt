package com.vasilyev.documentvalidator.di.modules

import com.vasilyev.documentvalidator.data.repository.RecentResultRepositoryImpl
import com.vasilyev.documentvalidator.domain.repository.RecentResultsRepository
import com.vasilyev.documentvalidator.domain.usecase.GetRecentResultsUseCase
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface DomainModule {
    @Binds
    @Singleton
    fun bindRecentResultRepository(
        impl: RecentResultRepositoryImpl
    ): RecentResultsRepository
}