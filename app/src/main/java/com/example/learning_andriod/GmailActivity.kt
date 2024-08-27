package com.example.learning_andriod

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.TextView
import androidx.activity.ComponentActivity
import androidx.core.widget.addTextChangedListener
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.learning_andriod.R.id.mail_add
import com.example.learning_andriod.R.id.mail_receive_list
import com.example.learning_andriod.R.id.mail_searchBox
import com.example.learning_andriod.R.id.no_items_found
import com.example.learning_andriod.constants.AppConstants
import com.example.learning_andriod.db.AppDatabase
import com.example.learning_andriod.domain.FormMode
import com.example.learning_andriod.domain.GmailItem
import com.example.learning_andriod.extensions.DataMapper
import com.example.learning_andriod.repository.GmailRepository
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.example.learning_andriod.R.layout.activity_gmail as activity_gmail1

class GmailActivity : ComponentActivity() {
    private lateinit var noResultsText: TextView
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: GmailAdapter
    private lateinit var searchBox: EditText
    private lateinit var floatingActionButton: FloatingActionButton
    /*private val gmailRepository: FakeGmailDao = GmailRepository.getInstance()
    private var gmailItemList: List<GmailItem> = gmailRepository.getAllGmail()*/

    private lateinit var gmailRepository: GmailRepository
    private var gmailItemList: MutableList<GmailItem> = mutableListOf()
    private val mapper = DataMapper()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(activity_gmail1)

        val database = AppDatabase.getDatabase(this)
        val gmailDao = database.gmailDao()
        gmailRepository = GmailRepository(gmailDao)

        loadGmailItems()

        recyclerView = findViewById(mail_receive_list)
        noResultsText = findViewById(no_items_found)
        searchBox = findViewById(mail_searchBox)
        floatingActionButton = findViewById(mail_add)
        recyclerView.layoutManager = LinearLayoutManager(this)

        adapter = GmailAdapter(gmailItemList)
        recyclerView.adapter = adapter

        searchBox.addTextChangedListener{
            text -> adapter.textSearchBox(text.toString())
            updateNoResultsText(text.toString())
        }

        floatingActionButton.setOnClickListener{
            openCreateNewGmailActivity()
        }
    }

    override fun onResume() {
        super.onResume()
        loadGmailItems()
        adapter.updateGmailList(gmailItemList)
        updateNoResultsText(searchBox.text.toString())
    }

    private fun loadGmailItems() {
        gmailRepository.getAllGmail { gmailItems ->
            gmailItemList.clear()
            val mappedGmailItems = gmailItems.map { mapper.toUi(it) }
            gmailItemList.addAll(mappedGmailItems)
            adapter.notifyDataSetChanged()
        }
    }

    private fun updateNoResultsText(searchText: String) {
        noResultsText.visibility = if (adapter.itemCount == 0 && searchText.isNotBlank()) {
            View.VISIBLE
        } else {
            View.GONE
        }
    }

    private fun openCreateNewGmailActivity() {
        val intent = Intent(this, GmailFormActivity::class.java).apply {
            putExtra(AppConstants.FORM_MODE, FormMode.CREATE)
        }
        this.startActivity(intent)
    }
}