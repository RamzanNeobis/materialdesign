package ru.startandroid.develop.financemanagment.adapters

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.item_transaction.view.*
import ru.startandroid.develop.financemanagment.R
import ru.startandroid.develop.financemanagment.models.TransactionStoryEntity

class TransactionAdapter(val transactionList: ArrayList<TransactionStoryEntity>) :
    RecyclerView.Adapter<TransactionAdapter.ViewHolder>() {

    //this method is returning the view for each item in the list
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): TransactionAdapter.ViewHolder {
        val v =
            LayoutInflater.from(parent.context).inflate(R.layout.item_transaction, parent, false)
        return ViewHolder(v)
    }

    //this method is binding the data on the list
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindItems(transactionList[position])
    }

    //this method is giving the size of the list
    override fun getItemCount(): Int {
        return transactionList.size
    }

    //the class is hodling the list view
    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bindItems(transaction: TransactionStoryEntity) {
            when(transaction.isIncome){
                true -> itemView.card_main.setCardBackgroundColor(Color.GREEN)
                false -> itemView.card_main.setCardBackgroundColor(Color.RED)
            }

            itemView.date.text = "Дата: ${transaction.date}"
            itemView.account.text = "Счет: ${transaction.bankType}"
            itemView.sum.text = "Сумма: ${transaction.sum}сом "
            itemView.category.text = "Операция: ${transaction.operation}"
            itemView.counterParty.text = "Конрагент: ${transaction.counterAgent}"
            itemView.project.text = "Проект: ${transaction.project}"




        }
    }
}