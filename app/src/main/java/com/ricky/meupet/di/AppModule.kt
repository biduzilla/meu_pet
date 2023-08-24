package com.ricky.meupet.di

import android.content.Context
import androidx.room.Room
import com.ricky.meupet.common.DataStoreUtil
import com.ricky.meupet.data.AppDatabase
import com.ricky.meupet.data.dao.MedicamentoDao
import com.ricky.meupet.data.dao.PetDao
import com.ricky.meupet.data.repository.MedicamentoRepositoryImpl
import com.ricky.meupet.data.repository.PetRepositoryImpl
import com.ricky.meupet.domain.repository.MedicamentoRepository
import com.ricky.meupet.domain.repository.PetRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun providePetDao(database: AppDatabase): PetDao = database.petDao()

    @Singleton
    @Provides
    fun provideMedicamentoDao(database: AppDatabase): MedicamentoDao = database.medicamentoDao()

    @Singleton
    @Provides
    fun providePetRepository(dao: PetDao): PetRepository = PetRepositoryImpl(dao)

    @Singleton
    @Provides
    fun provideMedicamentoRepository(dao: MedicamentoDao): MedicamentoRepository =
        MedicamentoRepositoryImpl(dao)

    @Singleton
    @Provides
    fun provideDataStoreUtil(@ApplicationContext context: Context): DataStoreUtil {
        return DataStoreUtil(context)
    }

    @Singleton
    @Provides
    fun provideAppDatabase(@ApplicationContext context: Context): AppDatabase =
        Room.databaseBuilder(
            context,
            AppDatabase::class.java,
            "app_db"
        ).fallbackToDestructiveMigration()
            .build()
}


