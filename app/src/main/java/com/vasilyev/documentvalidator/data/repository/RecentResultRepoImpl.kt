package com.vasilyev.documentvalidator.data.repository

import com.vasilyev.documentvalidator.data.source.local.entity.CheckingResultDbo
import com.vasilyev.documentvalidator.data.source.local.room.ResentChecksDao
import com.vasilyev.documentvalidator.data.source.remote.retrofit.ApiService
import com.vasilyev.documentvalidator.domain.models.CheckStatus
import com.vasilyev.documentvalidator.domain.models.CheckingResult
import com.vasilyev.documentvalidator.domain.repository.RecentResultsRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class RecentResultRepoImpl @Inject constructor(
    private val apiService: ApiService,
    private val checksDao: ResentChecksDao
): RecentResultsRepository {

    override fun getRecentResults(): Flow<List<CheckingResult>> = flow {
        val list = mutableListOf<CheckingResult>()

        for(i in 0 until 15){
            list.add(CheckingResult(
                i,
                "Document name",
                "",
                CheckStatus.SUCCESS,
                "Today"
            ))
        }

        emit(list)
    }

    override suspend fun getRecentResult(id: Int): CheckingResult {
        return CheckingResult(
            id,
            "Document name",
            "",
            CheckStatus.SUCCESS,
            "Today"
        )
    }

    override fun insertRecentResult(recent: CheckingResultDbo): Int {
        TODO("Not yet implemented")
    }
}