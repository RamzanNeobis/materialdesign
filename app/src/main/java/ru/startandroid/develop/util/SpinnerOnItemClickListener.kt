package ru.startandroid.develop.util

import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter

class SpinnerOnItemClickListener(
    private val listener :((AdapterView<*>?,Int) -> Unit)? = null
) : AdapterView.OnItemSelectedListener {

    override fun onNothingSelected(p0: AdapterView<*>?) {

    }

    override fun onItemSelected(parent: AdapterView<*>?, p1: View?, position: Int, id: Long) {
        listener?.invoke(parent,position)
    }
}