package com.vasilyev.documentvalidator.data.source.local.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.vasilyev.documentvalidator.data.source.local.entity.CheckingResultDbo

@Database(
    entities = [CheckingResultDbo::class],
    version = 1,
    exportSchema = false
)
abstract class ValidatorDatabase : RoomDatabase() {
    abstract fun resentChecksDao(): ResentChecksDao
}