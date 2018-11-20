package com.example.giovanni.giovanni.mvploginkotlin.main

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.example.giovanni.giovanni.R
import kotlinx.android.synthetic.main.activity_mainlogin_kotlin.*

class MainLoginKotlinActivity : AppCompatActivity(), MainView {

    private val presenter = MainKotlinPresenter(this, FindItemsInteractorKotlin())

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_mainlogin_kotlin)
    }

    override fun onResume() {
        super.onResume()
        presenter.onResume()
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.onDestroy()
    }

    override fun showProgress() {
        progress.visibility = View.VISIBLE
        mainlogin_list.visibility = View.GONE
    }

    override fun hideProgress() {
        progress.visibility = View.GONE
        mainlogin_list.visibility = View.VISIBLE
    }

    override fun setItems(items: List<String>) {
        mainlogin_list.adapter = MainKotlinAdapter(items, presenter::onItemClicked)
    }

    override fun showMessage(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show()
    }
}