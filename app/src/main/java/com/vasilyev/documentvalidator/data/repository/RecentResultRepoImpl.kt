package com.vasilyev.documentvalidator.data.repository

import com.vasilyev.documentvalidator.common.mappers.toCheckingResultDbo
import com.vasilyev.documentvalidator.common.mappers.toCheckingResultModel
import com.vasilyev.documentvalidator.common.mappers.toCheckingResultModelList
import com.vasilyev.documentvalidator.data.source.local.entity.CheckingResultDbo
import com.vasilyev.documentvalidator.data.source.local.room.ResentChecksDao
import com.vasilyev.documentvalidator.data.source.remote.retrofit.ApiService
import com.vasilyev.documentvalidator.domain.models.CheckStatus
import com.vasilyev.documentvalidator.domain.models.CheckingResult
import com.vasilyev.documentvalidator.domain.repository.RecentResultsRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class RecentResultRepoImpl @Inject constructor(
    private val checksDao: ResentChecksDao
): RecentResultsRepository {

    override fun getRecentResults(): Flow<List<CheckingResult>> = flow {
        checksDao.getResults().collect{ list ->
            emit(list.toCheckingResultModelList())
        }
    }

    override suspend fun getRecentResult(id: Int): CheckingResult {
        val checkingResultDbo = checksDao.getResult(id)

        return checkingResultDbo.toCheckingResultModel()
    }

    override fun addRecentResult(recent: CheckingResult): Int {
        return checksDao.addCheckingResult(recent.toCheckingResultDbo()).toInt()
    }
}