package com.example.learning_andriod

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.example.learning_andriod.R.id.welcome_employee
import com.example.learning_andriod.R.layout.fragment_welcome_employee
import com.example.learning_andriod.constants.AppConstants

class WelcomeEmployeeFragment: Fragment() {

    companion object {
        val TAG: String = WelcomeEmployeeFragment::class.java.simpleName

        fun getInstance(args: Bundle?): WelcomeEmployeeFragment {
            val fragment = WelcomeEmployeeFragment()
            fragment.arguments = args
            return fragment
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val employeeName = arguments?.getString(AppConstants.EMPLOYEE_NAME)
        val tvWelcomeEmployee = view.findViewById<TextView>(welcome_employee)
        tvWelcomeEmployee.text = "Welcome $employeeName"
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        return inflater.inflate(fragment_welcome_employee, container, false)
    }
}