package com.vasilyev.documentvalidator.data.source.local.entity

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.vasilyev.documentvalidator.data.source.local.entity.converters.DocumentTypeConverter
import com.vasilyev.documentvalidator.domain.models.CheckStatus
import com.vasilyev.documentvalidator.domain.models.Document

@Entity(tableName = "checking_results")
@TypeConverters(DocumentTypeConverter::class)
data class CheckingResultDbo(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val documentType: DocumentDbo,
    val documentPreview: String,
    val checkStatus: CheckStatus,
    val uploadDate: String
)