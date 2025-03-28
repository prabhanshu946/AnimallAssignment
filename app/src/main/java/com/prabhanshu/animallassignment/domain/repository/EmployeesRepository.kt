package com.prabhanshu.animallassignment.domain.repository

import com.prabhanshu.animallassignment.domain.model.EmployeeResponse

interface EmployeesRepository {
    suspend fun getEmployees(): EmployeeResponse?
}