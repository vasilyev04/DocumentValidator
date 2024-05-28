package com.vasilyev.documentvalidator.domain.usecase

import com.vasilyev.documentvalidator.domain.models.CheckingResult
import com.vasilyev.documentvalidator.domain.repository.RecentResultsRepository
import javax.inject.Inject

class GetRecentResultUseCase @Inject constructor(
    private val repository: RecentResultsRepository
) {
    suspend operator fun invoke(id: Int): CheckingResult{
        return repository.getRecentResult(id)
    }
}