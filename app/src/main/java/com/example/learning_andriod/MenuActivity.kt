package com.example.learning_andriod

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.learning_andriod.R.id.menuList
import com.example.learning_andriod.R.layout.activity_menu
import com.example.learning_andriod.repository.fake.FakeMenuRepository

class MenuActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(activity_menu)

        val recyclerView: RecyclerView = findViewById(menuList)
        recyclerView.layoutManager = GridLayoutManager(this, 2)

        val menuItemRepository = FakeMenuRepository()
        val menuItemList = menuItemRepository.getAllMenuItem()
        val adapter = MenuAdapter(menuItemList)

        recyclerView.adapter = adapter
    }
}