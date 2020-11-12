package ru.startandroid.develop.financemanagment.fragments

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.fragment_home.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import ru.startandroid.develop.financemanagment.R
import ru.startandroid.develop.financemanagment.adapters.HomeAdapter
import ru.startandroid.develop.financemanagment.add.AddActivity
import ru.startandroid.develop.financemanagment.models.Project
import ru.startandroid.develop.financemanagment.retrofit.RetrofitBuilder

/**
 * A simple [Fragment] subclass.
 * Use the [HomeFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class HomeFragment : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.fragment_home, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        fab.setOnClickListener {
            val intent = Intent(requireContext(), AddActivity::class.java)
            startActivity(intent)
        }

        initProjects()

    }

    private fun initProjects() {
        RetrofitBuilder.apiService().getProject().enqueue(object : Callback<List<Project>> {
            override fun onFailure(call: Call<List<Project>>, t: Throwable) {
                print("FAIL")
            }

            override fun onResponse(call: Call<List<Project>>, response: Response<List<Project>>) {
                println("Успешно братан")
                Log.d("s", "suc ${response.code()}")


                recyclerView.adapter = HomeAdapter(response.body() as ArrayList<Project>)
            }

        })
    }


}
//private fun initProjects() {
//    RetrofitBuilder.apiService().getProject().enqueue(object : Callback<List<Project>> {
//        override fun onFailure(call: Call<List<Project>>, t: Throwable) {
//            print("FAIL")
//        }
//
//        override fun onResponse(call: Call<List<Project>>, response: Response<List<Project>>) {
//            println("Успешно братан")
//            Log.d("s", "suc ${response.code()}")
//
//            recyclerView.adapter = HomeAdapter(response.body() as ArrayList<Project>)
//        }
//    })
//}



