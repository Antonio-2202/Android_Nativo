package com.example.learning_andriod.repository.fake

import com.example.learning_andriod.dao.FakeGmailDao
import com.example.learning_andriod.domain.GmailItem
import java.util.Date
import java.util.UUID

class FakeGmailRepository: FakeGmailDao {
    private var fakeGmailItemList: MutableList<GmailItem> = mutableListOf()

    init {
        fakeGmailItemList.addAll(
            listOf(
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
        )
    }

    override fun getAllGmail(): List<GmailItem> {
        return fakeGmailItemList
    }

    override fun createNewGmail(item: GmailItem) {
        if(fakeGmailItemList.none{ it.id == item.id}) {
            fakeGmailItemList.add(item)
        } else {
            val index = fakeGmailItemList.indexOfFirst { it.id == item.id }
            if(index != -1) fakeGmailItemList[index] = item
        }
    }
}