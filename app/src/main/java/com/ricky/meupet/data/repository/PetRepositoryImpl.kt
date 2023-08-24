package com.ricky.meupet.data.repository

import com.ricky.meupet.data.AppDatabase
import com.ricky.meupet.domain.model.Pet
import com.ricky.meupet.domain.repository.PetRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class PetRepositoryImpl @Inject constructor(private val dao: AppDatabase) : PetRepository {
    override fun getAllPets(): Flow<List<Pet>> = dao.petDao().getAllPets()

    override suspend fun getPetById(petId: String): Pet = dao.petDao().getPetById(petId)

    override suspend fun insertPet(pet: Pet) = dao.petDao().insertPet(pet)

    override suspend fun updatePet(pet: Pet) = dao.petDao().updatePet(pet)

    override suspend fun deletePet(pet: Pet) = dao.petDao().deletePet(pet)

    override suspend fun deleteMedicamentoById(petId: String) =
        dao.petDao().deleteMedicamentoById(petId)
}