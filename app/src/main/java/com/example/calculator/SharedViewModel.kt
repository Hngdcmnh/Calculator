package com.example.calculator

import android.util.Log
import android.widget.Toast
import androidx.core.text.isDigitsOnly
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import java.util.*

class SharedViewModel : ViewModel() {

    private val _showNumber = MutableLiveData<String>()
    val showNumber: LiveData<String> = _showNumber


    init {
        _showNumber.value = ""
    }

    fun updateShowNumber(input: String) {
        var show = _showNumber.value!!
        if (input == "+" || input == "-") {
            if (!show[show.length - 1].isDigit()) {
                show = show.substring(0, show.length - 1)
                _showNumber.postValue(show.plus(input))
            } else {
                _showNumber.postValue(show.plus(input))
            }
        } else if (input == "=") {
            _showNumber.value = calculateTotal(show)
        } else if (input == "C") {
            _showNumber.postValue("")
        } else if (input == "Up") {
            _showNumber.value = calculateTotal(show + "+1")
        } else if (input == "Down") {
            _showNumber.value = calculateTotal(show + "-1")
        } else if (input == "+-") {
            if (show[0] == '-') {
                _showNumber.value = show.substring(1, show.length)
            } else {
                _showNumber.value = calculateTotal("-" + calculateTotal(show))
            }
        } else if (input == "Del") {
            if (show.length >= 1) {
                _showNumber.postValue(show.substring(0, show.length - 1))
            }
        } else {
            _showNumber.postValue(show.plus(input))
        }
    }

    fun calculateTotal(show: String): String {
        var res = 0L
        var opa = ""
        var num = 0L
        for (i in show) {
            if (i.isDigit()) {
                num = num * 10L + i.digitToInt()
            } else {
                if (opa == "") {
                    res += num
                    if (i.equals('-')) {
                        opa = "-"
                    } else if (i.equals('+')) {
                        opa = "+"
                    }
                } else {
                    Log.e("i", i.toString())
                    if (opa == "-") {
                        res = res - num
                    } else if (opa == "+") {
                        res = res + num
                    }

                    if (i.equals('-')) {
                        opa = "-"
                    } else if (i.equals('+')) {
                        opa = "+"
                    }
                }
                num = 0L
            }

        }

        if (num != 0L) {
            if (opa == "-") {
                res = res - num
            } else if (opa == "+") {
                res = res + num
            } else {
                res = num
            }
        }



        return res.toString()
    }
}