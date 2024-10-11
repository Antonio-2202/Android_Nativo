package com.example.learning_andriod

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.example.learning_andriod.R.id.product_description
import com.example.learning_andriod.R.id.product_image
import com.example.learning_andriod.R.id.product_list_item
import com.example.learning_andriod.R.id.product_price
import com.example.learning_andriod.R.id.product_title
import com.example.learning_andriod.R.id.products_list
import com.example.learning_andriod.domain.Product
import com.squareup.picasso.Picasso

class ProductsListAdapter(
    private var context: Context,
    private var productList: List<Product>,
    private val onOpenApiCallsFragmentCallback: OnOpenApiCallsFragment,
) : RecyclerView.Adapter<ProductsListAdapter.ViewHolder>() {

    interface OnOpenApiCallsFragment {
        fun onOpenApiCallsFragment()
    }

    fun updateData(newList: List<Product>) {
        productList = newList
        notifyDataSetChanged()
    }

    class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        val productItem: CardView = itemView.findViewById(product_list_item)
        val productTitle: TextView = itemView.findViewById(product_title)
        val productPrice: TextView = itemView.findViewById(product_price)
        val productDescription: TextView = itemView.findViewById(product_description)
        val productImage: ImageView = itemView.findViewById(product_image)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.product_list_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val currentProduct = productList[position]
        holder.productTitle.text = currentProduct.title
        holder.productDescription.text = currentProduct.description
        holder.productPrice.text = "${currentProduct.price}€"

        holder.productItem.setOnClickListener {
            onOpenApiCallsFragmentCallback.onOpenApiCallsFragment()
        }

        Picasso.get().load(currentProduct.imageRoute).into(holder.productImage)

    }

    override fun getItemCount() = productList.size
}