package br.com.jordilucas.carros.domain.dao

import androidx.room.Database
import androidx.room.RoomDatabase
import br.com.jordilucas.carros.domain.Carro

/**
 * Created by jordi on 09/10/17.
 */
@Database(entities = arrayOf(Carro::class), version = 1)
abstract class CarrosDatabase: RoomDatabase(){
    abstract fun carroDAO(): CarroDAO
}