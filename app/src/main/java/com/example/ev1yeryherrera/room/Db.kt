package com.example.ev1yeryherrera.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.ev1yeryherrera.room.dao.DaoLista
import com.example.ev1yeryherrera.room.dao.DaoUsuario
import com.example.ev1yeryherrera.room.entity.Lista
import com.example.ev1yeryherrera.room.entity.Usuario

@Database(entities = [Usuario::class,Lista::class],
version= 1
)

abstract class Db:RoomDatabase() {
    abstract fun daoUsuario():DaoUsuario
    abstract fun  daoLista():DaoLista

}