package com.vasilyev.documentvalidator.data.repository

import android.text.Editable
import android.text.TextWatcher
import com.vasilyev.documentvalidator.domain.models.CheckStatus
import com.vasilyev.documentvalidator.domain.models.CheckingResult
import com.vasilyev.documentvalidator.domain.repository.RecentResultsRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch
import javax.inject.Inject
import javax.inject.Singleton

class RecentResultRepositoryImpl @Inject constructor(): RecentResultsRepository {

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
}