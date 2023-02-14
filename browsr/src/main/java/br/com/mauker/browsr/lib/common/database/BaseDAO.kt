package br.com.mauker.browsr.lib.common.database

import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Update

interface BaseDAO<T> {
    @Insert
    suspend fun insert(obj: T): Long

    @Insert
    suspend fun insert(vararg obj: T): List<Long>

    @Update
    suspend fun update(obj: T): Int

    @Delete
    suspend fun delete(obj: T): Int
}