package com.example.learning_andriod

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.learning_andriod.R.id.employee_name
import com.example.learning_andriod.R.layout.employee_list_item
import com.example.learning_andriod.domain.Employee

class EmployeeListAdapter(private var employeeList: List<Employee>): RecyclerView.Adapter<EmployeeListAdapter.ViewHolder>() {

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        val employeeName: TextView = itemView.findViewById(employee_name)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(employee_list_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val currentEmployee = employeeList[position]
        holder.employeeName.text = currentEmployee.name
    }

    override fun getItemCount() = employeeList.size
}