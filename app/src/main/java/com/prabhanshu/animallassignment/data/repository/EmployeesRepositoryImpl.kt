package com.prabhanshu.animallassignment.data.repository

import com.prabhanshu.animallassignment.data.network.ApiInterface
import com.prabhanshu.animallassignment.domain.model.EmployeeResponse
import com.prabhanshu.animallassignment.domain.repository.EmployeesRepository
import javax.inject.Inject

class EmployeesRepositoryImpl @Inject constructor(private val apiInterface: ApiInterface) :
    EmployeesRepository {
    override suspend fun getEmployees(): EmployeeResponse? {
        val response = apiInterface.getEmployees().body()
        return response
    }
}