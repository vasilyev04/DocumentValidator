package com.vasilyev.documentvalidator.di.modules

import com.vasilyev.documentvalidator.data.repository.RecentResultRepoImpl
import com.vasilyev.documentvalidator.data.repository.ValidateDocumentsRepoImpl
import com.vasilyev.documentvalidator.domain.repository.RecentResultsRepository
import com.vasilyev.documentvalidator.domain.repository.ValidateDocumentsRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface DomainModule {

    @Binds
    @Singleton
    fun bindRecentResultRepository(
        impl: RecentResultRepoImpl
    ): RecentResultsRepository

    @Binds
    @Singleton
    fun bindValidateDocumentRepository(
        impl: ValidateDocumentsRepoImpl
    ): ValidateDocumentsRepository
}