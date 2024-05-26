package com.vasilyev.documentvalidator.domain.usecase

import com.vasilyev.documentvalidator.domain.models.CheckingResult
import com.vasilyev.documentvalidator.domain.repository.RecentResultsRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetRecentResultsUseCase @Inject constructor(
    private val repository: RecentResultsRepository
) {
    operator fun invoke(): Flow<List<CheckingResult>> {
        return repository.getRecentResults()
    }
}