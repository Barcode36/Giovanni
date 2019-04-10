package com.example.giovanni.giovanni.utils

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.util.Log
import android.view.inputmethod.InputMethodManager

class UtilsKotlin {

    companion object {

        val currentActivity : Activity? = null

        fun callContact(context: Context, phone: String) {
            try {
                val uri = "tel:$phone"
                val intent = Intent(Intent.ACTION_DIAL, Uri.parse(uri))
                context.startActivity(intent)
            } catch (e: Exception) {
                Log.e("Exception", e.message)
            }
        }

        fun sendEmail(context: Context, email : String) {

            val intent = Intent(Intent.ACTION_SENDTO, Uri.parse("mailto:$email"))
            intent.putExtra(Intent.EXTRA_SUBJECT, "")
            intent.putExtra(Intent.EXTRA_TEXT, "")
            // intent.putExtra(Intent.EXTRA_HTML_TEXT, "") // If you are using HTML in your body text
            context.startActivity(Intent.createChooser(intent, ""))
        }

        fun hideSoftKeyboardKotlin() {
            val imm = currentActivity?.getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
            imm.hideSoftInputFromWindow(currentActivity.currentFocus?.windowToken, 0)
        }

//        fun hideSoftKeyboardKotlin2() {
//            val imm : InputMethodManager = currentActivity?.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
//            imm.hideSoftInputFromWindow(view?.windowToken, 0)
//        }

//    fun showSoftKeyboardKotlin() {
//        val imm = currentActivity?.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
//        imm.showSoftInput(search_input_text, InputMethodManager.SHOW_IMPLICIT)
//    }
    }
}