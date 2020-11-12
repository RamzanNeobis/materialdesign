package ru.startandroid.develop.financemanagment.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import ru.startandroid.develop.financemanagment.models.TransactionStoryEntity


@Dao
interface RoomDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertTransaction(body: TransactionStoryEntity)

    @Query("SELECT * FROM TransactionStoryEntity")
    fun findAllTransactions(): LiveData<List<TransactionStoryEntity>>

}