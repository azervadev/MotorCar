package com.azerva.motorcar.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable
import java.time.LocalDate

@Entity(tableName = "cars")
data class CarVo(
    @PrimaryKey (autoGenerate = true)
    val id : Long = 0,
    val mark : String,
    val model : String,
    val kilometers : String,
    val date : String,
    val cc : String,
    val cv : String,
    val price : String,
    val isSold : Int = 0

): Serializable {

    fun isCarValid() = mark.isNotEmpty() && model.isNotEmpty() && kilometers.isNotEmpty()
            && cc.isNotEmpty() && cv.isNotEmpty() && price.isNotEmpty()
}