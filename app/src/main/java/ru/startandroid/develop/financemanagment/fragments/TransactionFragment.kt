package ru.startandroid.develop.financemanagment.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import kotlinx.android.synthetic.main.fragment_transaction.*
import ru.startandroid.develop.financemanagment.R
import ru.startandroid.develop.financemanagment.adapters.TransactionAdapter
import ru.startandroid.develop.financemanagment.db.AppDatabase
import ru.startandroid.develop.financemanagment.models.TransactionStoryEntity

/**
 * A simple [Fragment] subclass.
 * Use the [TransactionFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class TransactionFragment : Fragment() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_transaction, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        AppDatabase.instance(requireContext()).dbDao().findAllTransactions().observe(viewLifecycleOwner, Observer {
            Log.d("table", it.toString())
            recyclerviewTransaction.adapter = TransactionAdapter(it as ArrayList<TransactionStoryEntity>)
        })

    }

}