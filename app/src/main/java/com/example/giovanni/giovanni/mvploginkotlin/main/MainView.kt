package com.example.giovanni.giovanni.mvploginkotlin.main

interface MainView {

    fun showProgress()
    fun hideProgress()
    fun setItems(items: List<String>)
    fun showMessage(message: String)
}