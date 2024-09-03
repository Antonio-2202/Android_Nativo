package com.example.learning_andriod

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.example.learning_andriod.R.id.btn_reload
import com.example.learning_andriod.R.id.employee_list
import com.example.learning_andriod.R.id.list_employee_fragment
import com.example.learning_andriod.R.id.main_container
import com.example.learning_andriod.R.layout.activity_base
import com.example.learning_andriod.constants.AppConstants
import com.example.learning_andriod.interfaces.OnReloadData

class BaseActivity : AppCompatActivity(), EmployeeListAdapter.OnOpenWelcomeEmployeeFragment {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(activity_base)

        val btnReload = findViewById<Button>(btn_reload)

        btnReload.setOnClickListener {
            val listView = findViewById<ConstraintLayout>(list_employee_fragment)
            var recyclerView: RecyclerView? = null

            if (findCurrentFragment()::class.java.simpleName == ListEmployeeFragment.TAG) recyclerView = listView.findViewById(employee_list)

            findCurrentFragment().onReloadData(recyclerView)
        }

        if(supportFragmentManager.backStackEntryCount < 1) injectListEmployeeFragment(null)
    }

    private fun findCurrentFragment(): OnReloadData {
        val currentFragmentTag = supportFragmentManager.getBackStackEntryAt(supportFragmentManager.backStackEntryCount - 1).name
        val currentFragment = supportFragmentManager.findFragmentByTag(currentFragmentTag)
        return currentFragment as OnReloadData
    }

    private fun injectListEmployeeFragment(args: Bundle?) {
        val nonNullArgs = args?: Bundle()
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