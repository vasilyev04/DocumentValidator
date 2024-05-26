package com.vasilyev.documentvalidator.domain.models

data class CheckingResult(
    val id: Int = UNDEFINED_ID,
    val documentName: String,
    val documentPreview: String,
    val checkStatus: CheckStatus,
    val uploadDate: String
){
    companion object{
        const val UNDEFINED_ID = -1
    }
}