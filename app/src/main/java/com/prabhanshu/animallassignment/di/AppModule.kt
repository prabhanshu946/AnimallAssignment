package com.prabhanshu.animallassignment.di

import com.prabhanshu.animallassignment.data.network.ApiInterface
import com.prabhanshu.animallassignment.data.repository.EmployeesRepositoryImpl
import com.prabhanshu.animallassignment.domain.repository.EmployeesRepository
import com.prabhanshu.animallassignment.domain.use_case.GetEmployeesUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object AppModule {

    private const val BASE_URL = "https://storage.googleapis.com/"

    private val loggingInterceptor = HttpLoggingInterceptor().apply {
        level = HttpLoggingInterceptor.Level.BODY
    }

    @Provides
    @Singleton
    fun provideRetrofitClient(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(OkHttpClient.Builder().addInterceptor(loggingInterceptor).build())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Provides
    @Singleton
    fun provideApiInterface(retrofit: Retrofit): ApiInterface {
        return retrofit.create<ApiInterface>(ApiInterface::class.java)
    }

    @Provides
    @Singleton
    fun providePostRepository(apiInterface: ApiInterface): EmployeesRepository {
        return EmployeesRepositoryImpl(apiInterface)
    }

    @Provides
    fun provideGetPostUseCase(employeesRepository: EmployeesRepository): GetEmployeesUseCase {
        return GetEmployeesUseCase(employeesRepository)
    }
}