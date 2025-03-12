package com.pascaciorc.insuranceapp.di.module

import android.content.Context
import androidx.room.Room
import com.pascaciorc.data.db.InsuranceDao
import com.pascaciorc.data.db.InsuranceDb
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DatabaseModule {

    @Provides
    @Singleton
    fun provideDatabase(
        @ApplicationContext context: Context,
    ): InsuranceDb {
        return Room.databaseBuilder(context, InsuranceDb::class.java, "insurance.db").build()
    }

    @Provides
    fun provideDao(db: InsuranceDb): InsuranceDao {
        return db.insuranceDao()
    }
}