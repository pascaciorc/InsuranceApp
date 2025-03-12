package com.pascaciorc.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.pascaciorc.data.entities.PolicyData

@Database(
    entities = [PolicyData::class],
    version = 1,
    exportSchema = false,
)
abstract class InsuranceDb : RoomDatabase() {
    abstract fun insuranceDao(): InsuranceDao
}