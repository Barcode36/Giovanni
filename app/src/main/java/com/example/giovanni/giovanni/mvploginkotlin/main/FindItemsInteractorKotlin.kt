package com.example.giovanni.giovanni.mvploginkotlin.main

import android.os.Handler

class FindItemsInteractorKotlin {

    fun findItems(callback: (List<String>) -> Unit) {
        postDelayed(1000) { callback(createArrayList()) }
    }

    private fun createArrayList(): List<String> = (1..10).map { "Item $it" }

    fun postDelayed(delayMillis: Long, task: () -> Unit) {
        Handler().postDelayed(task, delayMillis)
    }
}