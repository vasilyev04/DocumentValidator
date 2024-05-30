package com.vasilyev.documentvalidator.data.source.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.vasilyev.documentvalidator.domain.models.CheckStatus
import com.vasilyev.documentvalidator.domain.models.Document

@Entity(tableName = "checking_results")
data class CheckingResultDbo(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val documentType: Document,
    val documentPreview: String,
    val checkStatus: CheckStatus,
    val uploadDate: String
)