package com.prabhanshu.animallassignment.presentation

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.prabhanshu.animallassignment.databinding.ActivityMainBinding
import com.prabhanshu.animallassignment.presentation.view.EmployeeAdapter
import com.prabhanshu.animallassignment.presentation.viewModel.EmployeeViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val viewModel: EmployeeViewModel by viewModels()
    private lateinit var adapter: EmployeeAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setupRecyclerView()
        observeEmployees()
        setupRefresh()
    }

    private fun setupRecyclerView() {
        adapter = EmployeeAdapter()
        binding.recyclerView.layoutManager = LinearLayoutManager(this)
        binding.recyclerView.adapter = adapter
    }

    private fun observeEmployees() {
        viewModel.employeeLiveData.observe(this) {
            if (it.isLoading == true) {
                binding.recyclerView.visibility = View.GONE
                binding.progressBar.visibility = View.VISIBLE
            } else if (it.data != null) {
                it.data.let { employees ->
                    binding.recyclerView.visibility = View.VISIBLE
                    adapter.submitList(employees)
                }
            } else if (it.message != null) {
                Toast.makeText(this, it.message, Int.MAX_VALUE).show()
            }
            binding.progressBar.visibility = View.INVISIBLE
        }

    }

    private fun setupRefresh() {
        binding.swipeRefreshLayout.setOnRefreshListener {
            viewModel.getEmployeeList()
            binding.swipeRefreshLayout.isRefreshing = false
        }
    }
}