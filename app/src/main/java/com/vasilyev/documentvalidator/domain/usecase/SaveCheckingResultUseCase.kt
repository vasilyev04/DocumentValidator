package com.vasilyev.documentvalidator.domain.usecase

import com.vasilyev.documentvalidator.domain.models.CheckingResult
import com.vasilyev.documentvalidator.domain.repository.RecentResultsRepository
import javax.inject.Inject

class SaveCheckingResultUseCase @Inject constructor(
    private val repository: RecentResultsRepository
) {
    suspend operator fun invoke(checkingResult: CheckingResult): Int{
        return repository.addRecentResult(checkingResult)
    }
}