package project.phonestore.adapter

import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import project.phonestore.DetailActivity
import project.phonestore.Product
import project.phonestore.R
import java.util.ArrayList

class ProductsAdapter : RecyclerView.Adapter<ProductsAdapter.ProductViewHolder>() {
    inner class ProductViewHolder(itemView: View):RecyclerView.ViewHolder(itemView){
        val image: ImageView = itemView.findViewById(R.id.productImage)
        val price: TextView = itemView.findViewById(R.id.priceTv)
        val title: TextView = itemView.findViewById(R.id.titleTv)
    }

    private val differCallback = object : DiffUtil.ItemCallback<Product>() {
        override fun areItemsTheSame(oldItem: Product, newItem: Product): Boolean {
            return oldItem.thumbnail == newItem.thumbnail
        }

        override fun areContentsTheSame(oldItem: Product, newItem: Product): Boolean {
            return oldItem == newItem
        }
    }
    val differ = AsyncListDiffer(this, differCallback)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
       return ProductViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_product,parent,false))
    }
    private var onItemClickListener: ((Product) -> Unit)? = null
    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        val product = differ.currentList[position]
        holder.itemView.apply {
            Glide.with(this).load(product.thumbnail).into(holder.image)
//            holder.description.text = product.title
            holder.price.text = "$" + product.price
            holder.title.text= product.title

            setOnClickListener {
                    val intent = Intent(context, DetailActivity::class.java)
                    intent.putExtra("title",product.title)
                    intent.putExtra("desc",product.description)
                    intent.putExtra("price",holder.price.text)
                    intent.putExtra("category",product.category)
                    intent.putExtra("brand",product.brand)
                    intent.putExtra("thumbnail",product.thumbnail)
                    intent.putExtra("rating",product.rating)
                    intent.putExtra("stock",product.stock)
                    intent.putExtra("discount",product.discountPercentage)
                    intent.putStringArrayListExtra("imageList", product.images as ArrayList<String>?)
                    context.startActivity(intent)
            }
        }

    }

    override fun getItemCount(): Int {
      return differ.currentList.size
    }

    fun setOnItemClickListener(listener: (Product) -> Unit) {
        onItemClickListener = listener
    }


}