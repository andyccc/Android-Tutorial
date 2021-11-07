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

class SignUpActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)




        buttonRegister.isEnabled = false
        editTextRegisterUsername.addTextChangedListener(watcher)
        editTextRegisterPassword.addTextChangedListener(watcher)
        progressBarRegister.visibility = View.INVISIBLE
        buttonRegister.setOnClickListener {
            val name = editTextRegisterUsername.text?.trim().toString()
            val pwd = editTextRegisterPassword.text?.trim().toString()
            val user = LCUser().apply {
                progressBarRegister.visibility = View.VISIBLE

                username = name
                password = pwd
                signUpInBackground().subscribe(object : Observer<LCUser> {
                    override fun onSubscribe(d: Disposable) {

                    }

                    override fun onNext(t: LCUser) {
                        Toast.makeText(this@SignUpActivity, "注册成功", Toast.LENGTH_SHORT).show()
                        loginAction(name, pwd)
                    }

                    override fun onError(e: Throwable) {
                        progressBarRegister.visibility = View.INVISIBLE
                        Toast.makeText(this@SignUpActivity, "${e.message}", Toast.LENGTH_SHORT).show()
                    }

                    override fun onComplete() {

                    }
                })
            }


        }


    }

    private fun loginAction(name: String, pwd: String) {
        LCUser.logIn(name, pwd).subscribe(object : Observer<LCUser> {
            override fun onSubscribe(d: Disposable) {

            }

            override fun onNext(t: LCUser) {
                startActivity(Intent(this@SignUpActivity, MainActivity::class.java).also {
                    it.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK
                })
                finish()

            }

            override fun onError(e: Throwable) {
                progressBarRegister.visibility = View.INVISIBLE
                Toast.makeText(this@SignUpActivity, "${e.message}", Toast.LENGTH_SHORT)
            }

            override fun onComplete() {

            }

        })
    }



    private val watcher = object : TextWatcher {
        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
        }

        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            val t1 = editTextRegisterUsername.text.toString().isNotEmpty()
            val t2 = editTextRegisterPassword.text.toString().isNotEmpty()

            buttonRegister.isEnabled = t1 and t2
        }

        override fun afterTextChanged(s: Editable?) {
        }

    }

}