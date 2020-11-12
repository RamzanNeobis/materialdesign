package ru.startandroid.develop.financemanagment.retrofit

import retrofit2.Call
import retrofit2.http.GET
import ru.startandroid.develop.financemanagment.models.Account
import ru.startandroid.develop.financemanagment.models.OperationResponse
import ru.startandroid.develop.financemanagment.models.Project

interface ApiService {

    @GET("operation/index")
    fun getOperations(): Call<List<OperationResponse>>

   @GET("finance/index")
   fun  getProject(): Call<List<Project>>

    @GET("score/scoresdetails")
    fun getScores(): Call<List<Account>>

}