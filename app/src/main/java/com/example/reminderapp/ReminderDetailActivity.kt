package com.example.reminderapp

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class ReminderDetailActivity : AppCompatActivity() {
    private lateinit var titleTV : TextView
    private lateinit var dateTimeTV : TextView
    private lateinit var descTV : TextView
    private lateinit var goBackButton : Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_reminder_detail)

        val title = intent.getStringExtra("title") ?: "No Title"
        val date = intent.getStringExtra("date") ?: "No Date"
        val time = intent.getStringExtra("time") ?: "No Time"
        val desc = intent.getStringExtra("description") ?: "No Description"

        titleTV = findViewById(R.id.titleText)
        dateTimeTV = findViewById(R.id.dateTimeText)
        descTV = findViewById(R.id.descriptionText)
        goBackButton = findViewById(R.id.goBack)

        titleTV.text = title
        dateTimeTV.text = "$date at $time"
        descTV.text = desc

        goBackButton.setOnClickListener {finish()}
    }
}