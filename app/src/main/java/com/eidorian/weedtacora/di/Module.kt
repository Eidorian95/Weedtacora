package com.eidorian.weedtacora.di

import android.content.Context
import androidx.room.Room
import com.eidorian.weedtacora.bussinesslogic.usecase.CreateBinnacleUseCase
import com.eidorian.weedtacora.bussinesslogic.usecase.CreateGrowthUseCase
import com.eidorian.weedtacora.bussinesslogic.usecase.impl.CreateGrowthUseCaseImpl
import com.eidorian.weedtacora.bussinesslogic.usecase.GetAllGrowthsUseCase
import com.eidorian.weedtacora.bussinesslogic.usecase.GetGrowthDetailsUseCase
import com.eidorian.weedtacora.bussinesslogic.usecase.impl.CreateBinnacleUseCaseImpl
import com.eidorian.weedtacora.bussinesslogic.usecase.impl.GetAllGrowthsUseCaseImpl
import com.eidorian.weedtacora.bussinesslogic.usecase.impl.GetGrowthDetailsUseCaseImpl
import com.eidorian.weedtacora.data.dao.GrowthDao
import com.eidorian.weedtacora.data.database.AppDatabase
import com.eidorian.weedtacora.data.repository.BinnacleRepository
import com.eidorian.weedtacora.data.repository.GrowthRepository
import com.eidorian.weedtacora.data.repository.impl.BinnacleRepositoryImpl
import com.eidorian.weedtacora.data.repository.impl.GrowthRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import javax.inject.Singleton


@InstallIn(SingletonComponent::class)
@Module
class Module {
    @Provides
    @Singleton
    fun provideAppDatabase(@ApplicationContext appContext: Context): AppDatabase {
        return Room.databaseBuilder(
            appContext,
            AppDatabase::class.java,
            "wdd_data_base"
        ).allowMainThreadQueries().build()
    }

    @Provides
    fun provideGrowthDao(appDatabase: AppDatabase): GrowthDao {
        return appDatabase.growthDao()
    }

    @IoDispatcher
    @Provides
    fun providesIoDispatcher(): CoroutineDispatcher = Dispatchers.IO

    @MainDispatcher
    @Provides
    fun providesMainDispatcher(): CoroutineDispatcher = Dispatchers.Main
}

@InstallIn(SingletonComponent::class)
@Module
abstract class BindsModule {
    @Binds
    abstract fun providesRepository(repository: GrowthRepositoryImpl): GrowthRepository

    @Binds
    abstract fun providesBinnacleRepository(repository: BinnacleRepositoryImpl): BinnacleRepository

    @Binds
    abstract fun providesGetGrowthUseCase(useCase: GetAllGrowthsUseCaseImpl): GetAllGrowthsUseCase

    @Binds
    abstract fun providesCreateGrowthUseCase(useCase: CreateGrowthUseCaseImpl): CreateGrowthUseCase

    @Binds
    abstract fun providesCreateBinnacleUseCase(useCase: CreateBinnacleUseCaseImpl): CreateBinnacleUseCase

    @Binds
    abstract fun providesGetGrowthDetailsUseCase(useCase: GetGrowthDetailsUseCaseImpl): GetGrowthDetailsUseCase
}
