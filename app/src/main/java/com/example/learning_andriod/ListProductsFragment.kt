package com.example.learning_andriod

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.learning_andriod.R.id.product_edit_text
import com.example.learning_andriod.R.id.products_list
import com.example.learning_andriod.R.layout.fragment_list_products
import com.example.learning_andriod.domain.Product
import com.example.learning_andriod.repository.ProductRepository

class ListProductsFragment: Fragment() {
    private var listAdapter: ProductsListAdapter? = null

    companion object {
        val TAG: String = ListProductsFragment::class.java.simpleName

        fun getInstance(args: Bundle?): ListProductsFragment {
            val fragment = ListProductsFragment()
            fragment.arguments = args
            return fragment
        }
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
    }

    override fun onDetach() {
        super.onDetach()
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val productRepository = ProductRepository()
        var itemList: List<Product>

        val editText = view.findViewById<EditText>(product_edit_text)
        editText.setOnFocusChangeListener { _, hasFocus ->
            if (!hasFocus) editText.clearFocus()
        }

        val productRecyclerView = view.findViewById<RecyclerView>(products_list)
        productRecyclerView.layoutManager = LinearLayoutManager(context)

        productRepository.getProducts { productList, error ->
            if(error != null) {
                println("Error: $error")
            } else {
                itemList = productList ?: listOf()

                activity?.runOnUiThread {
                    listAdapter = ProductsListAdapter(itemList)
                    productRecyclerView.adapter = listAdapter
                }
            }
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        return  inflater.inflate(fragment_list_products, container, false)

    }
}