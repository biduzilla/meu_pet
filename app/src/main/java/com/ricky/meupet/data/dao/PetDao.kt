package com.ricky.meupet.data.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import androidx.room.Update
import com.ricky.meupet.domain.model.Pet
import com.ricky.meupet.domain.model.relationship.PetWithMedicamentos
import kotlinx.coroutines.flow.Flow
@Dao
interface PetDao {

    @Query("SELECT * FROM PET")
    fun getAllPets():Flow<List<Pet>>

    @Query("SELECT * FROM PET WHERE id = :petId")
    suspend fun getPetById(petId:String):Pet

    @Transaction
    @Query("DELETE FROM PET WHERE id = :petId")
    suspend fun deletePetById(petId:String)

    @Transaction
    @Query("SELECT * FROM PET WHERE id = :petId")
    suspend fun getPetWithMedicamentoById(petId:String):PetWithMedicamentos

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertPet(pet:Pet)

    @Update(onConflict = OnConflictStrategy.REPLACE)
    suspend fun updatePet(pet:Pet)

    @Delete
    suspend fun deletePet(pet:Pet)

    @Query("DELETE FROM PET WHERE id = :petId")
    suspend fun deleteMedicamentoById(petId:String)
}