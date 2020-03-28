package com.patipan.dev.coinrankingapp.base

import android.content.Context
import android.view.inputmethod.InputMethodManager
import androidx.appcompat.widget.AppCompatEditText
import androidx.fragment.app.Fragment

abstract class BaseFragment : Fragment() {

    fun AppCompatEditText.hideKeyBoard() {
        this.clearFocus()
        val inputMethodManger =
            context?.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        inputMethodManger.hideSoftInputFromWindow(this.windowToken, 0)
    }
}