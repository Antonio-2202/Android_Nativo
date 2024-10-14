package com.example.learning_andriod

import android.content.Context
import android.os.Bundle
import android.view.KeyEvent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.learning_andriod.R.id.product_edit_text
import com.example.learning_andriod.R.id.products_list
import com.example.learning_andriod.R.layout.fragment_list_products
import com.example.learning_andriod.apiClient.ApiClient
import com.example.learning_andriod.domain.Product
import com.example.learning_andriod.repository.ProductRepository
import kotlinx.coroutines.launch

class ListProductsFragment: Fragment() {
    private var listAdapter: ProductsListAdapter? = null
    private var itemList: List<Product> = listOf()
    private var onOpenApiCallsFragmentCallback: ProductsListAdapter.OnOpenApiCallsFragment? = null

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
        if(context is ProductsListAdapter.OnOpenApiCallsFragment) {
            onOpenApiCallsFragmentCallback = context;
        } else {
            throw RuntimeException("$context debe implementar OnOpenApiCallsFragment")
        }
    }

    override fun onDetach() {
        super.onDetach()
        onOpenApiCallsFragmentCallback = null
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val editText = view.findViewById<EditText>(product_edit_text)
        editText.setOnEditorActionListener { v, actionId, event ->
            if(actionId == EditorInfo.IME_ACTION_SEARCH || (event != null && event.keyCode == KeyEvent.KEYCODE_ENTER && event.action == KeyEvent.ACTION_DOWN)) {
                val searchQuery = editText.text.toString()
                searchItems(searchQuery)
                closeKeyboard(view)
                editText.clearFocus()
                true
            } else {
                false
            }
        }

        val productRecyclerView = view.findViewById<RecyclerView>(products_list)
        productRecyclerView.layoutManager = LinearLayoutManager(context)

        val productRepository = ProductRepository(ApiClient())
        viewLifecycleOwner.lifecycleScope.launch {
            val productList = productRepository.getProducts()
            itemList = productList ?: listOf()
            listAdapter = ProductsListAdapter(requireContext(), itemList, onOpenApiCallsFragmentCallback!!)
            productRecyclerView.adapter = listAdapter
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        return  inflater.inflate(fragment_list_products, container, false)

    }

    private fun closeKeyboard(view: View) {
        val imm = view.context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(view.windowToken, 0)
    }

    private fun searchItems(inputText: String) {
        val filteredList = itemList.filter { it.title.contains(inputText, ignoreCase = true) || it.description.contains(inputText, ignoreCase = true) }
        listAdapter?.updateData(filteredList)
    }
}