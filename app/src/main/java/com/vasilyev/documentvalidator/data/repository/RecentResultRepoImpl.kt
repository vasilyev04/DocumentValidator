package com.vasilyev.documentvalidator.data.repository

import com.vasilyev.documentvalidator.common.mappers.toCheckingResultDbo
import com.vasilyev.documentvalidator.common.mappers.toCheckingResultModel
import com.vasilyev.documentvalidator.data.source.local.room.ResentChecksDao
import com.vasilyev.documentvalidator.domain.models.CheckStatus
import com.vasilyev.documentvalidator.domain.models.CheckingResult
import com.vasilyev.documentvalidator.domain.models.Document
import com.vasilyev.documentvalidator.domain.repository.RecentResultsRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class RecentResultRepoImpl @Inject constructor(
    private val checksDao: ResentChecksDao
): RecentResultsRepository {

    override fun getRecentResults(): Flow<List<CheckingResult>> = flow {
//        checksDao.getResults().collect{ list ->
//            emit(list.toCheckingResultModelList())
//        }

        val list = mutableListOf<CheckingResult>()

        for(i in 0..15){
            list.add(CheckingResult(
                id = i,
                documentType = Document.ID_CARD,
                checkStatus = CheckStatus.SUCCESS,
                documentPreview = "",
                uploadDate = "Cегодня"
            ))
        }

        emit(list)
    }

    override suspend fun getRecentResult(id: Int): CheckingResult {
        val checkingResultDbo = checksDao.getCheckingResult(id)

        return checkingResultDbo.toCheckingResultModel()
    }

    override fun addRecentResult(recent: CheckingResult): Int {
        return checksDao.addCheckingResult(recent.toCheckingResultDbo()).toInt()
    }
}