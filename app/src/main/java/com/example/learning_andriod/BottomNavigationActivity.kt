package com.example.learning_andriod

import android.os.Bundle
import androidx.activity.ComponentActivity
import com.example.learning_andriod.databinding.ActivityBottomNavigationBinding

class BottomNavigationActivity : ComponentActivity() {

    private lateinit var binding: ActivityBottomNavigationBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityBottomNavigationBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
    }
}