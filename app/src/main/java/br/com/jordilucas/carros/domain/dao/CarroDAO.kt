package br.com.jordilucas.carros.domain.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import br.com.jordilucas.carros.domain.Carro

/**
 * Created by jordi on 09/10/17.
 */
@Dao
interface CarroDAO{
    @Query("SELECT * FROM carro where id = :arg0")
    fun getById(id: Long): Carro?
    @Query("SELECT * FROM carro")
    fun findAll(): List<Carro>
    @Insert
    fun insert(carro: Carro)
    @Delete
    fun delete(carro: Carro)
}