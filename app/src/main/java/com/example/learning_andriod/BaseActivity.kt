package com.example.learning_andriod

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.learning_andriod.R.id.main_container
import com.example.learning_andriod.R.layout.activity_base

class BaseActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(activity_base)

        if(supportFragmentManager.backStackEntryCount < 1) injectListEmployeeFragment(null)
    }

    private fun injectListEmployeeFragment(args: Bundle?) {
        val nonNullArgs = args ?: Bundle()
        val fragment = ListEmployeeFragment.getInstance(nonNullArgs)
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(main_container, fragment, ListEmployeeFragment.TAG).addToBackStack(ListEmployeeFragment.TAG).commit()
    }
}