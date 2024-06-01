package com.vasilyev.documentvalidator.data.source.local.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.vasilyev.documentvalidator.data.source.local.entity.CheckingResultDbo
import kotlinx.coroutines.flow.Flow


@Dao
interface ResentChecksDao {
    @Query("SELECT * FROM checking_results")
    fun getCheckingResults(): Flow<List<CheckingResultDbo>>

    @Query("SELECT * FROM checking_results WHERE id=:id")
    fun getCheckingResult(id: Int): CheckingResultDbo

    @Insert
    fun addCheckingResult(checkingResult: CheckingResultDbo): Long

    @Query("DELETE FROM checking_results WHERE id=:id")
    fun deleteCheckingResult(id: Int)
}