package com.vasilyev.documentvalidator.common.mappers

import com.vasilyev.documentvalidator.domain.models.Document


fun String.toDocument(): Document {
    return when(this){
        Document.IdCard.toString() -> {
            Document.IdCard
        }

        Document.BirthDocument.toString() -> {
            Document.BirthDocument
        }

        Document.DriverLicense.toString() -> {
            Document.DriverLicense
        }

        else -> {
            Document.Undefined
        }
    }
}