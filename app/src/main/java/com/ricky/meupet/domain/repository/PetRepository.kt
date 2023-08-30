package com.ricky.meupet.domain.repository

import com.ricky.meupet.domain.model.Pet
import com.ricky.meupet.domain.model.relationship.PetWithMedicamentos
import kotlinx.coroutines.flow.Flow

interface PetRepository {
    fun getAllPets(): Flow<List<Pet>>
    suspend fun getPetById(petId: String): Pet
    suspend fun insertPet(pet: Pet)
    suspend fun updatePet(pet: Pet)
    suspend fun deletePet(pet: Pet)
    suspend fun deleteMedicamentoById(petId: String)
    suspend fun getPetWithMedicamentoById(petId:String): PetWithMedicamentos
}