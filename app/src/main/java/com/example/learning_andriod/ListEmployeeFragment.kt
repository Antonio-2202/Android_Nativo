package com.example.learning_andriod

import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.graphics.drawable.shapes.RoundRectShape
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.ui.geometry.RoundRect
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.learning_andriod.R.id.employee_list
import com.example.learning_andriod.R.layout.fragment_list_employee
import com.example.learning_andriod.domain.Employee
import com.example.learning_andriod.interfaces.OnReloadData

class ListEmployeeFragment : Fragment(), OnReloadData {

    private var listAdapter: EmployeeListAdapter? = null
    private var onOpenEmployeeDetailCallback: EmployeeListAdapter.OnOpenWelcomeEmployeeFragment? = null

    companion object {
        val TAG: String = ListEmployeeFragment::class.java.simpleName

        fun getInstance(args: Bundle?): ListEmployeeFragment {
            val fragment = ListEmployeeFragment()
            fragment.arguments = args
            return fragment
        }
    }

    override fun onReloadData(rv: RecyclerView?) {
        for (i in 0 until listAdapter!!.itemCount) {
            val viewHolder = rv!!.findViewHolderForAdapterPosition(i) as? EmployeeListAdapter.ViewHolder

            viewHolder?.let {
                if (it.employeeItem.cardBackgroundColor.defaultColor == Color.WHITE) {
                    it.employeeItem.setCardBackgroundColor(Color.BLACK)
                    it.employeeName.setTextColor(Color.WHITE)
                } else {
                    it.employeeItem.setCardBackgroundColor(Color.WHITE)
                    it.employeeName.setTextColor(Color.BLACK)
                }
            }
        }

        listAdapter!!.notifyDataSetChanged()
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is EmployeeListAdapter.OnOpenWelcomeEmployeeFragment) {
            onOpenEmployeeDetailCallback = context
        } else {
            throw RuntimeException("$context debe implementar OnOpenEmployeeDetail")
        }
    }

    override fun onDetach() {
        super.onDetach()
        onOpenEmployeeDetailCallback = null
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
        listAdapter = EmployeeListAdapter(requireContext(), itemList, onOpenEmployeeDetailCallback!!)

        employeeRecyclerView.adapter = listAdapter
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        return inflater.inflate(fragment_list_employee, container, false)
    }
}