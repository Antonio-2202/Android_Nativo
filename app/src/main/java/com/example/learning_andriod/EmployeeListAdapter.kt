package com.example.learning_andriod

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.example.learning_andriod.R.id.employee_name
import com.example.learning_andriod.R.layout.employee_list_item
import com.example.learning_andriod.domain.Employee

class EmployeeListAdapter(
    val context: Context,
    private var employeeList: List<Employee>,
    private val onOpenWelcomeEmployeeCallback: OnOpenWelcomeEmployeeFragment
) : RecyclerView.Adapter<EmployeeListAdapter.ViewHolder>() {

    interface OnOpenWelcomeEmployeeFragment {
        fun OnOpenWelcomeEmployeeFragment(employeeName: String)
    }


    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        val employeeItem: CardView = itemView.findViewById(R.id.employee_list_item)
        val employeeName: TextView = itemView.findViewById(employee_name)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(employee_list_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val currentEmployee = employeeList[position]
        holder.employeeName.text = currentEmployee.name

        holder.employeeItem.setOnClickListener {
            onOpenWelcomeEmployeeCallback.OnOpenWelcomeEmployeeFragment(currentEmployee.name)
        }
    }

    override fun getItemCount() = employeeList.size
}