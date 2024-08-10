package com.vasilyev.documentvalidator.presentation.models

import com.vasilyev.documentvalidator.domain.models.Document

data class DocumentUI(
    val title: String,
    val icon: Int,
    val documentType: Document
)