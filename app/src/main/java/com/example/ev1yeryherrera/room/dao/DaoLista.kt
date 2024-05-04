package com.example.ev1yeryherrera.room.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.ev1yeryherrera.room.entity.Lista
import java.net.IDN

@Dao
interface DaoLista {
    @Query("SElECT * FROM Lista")
    fun obtenerlistas(): List<Lista>

    @Query("SELECT * FROM Lista WHERE user = :user")
    fun obtenerListaUsuario(user:String):List<Lista>

    @Query("SELECT * FROM lista WHERE name_list=:name AND user=:user")
    fun  obtenerListadoPorNombre(name:String, user: String):List<Lista>

    @Insert
    fun agregarLista(lista:Lista):Long

    @Query("UPDATE Lista SET name_list=:name, description_list=:description WHERE id=:id ")
    fun actualizarLista(name: String,description:String,id:Long):Int

    @Query("DELETE FROM Lista where id=:id")
    fun eliminarLista(id: Long)

}