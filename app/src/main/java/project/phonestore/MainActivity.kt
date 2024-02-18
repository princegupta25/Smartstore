package project.phonestore

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.view.View
import android.widget.ProgressBar
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import project.phonestore.adapter.ProductsAdapter
import project.phonestore.repository.ProductRepository
import project.phonestore.ui.ProductsViewModel
import project.phonestore.ui.ViewModelProviderFactory
import project.phonestore.utils.Resource
import java.util.ArrayList

class MainActivity : AppCompatActivity(){

    lateinit var viewModel: ProductsViewModel
    lateinit var  productadapter : ProductsAdapter
    lateinit var  recyclerView: RecyclerView

    lateinit var productList: ArrayList<Product>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        val productRepository = ProductRepository()
        val viewModelProviderFactory = ViewModelProviderFactory(productRepository)
        viewModel = ViewModelProvider(this,viewModelProviderFactory).get(ProductsViewModel::class.java)

        recyclerView = findViewById(R.id.rvProducts)

        setUpRecyclerView()
        viewModel.products.observe(this, Observer { response->

       when(response) {
           is Resource.Success ->{
               hideProgressBar()
               response.data?.let {  productResponse->
                   productadapter.differ.submitList(productResponse.products.toList()   )
//                   val totalpages = productResponse.total / QUERY_PAGE_SIZE +2
//                   isLastPage = viewModel.productspage == totalpages
               }
           }
           is Resource.Error ->{
               hideProgressBar()
               response.message?.let { message->
                   Log.e("ERROR", "An error occured: $message")

               }
           }

           is Resource.Loading ->{
               showProgressBar()
           }
       }
        })

//        recyclerView = view
    }

    private fun showProgressBar() {
        val progressbar = findViewById<ProgressBar>(R.id.paginationProgressBar)
        progressbar.visibility = View.INVISIBLE
//        isLoading = true
    }

    private fun hideProgressBar() {
        val progressbar = findViewById<ProgressBar>(R.id.paginationProgressBar)
        progressbar.visibility = View.VISIBLE
//        isLoading = false
    }

//    PAGINATION -CODE
//    var isLoading = false
//    var isLastPage = false
//    var isScrolling = false
//
//    val scrollListener = object:RecyclerView.OnScrollListener(){
//        override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
//            super.onScrollStateChanged(recyclerView, newState)
//            if(newState == AbsListView.OnScrollListener.SCROLL_STATE_TOUCH_SCROLL){
//                isScrolling = true
//            }
//        }
//
//        override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
//            super.onScrolled(recyclerView, dx, dy)
//            val layoutManager = recyclerView.layoutManager as LinearLayoutManager
//            val firstVisibleItemPosition = layoutManager.findFirstVisibleItemPosition()
//            val visibleItemCount = layoutManager.childCount
//            val totalItemCount = layoutManager.itemCount
//
//            val isNotLoadingAndNotLastPage = !isLoading && !isLastPage
//            val isAtLastItem = firstVisibleItemPosition + visibleItemCount >= totalItemCount
//            val isNotAtBeginning = firstVisibleItemPosition >=0
//            val isTotalMoreThanVisible = totalItemCount >=  QUERY_PAGE_SIZE
//
//            val shouldPaginate = isNotLoadingAndNotLastPage && isAtLastItem && isNotAtBeginning && isTotalMoreThanVisible &&  isScrolling
//
//            if(shouldPaginate){
//                viewModel.getProducts()
//                isScrolling = false
//            }
//        }
//    }
    private  fun setUpRecyclerView(){
        productadapter = ProductsAdapter()
        recyclerView.apply {
            adapter = productadapter
            layoutManager = LinearLayoutManager(this@MainActivity)
//            addOnScrollListener(this@MainActivity.scrollListener)
        }


    }

}