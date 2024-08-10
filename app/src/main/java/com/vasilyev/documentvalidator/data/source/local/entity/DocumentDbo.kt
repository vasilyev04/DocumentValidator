package com.vasilyev.documentvalidator.data.source.local.entity


sealed class DocumentDbo(val pageCount: Int){
    data object IdCard: DocumentDbo(ID_CARD_PAGES)
    data object DriverLicense: DocumentDbo(DRIVER_LICENSE_PAGES)
    data object BirthDocument: DocumentDbo(BIRTH_DOCUMENT_PAGES)
    data object Undefined : DocumentDbo(UNDEFINED_DOCUMENT_PAGES)

    companion object{
        private const val ID_CARD_PAGES = 2
        private const val DRIVER_LICENSE_PAGES = 2
        private const val BIRTH_DOCUMENT_PAGES = 3
        private const val UNDEFINED_DOCUMENT_PAGES = 0
    }
}