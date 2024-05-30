package com.vasilyev.documentvalidator.common.mappers

import com.vasilyev.documentvalidator.data.source.local.entity.CheckingResultDbo
import com.vasilyev.documentvalidator.data.source.remote.models.CheckStatusDto
import com.vasilyev.documentvalidator.domain.models.CheckStatus
import com.vasilyev.documentvalidator.domain.models.CheckingResult
import com.vasilyev.documentvalidator.domain.models.Document

fun CheckStatusDto.toCheckingResultDbo(
    documentType: Document,
    documentPreview: String,
    uploadDate: String
): CheckingResultDbo{
    val status = when(this){
        CheckStatusDto.SUCCESS -> CheckStatus.SUCCESS
        CheckStatusDto.WARNING -> CheckStatus.WARNING
        CheckStatusDto.ERROR -> CheckStatus.ERROR
    }

    return CheckingResultDbo(
        documentType = documentType,
        documentPreview = documentPreview,
        checkStatus = status,
        uploadDate = uploadDate
    )
}

fun CheckingResultDbo.toCheckingResultModel(): CheckingResult{
    return CheckingResult(
        id = id,
        documentType = documentType,
        documentPreview = documentPreview,
        checkStatus = checkStatus,
        uploadDate =  uploadDate
    )
}

fun CheckingResult.toCheckingResultDbo(): CheckingResultDbo{
    return CheckingResultDbo(
        id = id,
        documentType = documentType,
        documentPreview = documentPreview,
        checkStatus = checkStatus,
        uploadDate =  uploadDate
    )
}

fun List<CheckingResultDbo>.toCheckingResultModelList(): List<CheckingResult>{
    return map {
        it.toCheckingResultModel()
    }
}