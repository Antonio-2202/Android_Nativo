package com.example.learning_andriod

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.compose.ui.text.capitalize
import androidx.recyclerview.widget.RecyclerView
import com.example.learning_andriod.R.id.mail_item
import com.example.learning_andriod.R.id.mail_receive_content
import com.example.learning_andriod.R.id.mail_receive_date
import com.example.learning_andriod.R.id.mail_receive_sender
import com.example.learning_andriod.R.id.mail_receive_subtitle
import com.example.learning_andriod.R.layout.mail_row
import com.example.learning_andriod.constants.AppConstants
import com.example.learning_andriod.domain.GmailItem
import com.example.learning_andriod.domain.MenuItem
import com.google.android.material.floatingactionbutton.FloatingActionButton
import java.text.SimpleDateFormat
import java.time.format.DateTimeFormatter
import java.util.Locale

class GmailAdapter(private var items: List<GmailItem>): RecyclerView.Adapter<GmailAdapter.ViewHolder>() {
    private var filteredItems: List<GmailItem> = items

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val gmailSender: TextView = itemView.findViewById(mail_receive_sender)
        val gmailSubtitle: TextView = itemView.findViewById(mail_receive_subtitle)
        val gmailDate: TextView = itemView.findViewById(mail_receive_date)
        val gmailContent: TextView = itemView.findViewById(mail_receive_content)
        val gmailItem: LinearLayout = itemView.findViewById(mail_item)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(mail_row, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val dateFormatter = SimpleDateFormat("MMM dd", Locale.ENGLISH)
        val currentItem = filteredItems[position]
        holder.gmailSender.text = currentItem.sender
        holder.gmailSubtitle.text = currentItem.subtitle
        holder.gmailDate.text = dateFormatter.format(currentItem.date).replaceFirstChar { it.uppercase() }
        holder.gmailContent.text = currentItem.content
        holder.gmailItem.setOnClickListener{
            val context = it.context
            val intent = Intent(context, GmailDetailActivity::class.java).apply {
                putExtra(AppConstants.GMAIL_KEY, currentItem)
            }
            context.startActivity(intent)
        }
    }

    override fun getItemCount() = filteredItems.size

    fun textSearchBox(searchTex: String) {
        filteredItems = if(searchTex.isBlank() || searchTex.isEmpty())  {
            items
        } else {
            items.filter { it.content.contains(searchTex, ignoreCase = true) || it.sender.contains(searchTex, ignoreCase = true) || it.subtitle.contains(searchTex, ignoreCase = true)}
        }
        notifyDataSetChanged()
    }

    fun updateGmailList(newItems: List<GmailItem>) {
        items = newItems
        notifyDataSetChanged()
    }
}