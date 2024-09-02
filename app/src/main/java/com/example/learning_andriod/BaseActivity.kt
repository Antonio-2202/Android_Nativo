package com.example.learning_andriod

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.example.learning_andriod.R.id.main_container
import com.example.learning_andriod.R.layout.activity_base
import com.example.learning_andriod.constants.AppConstants

class BaseActivity : AppCompatActivity(), EmployeeListAdapter.OnOpenWelcomeEmployeeFragment {
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

    private fun injectWelcomeEmployeeFragment(args: Bundle?) {
        val nonNullArgs = args ?: Bundle()
        val fragment = WelcomeEmployeeFragment.getInstance(nonNullArgs)
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(main_container, fragment, WelcomeEmployeeFragment.TAG).addToBackStack(WelcomeEmployeeFragment.TAG).commit()
    }

    override fun onBackPressed() {
        if (supportFragmentManager.backStackEntryCount > 1) {
            supportFragmentManager.popBackStack()
        } else {
            finish()
        }
    }

    override fun OnOpenWelcomeEmployeeFragment(employeeName: String) {
        val args = Bundle().apply {
            putString(AppConstants.EMPLOYEE_NAME, employeeName)
        }
        injectWelcomeEmployeeFragment(args)
        Log.e("EMPLOYEE NAME", employeeName)
    }
}