package com.prabhanshu.animallassignment.presentation.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.prabhanshu.animallassignment.data.network.Resource
import com.prabhanshu.animallassignment.domain.model.EmployeeResponse
import com.prabhanshu.animallassignment.domain.use_case.GetEmployeesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class EmployeeViewModel @Inject constructor(private val getEmployeeUseCase: GetEmployeesUseCase) :
    ViewModel() {

    private val _employeeLiveData = MutableLiveData<Resource<EmployeeResponse>>()
    val employeeLiveData get() = _employeeLiveData

    init {
        getEmployeeList()
    }

    fun getEmployeeList() {
        getEmployeeUseCase().onEach {
            when (it) {
                is Resource.Loading -> {
                    employeeLiveData.value = Resource.Loading(true)
                }

                is Resource.Success -> {
                    employeeLiveData.value = Resource.Success(it.data)
                }

                is Resource.Error -> {
                    employeeLiveData.value = Resource.Error(it.message.toString())
                }
            }
        }.launchIn(viewModelScope)
    }
}