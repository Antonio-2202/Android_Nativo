package com.example.learning_andriod

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.example.learning_andriod.R.id.tvUsernameReversed
import com.example.learning_andriod.R.layout.fragment_visit

class VisitFragment : Fragment() {

    private val args: VisitFragmentArgs by navArgs()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val usernameReversed = args.username
        val tvUsernameReversed = view.findViewById<TextView>(tvUsernameReversed)
        tvUsernameReversed.text = usernameReversed
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(fragment_visit, container, false)
    }
}