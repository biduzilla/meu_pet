package com.ricky.meupet.data.dao

import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.ricky.meupet.domain.model.Pet
import kotlinx.coroutines.flow.Flow

interface PetDao {

    @Query("SELECT * FROM PET")
    fun getAllPets():Flow<List<Pet>>

    @Query("SELECT * FROM PET WHERE id = :petId")
    suspend fun getPetById(petId:String):Pet

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertPet(pet:Pet)

    @Update(onConflict = OnConflictStrategy.REPLACE)
    suspend fun updatePet(pet:Pet)

    @Delete
    suspend fun deletePet(pet:Pet)

    @Query("DELETE FROM PET WHERE id = :petId")
    suspend fun deleteMedicamentoById(petId:String)
}