package com.example.lab5

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import kotlinx.android.synthetic.main.fragment_add.view.*
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class AddFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_add, container, false)
        view.addNewItemButton.setOnClickListener {
            val logging = HttpLoggingInterceptor()
            logging.setLevel(HttpLoggingInterceptor.Level.BASIC)
            val client = OkHttpClient.Builder()
                .addInterceptor(logging)
                .build()

            val retrofit = Retrofit.Builder()
                .baseUrl("https://jsonplaceholder.typicode.com")
                .addConverterFactory(GsonConverterFactory.create()).client(client).build()

            val apiService = retrofit.create(ApiService::class.java)

            val userId = view.userIdEditText.text.toString().toInt()
            val title = view.titleEditText.text.toString()
            val status = view.statusEditText.text.toString().toBoolean()

            apiService.addTodo(userId, title, status).enqueue(object : Callback<Task> {
                override fun onFailure(call: Call<Task>, t: Throwable){
                    Log.e("Error", t.message!!)
                }

                override fun onResponse(call: Call<Task>, response: Response<Task>) {
                    Log.e("Response size: ", response.body()!!.title+"")
                }
            })
            Navigation.findNavController(view).navigate(R.id.navigateFromAddToList)
        }

        return view
    }

}