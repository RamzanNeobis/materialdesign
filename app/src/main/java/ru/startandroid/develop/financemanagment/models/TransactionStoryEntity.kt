package ru.startandroid.develop.financemanagment.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
class TransactionStoryEntity(
    @PrimaryKey
    val id: Int,
    val date: String,
    val bankType: String,
    val sum: String,
    val operation: String,
    val counterAgent: String,
    val project: String,
    val isIncome: Boolean
)