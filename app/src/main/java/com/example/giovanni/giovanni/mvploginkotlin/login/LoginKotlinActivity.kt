package com.example.giovanni.giovanni.mvploginkotlin.login

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.view.inputmethod.InputMethodManager
import androidx.appcompat.app.AppCompatActivity
import com.example.giovanni.giovanni.R
import com.example.giovanni.giovanni.mvploginkotlin.main.MainLoginKotlinActivity
import kotlinx.android.synthetic.main.activity_login_kotlin.*

class LoginKotlinActivity : AppCompatActivity(), LoginView {

    private val presenter = LoginKotlinPresenter(this, LoginKotlinInteractor())

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login_kotlin)

        button.setOnClickListener { validateCredentials() }

        username.requestFocus()
        val imm = applicationContext.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.showSoftInput(username, InputMethodManager.SHOW_IMPLICIT)
    }

    private fun validateCredentials() {
        presenter.validateCredentials(username.text.toString(), password.text.toString())
    }

    override fun onDestroy() {
        presenter.onDestroy()
        super.onDestroy()
    }

    override fun showProgress() {
        progress.visibility = View.VISIBLE
    }

    override fun hideProgress() {
        progress.visibility = View.GONE
    }

    override fun setUsernameError() {
        username.error = getString(R.string.username_error)
    }

    override fun setPasswordError() {
        password.error = getString(R.string.password_error)
    }

    override fun navigateToHome() {
        startActivity(Intent(this, MainLoginKotlinActivity::class.java))
    }
}