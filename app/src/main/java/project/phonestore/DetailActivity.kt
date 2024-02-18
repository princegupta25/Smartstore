package project.phonestore


import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import project.phonestore.adapter.ImageAdapter
import kotlin.math.log

class DetailActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

    val titleTv  = findViewById<TextView>(R.id.titleTv)
    val descriptionTv  = findViewById<TextView>(R.id.descriptionTv)
    val pricetv  = findViewById<TextView>(R.id.price)
    val categoryTv  = findViewById<TextView>(R.id.categoryTv)
    val discountTv  = findViewById<TextView>(R.id.discount)
    val BrandTv  = findViewById<TextView>(R.id.BrandTv)

    val ratingTv  = findViewById<TextView>(R.id.rating)
    val stockTv  = findViewById<TextView>(R.id.stock)
    val productImage = findViewById<ImageView>(R.id.productImage)


        val imageList = intent.getStringArrayListExtra("imageList")
         titleTv.text =intent.getStringExtra("title")
        descriptionTv.text =intent.getStringExtra("desc")
        pricetv.text =intent.getStringExtra("price")
//        Log.d("prince",intent.getStringExtra("price").toString())
        categoryTv.text =intent.getStringExtra("category")
        discountTv.text =intent.getStringExtra("discount")
//        Log.d("princeeee",intent.getStringExtra("discount").toString())

         Log.d("prince",discountTv.text.toString())
        ratingTv.text = intent.getStringExtra("rating")
        stockTv.text = intent.getStringExtra("stock")
        BrandTv.text = intent.getStringExtra("brand")
        Glide.with(this).load(intent.getStringExtra("thumbnail")).into(productImage)

        val recyclerView: RecyclerView = findViewById(R.id.rvImages)

    // Set up RecyclerView
        recyclerView.layoutManager = LinearLayoutManager(this)
        val adapter = ImageAdapter(this, imageList)
         recyclerView.adapter = adapter
     }
}