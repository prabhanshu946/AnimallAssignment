package com.prabhanshu.animallassignment.presentation.view

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.prabhanshu.animallassignment.R
import com.prabhanshu.animallassignment.databinding.ItemEmployeeBinding
import com.prabhanshu.animallassignment.domain.model.EmployeeResponse
import com.prabhanshu.animallassignment.domain.model.Employees


class EmployeeAdapter : RecyclerView.Adapter<EmployeeAdapter.EmployeeViewHolder>() {
    private val employees = mutableListOf<Employees>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EmployeeViewHolder {
        val binding =
            ItemEmployeeBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return EmployeeViewHolder(binding)
    }

    override fun onBindViewHolder(holder: EmployeeViewHolder, position: Int) {
        holder.bind(employees[position])
    }

    override fun getItemCount() = employees.size

    fun submitList(newList: EmployeeResponse) {
        employees.clear()
        newList.let {
            employees.addAll(it.employees)
        }
        notifyDataSetChanged()
    }

    inner class EmployeeViewHolder(private val binding: ItemEmployeeBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(employee: Employees) {
            binding.name.text = employee.full_name
            binding.team.text = employee.team
            binding.photo.load(employee.photo_url_small) {
                crossfade(true)
                placeholder(R.drawable.ic_account_profile_default)
                error(R.drawable.ic_account_profile_default)
            }
        }
    }
}