package com.berkaysazak.besinlerkitabi.servis

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.berkaysazak.besinlerkitabi.model.Besin
@Dao
interface BesinDAO {
    //Data Acess Object

    @Insert //Veriyi ekledik şimdi veri çekme işlemini Query ile yapacağız.
    suspend fun insertAll(vararg besin : Besin): List<Long>

    @Query("SELECT * FROM besin")
    suspend fun getAllBesin() : List<Besin>

    @Query("SELECT * FROM besin WHERE uuid = :besinId")
    suspend fun getBesin(besinId : Int) : Besin
    @Query("DELETE FROM Besin")
    suspend fun deleteAllBesin()  //Tüm besinleri silmek için bu komut kullanılır eğer ki bir besini silmek istiyorsak
    //uuid sini üstteki gibi isteyerek silebiliriz
}