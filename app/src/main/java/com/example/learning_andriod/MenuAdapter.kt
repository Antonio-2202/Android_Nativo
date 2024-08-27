package com.example.learning_andriod

import android.content.Intent
import android.graphics.drawable.Drawable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.compose.material3.contentColorFor
import androidx.recyclerview.widget.RecyclerView
import com.example.learning_andriod.R.drawable.carretilla_
import com.example.learning_andriod.R.id.card_image
import com.example.learning_andriod.R.id.card_title
import com.example.learning_andriod.R.id.menu_item
import com.example.learning_andriod.R.layout.menu_item_card
import com.example.learning_andriod.constants.AppConstants
import com.example.learning_andriod.domain.MenuItem

class MenuAdapter(private val items: List<MenuItem>) : RecyclerView.Adapter<MenuAdapter.ViewHolder>() {
    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val menuImage: ImageView = itemView.findViewById(card_image)
        val menuText: TextView = itemView.findViewById(card_title)
        val menuItem: CardView = itemView.findViewById(menu_item)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(menu_item_card, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val currentItem = items[position]
        holder.menuText.text = currentItem.title
        holder.menuImage.setImageResource(currentItem.imageResUrl)

        when (currentItem.id) {
            1 -> {
                holder.menuItem.setOnClickListener{
                    val context = it.context
                    val intent = Intent(context, GmailActivity::class.java)
                    context.startActivity(intent)
                }
            }
            2 -> {
                holder.menuItem.setOnClickListener{
                    val context = it.context
                    val intent = Intent(context, PhotoActivity::class.java)
                    context.startActivity(intent)
                }
            }
            3 -> {
                holder.menuItem.setOnClickListener{
                    val context = it.context
                    val intent = Intent(context, BottomNavigationActivity::class.java)
                    context.startActivity(intent)
                }
            }
        }
    }

    override fun getItemCount() = items.size

}