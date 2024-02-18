package project.phonestore.repository

import project.phonestore.api.ApiInterface
import project.phonestore.api.RetrofitInstance

class ProductRepository {
    val apiClient = RetrofitInstance()
    suspend fun  getProduct()= apiClient.getRetrofitInstance().getProducts()
}