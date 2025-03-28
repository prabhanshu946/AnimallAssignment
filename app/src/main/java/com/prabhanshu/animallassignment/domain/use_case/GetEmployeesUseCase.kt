package com.prabhanshu.animallassignment.domain.use_case

import com.prabhanshu.animallassignment.data.network.Resource
import com.prabhanshu.animallassignment.domain.model.EmployeeResponse
import com.prabhanshu.animallassignment.domain.repository.EmployeesRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject


class GetEmployeesUseCase @Inject constructor(private val employeesRepository: EmployeesRepository) {

    operator fun invoke(): Flow<Resource<EmployeeResponse>> = flow {
        emit(Resource.Loading(true))
        try {
            emit(Resource.Success(data = employeesRepository.getEmployees()))
        } catch (e: Exception) {
            emit(Resource.Error(message = e.message.toString()))
        }
    }.flowOn(Dispatchers.IO)
}