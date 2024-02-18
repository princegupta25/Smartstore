package project.phonestore.api

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitInstance {

    private lateinit var retrofitInterface: ApiInterface

    fun getRetrofitInstance(): ApiInterface {

        val retrofit: Retrofit = Retrofit.Builder()
            .baseUrl("https://dummyjson.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        retrofitInterface = retrofit.create(ApiInterface::class.java)
        return retrofitInterface
    }
}