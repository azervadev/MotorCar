package com.azerva.motorcar.model

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update

@Dao
interface CarDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertCar(car : CarVo) : Long

    @Update
    fun updateCar(car : CarVo)

    @Delete
    fun deleteCar(car : CarVo)

    @Query("Select * From cars")
    fun getAllCars() : List<CarVo>

    @Query("DELETE FROM cars WHERE id = :carId")
    fun deleteCarById(carId: Long)

}