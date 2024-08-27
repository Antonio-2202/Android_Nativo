package com.example.learning_andriod

import android.annotation.TargetApi
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.ComponentActivity
import androidx.activity.OnBackPressedCallback
import com.example.learning_andriod.R.id.detail_go_back
import com.example.learning_andriod.R.id.go_detail
import com.example.learning_andriod.R.id.mail_content
import com.example.learning_andriod.R.id.mail_date
import com.example.learning_andriod.R.id.mail_sender
import com.example.learning_andriod.R.id.mail_title
import com.example.learning_andriod.R.layout.activity_gmail_detail
import com.example.learning_andriod.constants.AppConstants
import com.example.learning_andriod.domain.FormMode
import com.example.learning_andriod.domain.GmailItem
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class GmailDetailActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(activity_gmail_detail)
        setInformation()

        val backItem: ImageView = findViewById(detail_go_back)
        backItem.setOnClickListener{
            onBackPressedDispatcher.onBackPressed()
        }

    }

    private fun setInformation() {
        val dateFormatter = SimpleDateFormat("MMM dd", Locale.ENGLISH)
        val mailItem: GmailItem? = intent.getParcelableExtra(AppConstants.GMAIL_KEY)

        val mailSender: TextView = findViewById(mail_sender)
        val mailTitle: TextView = findViewById(mail_title)
        val mailDate: TextView = findViewById(mail_date)
        val mailContent: TextView = findViewById(mail_content)

        mailSender.text = mailItem?.sender ?: "..."
        mailTitle.text = mailItem?.subtitle ?: "..."
        mailContent.text = mailItem?.content ?: "..."
        mailDate.text = dateFormatter.format(mailItem?.date ?: Date()).replaceFirstChar { it.uppercase() }

        val mailDetailButton: ImageView = findViewById(go_detail)
        mailDetailButton.setOnClickListener{
            val intent = Intent(this, GmailFormActivity::class.java).apply {
                putExtra(AppConstants.FORM_MODE, FormMode.EDIT)
                putExtra(AppConstants.GMAIL_KEY, mailItem)
            }
            this.startActivity(intent)
            finish()
        }
    }
}