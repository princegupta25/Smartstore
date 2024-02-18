package project.phonestore.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.CreationExtras
import project.phonestore.repository.ProductRepository

class ViewModelProviderFactory(val productRepository: ProductRepository) :ViewModelProvider.Factory{
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return ProductsViewModel(productRepository) as T
    }


}