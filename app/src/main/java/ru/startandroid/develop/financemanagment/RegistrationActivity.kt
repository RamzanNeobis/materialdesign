package ru.startandroid.develop.financemanagment

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.registration.*

class RegistrationActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.registration)

        signUpBut.setOnClickListener{
            val intent = Intent(this,AuthorizationActivity::class.java)
            startActivity(intent)
        }
    }
}