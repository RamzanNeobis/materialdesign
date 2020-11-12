package ru.startandroid.develop.financemanagment.add

import android.annotation.SuppressLint
import android.app.DatePickerDialog
import android.content.Intent
import android.os.AsyncTask
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_income.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import ru.startandroid.develop.financemanagment.MainActivity
import ru.startandroid.develop.financemanagment.R
import ru.startandroid.develop.financemanagment.db.AppDatabase
import ru.startandroid.develop.financemanagment.fragments.TransactionFragment
import ru.startandroid.develop.financemanagment.models.Account
import ru.startandroid.develop.financemanagment.models.OperationResponse
import ru.startandroid.develop.financemanagment.models.TransactionStoryEntity
import ru.startandroid.develop.financemanagment.retrofit.RetrofitBuilder
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList
import kotlin.random.Random


/**
 * A simple [Fragment] subclass.
 * Use the [IncomeFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class IncomeFragment : Fragment() {
    val nameProject = arrayOf("NeoLabs", "NeobisClub", "NeobisStudio")
    val categories = arrayOf(" Курсы ", " Прочее ", " Коммуналка ")
    val nameCounterParty = arrayOf(" Рамзан ", " Аскар ", " Кушбак ", "Айдар")
    val nameAccount = arrayOf("KICB", "ЭЛСОМ", "Демир Банк")
    private val calendar: Calendar by lazy { Calendar.getInstance() }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.fragment_income, container, false)

    @SuppressLint("SimpleDateFormat")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        initOperations()
        initAccount()
        insertNewTransaction()

        val textDate = SimpleDateFormat("dd.MM.yyyy").format(System.currentTimeMillis())
        date.setText(textDate)
        val dateSetListener =
            DatePickerDialog.OnDateSetListener { view, year, monthOfYear, dayOfMonth ->
                calendar.set(Calendar.YEAR, year)
                calendar.set(Calendar.MONTH, monthOfYear)
                calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth)

                val myFormat = "dd.MM.yyyy" // mention the format you need
                val sdf = SimpleDateFormat(myFormat, Locale.US)
                val formatDate = sdf.format(calendar.time)
                date.setText(formatDate)
            }

        date.setOnClickListener {
            DatePickerDialog(
                requireContext(), dateSetListener,
                calendar.get(Calendar.YEAR),
                calendar.get(Calendar.MONTH),
                calendar.get(Calendar.DAY_OF_MONTH)
            ).show()
        }

        val adapterCounterParty: ArrayAdapter<String>? = context?.let {
            ArrayAdapter<String>(
                it,
                R.layout.dropdown_menu_popup_item,
                nameCounterParty
            )
        }

        val editTextCounterParty: AutoCompleteTextView =
            view.findViewById(R.id.selectCounterParty)
        editTextCounterParty.setAdapter(adapterCounterParty)


        val adapterProject: ArrayAdapter<String>? = context?.let {
            ArrayAdapter<String>(
                it,
                R.layout.dropdown_menu_popup_item,
                nameProject
            )
        }

        val editTextProject: AutoCompleteTextView =
            view.findViewById(R.id.selectProject)
        editTextProject.setAdapter(adapterProject)


//        val adapterNameAccount: ArrayAdapter<String>? = context?.let {
//            ArrayAdapter<String>(
//                it,
//                R.layout.dropdown_menu_popup_item,
//                nameAccount
//            )
//        }

//        val nameAccount: AutoCompleteTextView =
//            view.findViewById(R.id.nameAccounts)
//        nameAccount.setAdapter(adapterNameAccount)
    }

    private fun insertNewTransaction() {
        save_btn.setOnClickListener {
            val date = date.text.toString()
            val account = nameAccounts.text.toString()
            val sum = sum.text.toString()
            val operation = selectCategories.text.toString()
            val counterAgent = selectCounterParty.text.toString()
            val project = selectProject.text.toString()

            val body = TransactionStoryEntity(
                id = Random.nextInt(),
                bankType = account,
                counterAgent = counterAgent,
                date = date,
                operation = operation,
                project = project,
                sum = sum,
                isIncome = true
            )

            AsyncTask.execute {
                AppDatabase.instance(requireContext()).dbDao().insertTransaction(body)
            }

            Toast.makeText(requireContext(), "Транзакция прошла успешно", Toast.LENGTH_SHORT).show()
            requireActivity().finish()
        }
    }


    private fun initOperations() {
        RetrofitBuilder.apiService().getOperations()
            .enqueue(object : Callback<List<OperationResponse>> {
                override fun onFailure(call: Call<List<OperationResponse>>, t: Throwable) {
                    println("Ошибка братан")
                    Log.d("suka", "fail $t")
                }

                override fun onResponse(
                    call: Call<List<OperationResponse>>,
                    response: Response<List<OperationResponse>>
                ) {
                    println("Успешно братан")
                    Log.d("s", "suc ${response.code()}")


                    val operations = ArrayList<String>()

                    response.body()?.forEach {
                        operations.add(it.name)
                    }

                    val adapterCategories: ArrayAdapter<String>? = context?.let {
                        ArrayAdapter<String>(it, R.layout.dropdown_menu_popup_item, operations)
                    }
                    selectCategories.setAdapter(adapterCategories)
                }
            })
    }


    private fun initAccount() {
        RetrofitBuilder.apiService().getScores()
            .enqueue(object : Callback<List<Account>>{
                override fun onFailure(call: Call<List<Account>>, t: Throwable) {
                    println("Ошибка братан")
                    Log.d("suka", "fail $t")
                }

                override fun onResponse(
                    call: Call<List<Account>>,
                    response: Response<List<Account>>
                ) {
                    println("Успешно братан")
                    Log.d("s", "suc ${response.code()}")


                    val operations = ArrayList<String>()

                    response.body()?.forEach {
                        operations.add(it.name)
                        operations.add(it.paymentType)
                    }

                    val adapterAccount: ArrayAdapter<String>? = context?.let {
                        ArrayAdapter<String>(it, R.layout.dropdown_menu_popup_item, operations)
                    }
                    nameAccounts.setAdapter(adapterAccount)
                }

            })

    }

}