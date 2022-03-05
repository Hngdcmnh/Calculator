package com.example.calculator

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.TextView
import androidx.lifecycle.ViewModelProvider

class MainActivity : AppCompatActivity() {
    @SuppressLint("ResourceType")
    lateinit var sharedViewModel: SharedViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val showFragment = ShowFragment()
        val keyboardFragment = KeyboardFragment()
        sharedViewModel = ViewModelProvider(this).get(SharedViewModel::class.java)
        supportFragmentManager.beginTransaction().replace(R.id.show_container,showFragment).commit()
        supportFragmentManager.beginTransaction().replace(R.id.keyboard_container,keyboardFragment).commit()
    }

    fun setcalculatorClickListener(view: View) {
        if(view is TextView)
        {
            sharedViewModel.updateShowNumber(view.text.toString())
        }
    }
}