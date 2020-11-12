package ru.startandroid.develop.financemanagment.adapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.item_home.view.*
import ru.startandroid.develop.financemanagment.R
import ru.startandroid.develop.financemanagment.models.Project

class HomeAdapter(val userList: ArrayList<Project>) : RecyclerView.Adapter<HomeAdapter.ViewHolder>() {




    //this method is returning the view for each item in the list
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeAdapter.ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.item_home, parent, false)
        return ViewHolder(v)
    }

    //this method is binding the data on the list
    override fun onBindViewHolder(holder: HomeAdapter.ViewHolder, position: Int) {
        holder.bindItems(userList[position])
    }

    //this method is giving the size of the list
    override fun getItemCount(): Int {
        return userList.size
    }

    //the class is hodling the list view
    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val urlProject = "https://yt3.ggpht.com/a/AATXAJyo_D848IE-MugVYXd796RFTPCQPj96aDk-JMoQpA=s900-c-k-c0x00ffffff-no-rj" +
                ""

        @SuppressLint("SetTextI18n")
        fun bindItems(project: Project) {
            itemView.nameProject.text = "Имя проекта:  \n${project.nameProject}"
            itemView.sumIncome.text = "Сумма дохода: \n${project.sumIncome.toString()}сом"
            itemView.sumConSumption.text = "Сумма расхода: \n${project.sumExpense.toString()}сом"
            itemView.totalAmount.text = "Текущий баланс: \n${project.profit.toString()}сом"
            val imageProject = itemView.findViewById<ImageView>(R.id.imageProject)
//            nameProject.text = project.nameProject
//            sumIncome.text = "Сумма дохода - " + project.sumIncome.toString()
//            sumConsumption.text = "Cумма расхода - " + project.sumСonsumption.toString()
            Glide
                .with(imageProject.context)
                .load(urlProject)
                .into(imageProject)


        }
    }
}