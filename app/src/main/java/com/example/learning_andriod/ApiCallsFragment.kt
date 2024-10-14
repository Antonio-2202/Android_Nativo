package com.example.learning_andriod

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.compose.ui.text.toUpperCase
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import com.example.learning_andriod.R.id.btn_delete_call
import com.example.learning_andriod.R.id.btn_put_call
import com.example.learning_andriod.R.layout.fragment_api_calls
import com.example.learning_andriod.apiClient.ApiClient
import com.example.learning_andriod.domain.WsProduct
import com.example.learning_andriod.repository.ProductRepository
import kotlinx.coroutines.launch

class ApiCallsFragment : Fragment() {
    companion object {
        val TAG: String = ApiCallsFragment::class.java.simpleName

        fun getInstance(args: Bundle?): ApiCallsFragment {
            val fragment = ApiCallsFragment()
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

        val btnPutCall = view.findViewById<Button>(btn_put_call)
        val btnDeleteCall = view.findViewById<Button>(btn_delete_call)
        val productRepository = ProductRepository(ApiClient())

        btnPutCall.setOnClickListener {
            val product = WsProduct("Test Product", 13.5, "Lorem ipsum set", "https://fakestoreapi.com/img/61IBBVJvSDL._AC_SY879_.jpg", "electronic")
            viewLifecycleOwner.lifecycleScope.launch {
                val isSuccess = productRepository.createProducts(product)
                println(isSuccess.toString().uppercase())
                if(isSuccess) {
                    parentFragmentManager.popBackStack()
                }
            }
        }
        btnDeleteCall.setOnClickListener {
            viewLifecycleOwner.lifecycleScope.launch {
                val isSuccess = productRepository.deleteProduct(1)
                println(isSuccess.toString().uppercase())
                if (isSuccess) {
                    parentFragmentManager.popBackStack()
                }
            }
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        return inflater.inflate(fragment_api_calls, container, false)
    }
}