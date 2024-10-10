package com.example.learning_andriod

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.appcompat.app.AppCompatActivity
import com.example.learning_andriod.databinding.ActivityHttpBinding

class HttpActivity : AppCompatActivity() {

    private lateinit var binding: ActivityHttpBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHttpBinding.inflate(layoutInflater)
        setContentView(binding.root)

        if(supportFragmentManager.backStackEntryCount < 1) injectPokemonListFragment(null)
    }

    private fun injectPokemonListFragment(args: Bundle?) {
        val nonNullArgs = args?: Bundle()
        val fragment = ListProductsFragment.getInstance(nonNullArgs)
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.http_main_container, fragment, ListEmployeeFragment.TAG).addToBackStack(ListEmployeeFragment.TAG).commit()
    }

    override fun onBackPressed() {
        if(supportFragmentManager.backStackEntryCount > 1){
            supportFragmentManager.popBackStack()
        } else {
            finish()
        }
    }
}