package com.example.learning_andriod

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.learning_andriod.R.id.btnNavigate2
import com.example.learning_andriod.R.id.tvUsername
import com.example.learning_andriod.R.layout.fragment_commercial_demand

class CommercialDemandFragment : Fragment() {

    private val args: CommercialDemandFragmentArgs by navArgs()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val username = args.username
        val tvUsername = view.findViewById<TextView>(tvUsername)
        tvUsername.text = username

        val btnNavigate2 = view.findViewById<Button>(btnNavigate2)

        btnNavigate2.setOnClickListener {
            findNavController().navigate(
                CommercialDemandFragmentDirections.actionCommercialDemandFragmentToVisitFragment(
                    username = args.username.reversed()
                )
            )
        }

    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(fragment_commercial_demand, container, false)
    }
}