package com.eidorian.weedtacora.di

import android.content.Context
import androidx.room.Room
import com.eidorian.weedtacora.bussinesslogic.usecase.GetAllGrowthsUseCase
import com.eidorian.weedtacora.bussinesslogic.usecase.GetAllGrowthsUseCaseImpl
import com.eidorian.weedtacora.data.dao.GrowthDao
import com.eidorian.weedtacora.data.database.AppDatabase
import com.eidorian.weedtacora.data.repository.GrowthRepository
import com.eidorian.weedtacora.data.repository.GrowthRepositoryImpl
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
    abstract fun providesGetGrowthUseCase(useCase: GetAllGrowthsUseCaseImpl): GetAllGrowthsUseCase
}
