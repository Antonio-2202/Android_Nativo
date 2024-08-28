package com.example.learning_andriod

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.learning_andriod.R.id.action_clientFragment_to_commercialDemandFragment
import com.example.learning_andriod.R.id.btnNavigate
import com.example.learning_andriod.R.layout.fragment_client

class ClientFragment : Fragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val root = inflater.inflate(fragment_client, container, false)

        val btnNavigate = root.findViewById<Button>(btnNavigate)

        btnNavigate.setOnClickListener {
            findNavController().navigate(action_clientFragment_to_commercialDemandFragment)
        }

        return root
    }
}