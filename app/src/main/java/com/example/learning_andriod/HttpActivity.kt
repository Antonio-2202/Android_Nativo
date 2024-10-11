package com.example.learning_andriod

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.appcompat.app.AppCompatActivity
import com.example.learning_andriod.databinding.ActivityHttpBinding

class HttpActivity : AppCompatActivity(), ProductsListAdapter.OnOpenApiCallsFragment {

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
        transaction.replace(R.id.http_main_container, fragment, ListProductsFragment.TAG).addToBackStack(ListProductsFragment.TAG).commit()
    }

    private fun injectApiCallsFragment(args: Bundle?) {
        val nonNullArgs = args?: Bundle()
        val fragment = ApiCallsFragment.getInstance(nonNullArgs)
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.http_main_container, fragment, ApiCallsFragment.TAG).addToBackStack(ApiCallsFragment.TAG).commit()
    }

    override fun onBackPressed() {
        if(supportFragmentManager.backStackEntryCount > 1){
            supportFragmentManager.popBackStack()
        } else {
            finish()
        }
    }

    override fun onOpenApiCallsFragment() {
        injectApiCallsFragment(null)
    }

}