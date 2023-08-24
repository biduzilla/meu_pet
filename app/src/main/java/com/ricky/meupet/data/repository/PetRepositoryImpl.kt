package com.ricky.meupet.data.repository

import com.ricky.meupet.data.AppDatabase
import com.ricky.meupet.data.dao.PetDao
import com.ricky.meupet.domain.model.Pet
import com.ricky.meupet.domain.repository.PetRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class PetRepositoryImpl @Inject constructor(private val dao: PetDao) : PetRepository {
    override fun getAllPets(): Flow<List<Pet>> = dao.getAllPets()

    override suspend fun getPetById(petId: String): Pet = dao.getPetById(petId)

    override suspend fun insertPet(pet: Pet) = dao.insertPet(pet)

    override suspend fun updatePet(pet: Pet) = dao.updatePet(pet)

    override suspend fun deletePet(pet: Pet) = dao.deletePet(pet)

    override suspend fun deleteMedicamentoById(petId: String) = dao.deleteMedicamentoById(petId)
}