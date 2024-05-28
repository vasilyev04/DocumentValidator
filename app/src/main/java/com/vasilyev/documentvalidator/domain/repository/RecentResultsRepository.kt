package com.vasilyev.documentvalidator.domain.repository

import com.vasilyev.documentvalidator.domain.models.CheckingResult
import kotlinx.coroutines.flow.Flow

interface RecentResultsRepository {
    fun getRecentResults(): Flow<List<CheckingResult>>

    suspend fun getRecentResult(id: Int): CheckingResult
}