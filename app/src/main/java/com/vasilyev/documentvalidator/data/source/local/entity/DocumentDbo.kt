package com.vasilyev.documentvalidator.data.source.local.entity


sealed class DocumentDbo(
    val type: String,
    val pageCount: Int
){
    data object IdCard: DocumentDbo(ID_CARD_TYPE, ID_CARD_PAGES)
    data object DriverLicense: DocumentDbo(DRIVER_LICENSE_TYPE, DRIVER_LICENSE_PAGES)
    data object BirthDocument: DocumentDbo(BIRTH_DOCUMENT_TYPE, BIRTH_DOCUMENT_PAGES)
    data object Undefined : DocumentDbo(UNDEFINED_TYPE, UNDEFINED_DOCUMENT_PAGES)

    companion object{
        private const val ID_CARD_PAGES = 2
        private const val DRIVER_LICENSE_PAGES = 2
        private const val BIRTH_DOCUMENT_PAGES = 3
        private const val UNDEFINED_DOCUMENT_PAGES = 0

        private const val ID_CARD_TYPE = "IdCard"
        private const val DRIVER_LICENSE_TYPE = "DriverLicence"
        private const val BIRTH_DOCUMENT_TYPE = "BirthDocument"
        private const val UNDEFINED_TYPE = "Undefined"
    }
}