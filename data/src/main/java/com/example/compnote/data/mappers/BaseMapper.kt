package com.example.compnote.data.mappers

interface BaseMapper<E, D> {

    fun mapFromEntity(type: E): D

    fun mapToEntity(type: D): E
}