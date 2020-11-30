package com.example.lab5
import retrofit2.Call
import retrofit2.http.*

interface ApiService {
    @GET("todos/")
    fun getTodos(): Call<List<Task>>

    @GET("todos/{id}/")
    fun getTodoById(@Path("id") todoInt: Int): Call<Task>

    @FormUrlEncoded
    @POST("todos/")
    fun addTodo(
        @Field("userId") userId: Int,
        @Field("title") title: String,
        @Field("completed") completed: Boolean): Call<Task>
}