package com.example.learning_andriod

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.example.learning_andriod.R.id.tvUsername
import com.example.learning_andriod.R.layout.fragment_commercial_demand

class CommercialDemandFragment : Fragment() {

    private val args: CommercialDemandFragmentArgs by navArgs()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val username = args.username
        val tvUsername = view.findViewById<TextView>(tvUsername)
        tvUsername.text = username
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(fragment_commercial_demand, container, false)
    }
}