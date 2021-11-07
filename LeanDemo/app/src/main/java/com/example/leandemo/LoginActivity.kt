package com.example.leandemo

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        buttonLogin.isEnabled = false
        editTextLoginUsername.addTextChangedListener(watcher)
        editTextLoginPassword.addTextChangedListener(watcher)
        progressBarLogin.visibility = View.INVISIBLE
        buttonRegister.setOnClickListener {
            startActivity(Intent(this, SignUpActivity::class.java))
        }
        buttonLogin.setOnClickListener {

        }


    }

    private val watcher = object : TextWatcher{
        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
        }

        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            val t1 = editTextLoginUsername.text.toString().isNotEmpty()
            val t2 = editTextLoginPassword.text.toString().isNotEmpty()



            buttonLogin.isEnabled = t1 and t2
        }

        override fun afterTextChanged(s: Editable?) {
        }

    }

}