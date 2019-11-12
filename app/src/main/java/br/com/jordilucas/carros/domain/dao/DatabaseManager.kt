package br.com.jordilucas.carros.domain.dao

import androidx.room.Room
import br.com.jordilucas.carros.CarrosAplication

/**
 * Created by jordi on 09/10/17.
 */
object DatabaseManager{
    private var dbInstance: CarrosDatabase
    init {
        val appContext = CarrosAplication.getInstance().applicationContext

        dbInstance = Room.databaseBuilder(
                appContext,
                CarrosDatabase::class.java,
                "carros.sqlite")
                .build()
    }

    fun getCarroDAO(): CarroDAO{
        return dbInstance.carroDAO()
    }

}