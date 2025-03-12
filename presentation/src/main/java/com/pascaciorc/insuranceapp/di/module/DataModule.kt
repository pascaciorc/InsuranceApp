package com.pascaciorc.insuranceapp.di.module

import com.pascaciorc.data.db.InsuranceDao
import com.pascaciorc.data.repository.PolicyDataSource
import com.pascaciorc.data.repository.PolicyDataSourceImpl
import com.pascaciorc.data.repository.PolicyRepositoryImpl
import com.pascaciorc.domain.repository.PoliciesRepository
import com.pascaciorc.domain.usecase.CreatePolicyUseCase
import com.pascaciorc.domain.usecase.GetPoliciesUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DataModule {

    @Provides
    @Singleton
    fun provideRepository(
        dataSource: PolicyDataSource
    ): PoliciesRepository {
        return PolicyRepositoryImpl(dataSource)
    }

    @Provides
    @Singleton
    fun provideDataSource(
        dao: InsuranceDao
    ): PolicyDataSource {
        return PolicyDataSourceImpl(dao)
    }

    @Provides
    fun provideGetEntriesUseCase(repository: PoliciesRepository) = GetPoliciesUseCase(repository)

    @Provides
    fun provideCreatePolicyUseCase(repository: PoliciesRepository) = CreatePolicyUseCase(repository)
}