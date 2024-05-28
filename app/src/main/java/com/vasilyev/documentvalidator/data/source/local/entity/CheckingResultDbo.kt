package com.vasilyev.documentvalidator.data.source.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.vasilyev.documentvalidator.domain.models.CheckStatus

@Entity(tableName = "checking_results")
data class CheckingResultDbo(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val documentName: String,
    val documentPreview: String,
    val checkStatus: CheckStatus,
    val uploadDate: String
)