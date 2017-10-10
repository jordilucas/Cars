package br.com.jordilucas.carros.domain

import br.com.jordilucas.carros.domain.dao.DatabaseManager

/**
 * Created by jordi on 09/10/17.
 */
object FavoritosService{
    fun getCarros(): List<Carro>{
        val dao = DatabaseManager.getCarroDAO()
        val carros = dao.findAll()
        return carros
    }

    fun isFavorito(carro: Carro): Boolean{
        val dao = DatabaseManager.getCarroDAO()
        val exists = dao.getById(carro.id) != null
        return exists
    }

    fun favoritar(carro: Carro): Boolean{
        val dao = DatabaseManager.getCarroDAO()
        val favorito = isFavorito(carro)
        if(favorito){
            dao.delete(carro)
            return false
        }
        dao.insert(carro)
        return true
    }

}