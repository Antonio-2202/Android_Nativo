package com.example.learning_andriod

import android.os.Bundle
import android.text.Editable
import androidx.activity.ComponentActivity
import com.example.learning_andriod.constants.AppConstants
import com.example.learning_andriod.dao.FakeGmailDao
import com.example.learning_andriod.databinding.ActivityGmailFormBinding
import com.example.learning_andriod.domain.FormMode
import com.example.learning_andriod.domain.GmailItem
import com.example.learning_andriod.extensions.toEditable
import com.example.learning_andriod.singletons.GmailRepository
import java.util.Date
import java.util.UUID

class GmailFormActivity : ComponentActivity() {

    private lateinit var binding: ActivityGmailFormBinding
    private val gmailRepository: FakeGmailDao = GmailRepository.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityGmailFormBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val formMode = intent.getSerializableExtra(AppConstants.FORM_MODE) as? FormMode
        val item = intent.getParcelableExtra<GmailItem>(AppConstants.GMAIL_KEY)

        binding.sendButton.text = if(formMode == FormMode.CREATE) "ENVIAR" else "GUARDAR"
        if(item != null) setInformation(item)

        binding.sendButton.setOnClickListener {
            createMailItem(item)
        }

    }

    private fun createMailItem(mailItem: GmailItem?) {
        val sender = binding.sendSender.text.toString()
        val title = binding.sendTitle.text.toString()
        val content = binding.sendMessage.text.toString()
        val gmailItem: GmailItem

        if(mailItem != null) {
            gmailItem = GmailItem(
                id = mailItem.id,
                sender = sender,
                subtitle = title,
                content = content,
                date = mailItem.date
            )
        } else {
            gmailItem = GmailItem(
                id = UUID.randomUUID().toString(),
                sender = sender,
                subtitle = title,
                content = content,
                date = Date()
            )
        }

        gmailRepository.createNewGmail(gmailItem)
        finish()
    }

    private fun setInformation(mailItem: GmailItem?) {
        binding.sendSender.text = mailItem?.sender?.toEditable()
        binding.sendTitle.text = mailItem?.subtitle?.toEditable()
        binding.sendMessage.text = mailItem?.content?.toEditable()
    }
}