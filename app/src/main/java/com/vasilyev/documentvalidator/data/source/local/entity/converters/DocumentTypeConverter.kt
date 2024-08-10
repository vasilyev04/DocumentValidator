package com.vasilyev.documentvalidator.data.source.local.entity.converters

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.vasilyev.documentvalidator.data.source.local.entity.DocumentDbo


class DocumentTypeConverter {
    private val gson = Gson()

    @TypeConverter
    fun fromDocumentDbo(documentDbo: DocumentDbo): String {
        return gson.toJson(documentDbo)
    }

    @TypeConverter
    fun toDocumentDbo(documentDboString: String): DocumentDbo {
        val type = object : TypeToken<DocumentDbo>() {}.type
        return gson.fromJson(documentDboString, type)
    }
}