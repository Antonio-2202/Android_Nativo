package com.example.learning_andriod

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import com.example.learning_andriod.R.id.btn_delete_call
import com.example.learning_andriod.R.id.btn_put_call
import com.example.learning_andriod.R.layout.fragment_api_calls
import com.example.learning_andriod.repository.ProductRepository

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

        var productRepository = ProductRepository()

        val btnPutCall = view.findViewById<Button>(btn_put_call)
        val btnDeleteCall = view.findViewById<Button>(btn_delete_call)

        btnPutCall.setOnClickListener {  }
        btnDeleteCall.setOnClickListener {  }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        return inflater.inflate(fragment_api_calls, container, false)
    }
}