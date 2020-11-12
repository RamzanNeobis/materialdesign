package ru.startandroid.develop.financemanagment.fragments

import android.app.DatePickerDialog
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import kotlinx.android.synthetic.main.fragment_income.*
import ru.startandroid.develop.financemanagment.R
import java.text.SimpleDateFormat
import java.util.*

/**
 * A simple [Fragment] subclass.
 * Use the [TransferFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class TransferFragment : Fragment() {

    private val calendar: Calendar by lazy { Calendar.getInstance() }
    val fromAccount = arrayOf("Демир_Банк", "Элсом")
    val toAccount = arrayOf("Демир_Банк", "Элсом")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = inflater.inflate(R.layout.fragment_transfer, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

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

        val adapterFromAccount: ArrayAdapter<String>? = context?.let {
            ArrayAdapter<String>(
                it,
                R.layout.dropdown_menu_popup_item,
                fromAccount
            )
        }

        val editTextCash: AutoCompleteTextView =
            view.findViewById(R.id.fromAccounts)
        editTextCash.setAdapter(adapterFromAccount)

        val adapterToAccount: ArrayAdapter<String>? = context?.let {
            ArrayAdapter<String>(
                it,
                R.layout.dropdown_menu_popup_item,
                toAccount
            )
        }

        val editTextCategories: AutoCompleteTextView =
            view.findViewById(R.id.toAccounts)
        editTextCategories.setAdapter(adapterToAccount)


    }


}