package com.ricky.meupet.data.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import androidx.room.Update
import com.ricky.meupet.domain.model.Pet
import kotlinx.coroutines.flow.Flow
@Dao
interface PetDao {

    @Transaction
    @Query("SELECT * FROM PET")
    fun getAllPets():Flow<List<Pet>>

    @Transaction
    @Query("SELECT * FROM PET WHERE id = :petId")
    suspend fun getPetById(petId:String):Pet

    @Transaction
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertPet(pet:Pet)

    @Transaction
    @Update(onConflict = OnConflictStrategy.REPLACE)
    suspend fun updatePet(pet:Pet)

    @Transaction
    @Delete
    suspend fun deletePet(pet:Pet)

    @Transaction
    @Query("DELETE FROM PET WHERE id = :petId")
    suspend fun deleteMedicamentoById(petId:String)
}