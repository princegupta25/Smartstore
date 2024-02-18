package project.phonestore.api

import project.phonestore.Product
import project.phonestore.ProductResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiInterface {

    @GET("products")
    suspend fun getProducts(
        @Query("page")
        pageNumber: Int = 1,
    ): Response<ProductResponse>
}