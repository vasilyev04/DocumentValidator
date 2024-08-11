package com.vasilyev.documentvalidator.domain.models

//enum class Document {
//    ID_CARD,
//    DRIVER_LICENSE,
//    BIRTH_DOCUMENT,
//    UNDEFINED,
//}
sealed class Document(val pageCount: Int){

    data object IdCard: Document(ID_CARD_PAGES)

    data object DriverLicense: Document(DRIVER_LICENSE_PAGES)

    data object BirthDocument: Document(BIRTH_DOCUMENT_PAGES)

    data object Undefined : Document(UNDEFINED_DOCUMENT_PAGES)

    companion object{
        private const val ID_CARD_PAGES = 2
        private const val DRIVER_LICENSE_PAGES = 2
        private const val BIRTH_DOCUMENT_PAGES = 3
        private const val UNDEFINED_DOCUMENT_PAGES = 0
    }
}