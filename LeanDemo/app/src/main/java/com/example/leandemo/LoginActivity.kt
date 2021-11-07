package com.example.leandemo

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.Toast
import cn.leancloud.LCUser
import io.reactivex.Observer
import io.reactivex.disposables.Disposable
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.android.synthetic.main.activity_login.buttonRegister
import kotlinx.android.synthetic.main.activity_sign_up.*

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
            progressBarLogin.visibility = View.VISIBLE

            val name = editTextLoginUsername.text?.toString()?.trim()
            val pwd = editTextLoginPassword.text?.toString()?.trim()
            name?.let {
                pwd?.let {
                    loginAction(name, pwd)
                }
            }
        }


    }

    private val watcher = object : TextWatcher {
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


    private fun loginAction(name: String, pwd: String) {
        LCUser.logIn(name, pwd).subscribe(object : Observer<LCUser> {
            override fun onSubscribe(d: Disposable) {

            }

            override fun onNext(t: LCUser) {
                startActivity(Intent(this@LoginActivity, MainActivity::class.java).also {
                    it.flags =
                        Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK // 不让退回
                })
                finish()
            }

            override fun onError(e: Throwable) {
                progressBarLogin.visibility = View.INVISIBLE
                Toast.makeText(this@LoginActivity, "${e.message}", Toast.LENGTH_SHORT)
            }

            override fun onComplete() {

            }

        })
    }

}