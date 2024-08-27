package com.example.learning_andriod

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import com.example.learning_andriod.R.id.loginButton
import com.example.learning_andriod.R.id.passwordText
import com.example.learning_andriod.R.id.visibilityIcon
import com.example.learning_andriod.R.layout.activity_test
import com.example.learning_andriod.db.AppDatabase
import com.example.learning_andriod.domain.GmailItem
import com.example.learning_andriod.extensions.DataMapper
import com.example.learning_andriod.repository.GmailRepository
import com.example.learning_andriod.ui.theme.Learning_AndriodTheme
import java.util.Date
import java.util.UUID


class TestActivity : ComponentActivity() {
    private var isVisible = false
    private lateinit var gmailRepository: GmailRepository
    private val mapper = DataMapper()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(activity_test)

        val visibilityIcon: ImageView = findViewById(visibilityIcon)
        val passwordText: TextView = findViewById(passwordText)
        val loginButton: Button = findViewById(loginButton)

        val database = AppDatabase.getDatabase(this)
        val gmailDao = database.gmailDao()
        gmailRepository = GmailRepository(gmailDao)

        insertGmailItems()

        passwordText.setText("******************")

        visibilityIcon.setOnClickListener{
            changePasswordText(visibilityIcon, passwordText, isVisible)
            isVisible = !isVisible
        }

        loginButton.setOnClickListener {
            moveNextScreen()
        }
    }

    private fun insertGmailItems() {
        val gmailItemList: List<GmailItem> = mutableListOf(
            GmailItem(
                id = UUID.randomUUID().toString(),
                sender = "Marcos Escartín",
                subtitle = "Scheduled for Monday at 10 AM",
                content = "Hi Team, please be reminded of our meeting on Monday at 10 AM to discuss the project roadmap and milestones for the next quarter.",
                date = Date()
            ),
            GmailItem(
                id = UUID.randomUUID().toString(),
                sender = "Antonio Martínez",
                subtitle = "Finance Department - Summary of July",
                content = "Dear All, attached is the financial report for the month of July. Please review and provide your feedback by the end of the week.",
                date = Date()
            ),
            GmailItem(
                id = UUID.randomUUID().toString(),
                sender = "Fernando Nieto",
                subtitle = "You're Invited to the Annual Retreat",
                content = "We are excited to invite you to this year's annual company retreat. It's a great opportunity for team building and relaxation.",
                date = Date()
            ),
            GmailItem(
                id = UUID.randomUUID().toString(),
                sender = "Antonio David García",
                subtitle = "Important: Client Feedback",
                content = "Please find attached the feedback from the client regarding our recent project delivery. Let's review it in our next team meeting.",
                date = Date()
            ),
            GmailItem(
                id = UUID.randomUUID().toString(),
                sender = "Manuel Ramos",
                subtitle = "Performance Review - Q3",
                content = "This is a reminder that the quarterly performance reviews are coming up next month. Please prepare your self-evaluations.",
                date = Date()
            ),
            GmailItem(
                id = UUID.randomUUID().toString(),
                sender = "Rafa Salgueiro",
                subtitle = "Action Required: Security Update",
                content = "For your security, please update your account settings and change your password. Follow the instructions in the attached document.",
                date = Date()
            ),
            GmailItem(
                id = UUID.randomUUID().toString(),
                sender = "Gregorio Gimenez",
                subtitle = "Please Review New Policies",
                content = "We have updated our office policies effective from January. Please review the attached document for the changes.",
                date = Date()
            ),
            GmailItem(
                id = UUID.randomUUID().toString(),
                sender = "Pilar Laborda",
                subtitle = "Meet John Doe - New Developer",
                content = "Please join us in welcoming John Doe to our team. John is a seasoned developer with extensive experience in mobile app development.",
                date = Date()
            ),
            GmailItem(
                id = UUID.randomUUID().toString(),
                sender = "Adara Ramon",
                subtitle = "Great Work Team!",
                content = "Congratulations to everyone for hitting the Project Alpha milestone. Your hard work and dedication are greatly appreciated.",
                date = Date()
            ),
            GmailItem(
                id = UUID.randomUUID().toString(),
                sender = "Alejandra Montes",
                subtitle = "System Maintenance Scheduled",
                content = "Please be aware that there will be scheduled system maintenance this weekend. Ensure you save your work to avoid any data loss.",
                date = Date()
            )
        )
        val gmailList = gmailItemList.map { mapper.toDatabase(it) }

        gmailRepository.deleteAllGmail()
        gmailRepository.insertAllGmail(gmailList)
    }

    private fun changePasswordText(visibilityIcon: ImageView, passwordText: TextView, isVisible: Boolean) {
        if (isVisible) {
            visibilityIcon.setImageResource(R.drawable.ic_visibility_off)
            passwordText.setText("MyStrongPassword!!")
        } else {
            var passwordMask = ""
            for (char in passwordText.text) {
                passwordMask += "*"
            }

            visibilityIcon.setImageResource(R.drawable.ic_visibility)
            passwordText.setText(passwordMask)
        }
    }

    private fun moveNextScreen() {
        try {
            val intent = Intent(this, MenuActivity::class.java)
            this.startActivity(intent)
        } catch (e: Error) {
            e.message?.let { Log.e("ERROR", it) }
        }
    }
}

