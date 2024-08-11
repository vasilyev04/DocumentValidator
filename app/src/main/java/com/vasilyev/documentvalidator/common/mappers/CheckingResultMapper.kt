package com.vasilyev.documentvalidator.common.mappers

import com.vasilyev.documentvalidator.data.source.local.entity.CheckingResultDbo
import com.vasilyev.documentvalidator.data.source.local.entity.DocumentDbo
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
        documentType = documentType.toDocumentDbo(),
        documentPreview = documentPreview,
        checkStatus = status,
        uploadDate = uploadDate
    )
}

fun Document.toDocumentDbo(): DocumentDbo{
    return when(this){
        is Document.IdCard -> DocumentDbo.IdCard
        is Document.BirthDocument -> DocumentDbo.BirthDocument
        is Document.DriverLicense -> DocumentDbo.DriverLicense
        is Document.Undefined -> DocumentDbo.Undefined
    }
}

fun DocumentDbo.toDocument(): Document{
    return when(this){
        is DocumentDbo.IdCard -> Document.IdCard
        is DocumentDbo.BirthDocument -> Document.BirthDocument
        is DocumentDbo.DriverLicense -> Document.DriverLicense
        is DocumentDbo.Undefined -> Document.Undefined
    }
}

fun CheckingResultDbo.toCheckingResultModel(): CheckingResult{
    return CheckingResult(
        id = id,
        documentType = documentType.toDocument(),
        documentPreview = documentPreview,
        checkStatus = checkStatus,
        uploadDate =  uploadDate
    )
}

fun CheckingResult.toCheckingResultDbo(): CheckingResultDbo{
    return CheckingResultDbo(
        id = id,
        documentType = documentType.toDocumentDbo(),
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
