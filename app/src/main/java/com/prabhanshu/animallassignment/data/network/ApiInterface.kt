package com.prabhanshu.animallassignment.data.network

import com.prabhanshu.animallassignment.domain.model.EmployeeResponse
import retrofit2.Response
import retrofit2.http.GET

interface ApiInterface {

    @GET("/animall.appspot.com/android-interview/employees.json")
    suspend fun getEmployees(): Response<EmployeeResponse>

    @GET("/animall.appspot.com/android-interview/employees_malformed.json")
    suspend fun getEmployeesMalformed(): Response<EmployeeResponse?>

    @GET("/animall.appspot.com/android-interview/employees_empty.json")
    suspend fun getEmployeesEmptyList(): Response<EmployeeResponse?>

}