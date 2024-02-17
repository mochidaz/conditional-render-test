package com.example.lambdadelta
import retrofit2.Call
import retrofit2.http.GET

interface ApiService {
    @GET("/lambda_calculus")
    fun getLambdaCalculusExplanation(): Call<ExplanationResponse>

    @GET("/higher_order_functions")
    fun getHigherOrderFunctionsExplanation(): Call<ExplanationResponse>

    @GET("/functional_programming_basics")
    fun getFunctionalProgrammingBasicsExplanation(): Call<ExplanationResponse>
}