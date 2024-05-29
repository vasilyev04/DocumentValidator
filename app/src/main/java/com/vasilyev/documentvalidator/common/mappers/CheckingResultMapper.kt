package com.vasilyev.documentvalidator.common.mappers

import com.vasilyev.documentvalidator.data.source.local.entity.CheckingResultDbo
import com.vasilyev.documentvalidator.data.source.remote.models.CheckStatusDto
import com.vasilyev.documentvalidator.domain.models.CheckStatus
import com.vasilyev.documentvalidator.domain.models.CheckingResult

fun CheckStatusDto.toCheckingResultDbo(
    documentName: String,
    documentPreview: String,
    uploadDate: String
): CheckingResultDbo{
    val status = when(this){
        CheckStatusDto.SUCCESS -> CheckStatus.SUCCESS
        CheckStatusDto.WARNING -> CheckStatus.WARNING
        CheckStatusDto.ERROR -> CheckStatus.ERROR
    }

    return CheckingResultDbo(
        documentName = documentName,
        documentPreview = documentPreview,
        checkStatus = status,
        uploadDate = uploadDate
    )
}

fun CheckingResultDbo.toCheckingResultModel(): CheckingResult{
    return CheckingResult(

    )
}