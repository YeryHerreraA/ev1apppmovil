package com.example.ev1yeryherrera.room.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
class Lista {
    @PrimaryKey(autoGenerate = true)
    var id:Long=0
    var name_list: String? = null
    var description_list: String? = null
    var user: String? =null

    constructor(name_list: String?, description_list: String?, user: String?) {
        this.name_list = name_list
        this.description_list = description_list
        this.user = user
    }
}
