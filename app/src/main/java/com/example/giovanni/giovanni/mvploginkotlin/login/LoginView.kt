package com.example.giovanni.giovanni.mvploginkotlin.login

interface LoginView {

    fun showProgress()
    fun hideProgress()
    fun setUsernameError()
    fun setPasswordError()
    fun navigateToHome()
}