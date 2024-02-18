package project.phonestore.ui

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import project.phonestore.Product
import project.phonestore.ProductResponse
import project.phonestore.repository.ProductRepository
import project.phonestore.utils.Resource
import retrofit2.Response

class ProductsViewModel(
    val productRepository :ProductRepository
):ViewModel() {
    val products: MutableLiveData<Resource<ProductResponse>> = MutableLiveData()
    var productspage =1
    var productsResponse: ProductResponse? = null

    init {
        getProducts()
    }

    fun  getProducts() = viewModelScope.launch {
        products.postValue(Resource.Loading())
        val response = productRepository.getProduct()
        products.postValue(handleProductsResponse(response))
    }

    private fun handleProductsResponse(response: Response<ProductResponse>): Resource<ProductResponse>? {
        if(response.isSuccessful){
            response.body()?.let {  resultResponse->
//                PAGINATION -Code
//                productspage++
//                if(productsResponse == null){
//                    productsResponse = resultResponse
//                }else{
//                    val oldarticles = productsResponse?.products
//                    val newarticles = resultResponse.products
//                    oldarticles?.addAll(newarticles)
//                }
                return Resource.Success(productsResponse?:resultResponse)

            }
        }
        return Resource.Error(response.message())

    }

}