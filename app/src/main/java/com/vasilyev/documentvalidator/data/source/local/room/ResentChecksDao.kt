package com.vasilyev.documentvalidator.data.source.local.room

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.vasilyev.documentvalidator.data.source.local.entity.CheckingResultDbo
import com.vasilyev.documentvalidator.domain.models.CheckingResult

@Dao
interface ResentChecksDao {
    @Query("SELECT * FROM checking_results")
    fun getResults(): LiveData<List<CheckingResultDbo>>

    @Query("SELECT * FROM checking_results WHERE id=:id")
    fun getResult(id: Int): CheckingResultDbo

    @Insert
    fun addCheckingResult(checkingResult: CheckingResultDbo): Long

    @Query("DELETE FROM checking_results WHERE id=:id")
    fun deleteCheckingResult(id: Int)
}