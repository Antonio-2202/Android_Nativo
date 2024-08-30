package com.example.learning_andriod

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.learning_andriod.R.id.employee_list
import com.example.learning_andriod.R.layout.fragment_list_employee
import com.example.learning_andriod.domain.Employee

class ListEmployeeFragment : Fragment() {

    companion object {
        val TAG: String = ListEmployeeFragment::class.java.simpleName

        fun getInstance(args: Bundle?): ListEmployeeFragment {
            val fragment = ListEmployeeFragment()
            fragment.arguments = args
            return fragment
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val employeeRecyclerView = view.findViewById<RecyclerView>(employee_list)
        employeeRecyclerView.layoutManager = LinearLayoutManager(context)

        val itemList: List<Employee> = listOf(
            Employee(
                name = "Antonio",
            ),
            Employee(
                name = "Marcos",
            ),
            Employee(
                name = "Fernando",
            ),
            Employee(
                name = "Toño",
            ),
            Employee(
                name = "Rafa",
            )
        )
        val adapter = EmployeeListAdapter(itemList)

        employeeRecyclerView.adapter = adapter
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        return inflater.inflate(fragment_list_employee, container, false)
    }
}