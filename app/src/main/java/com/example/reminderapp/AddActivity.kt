package com.example.reminderapp

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.work.Data
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkManager
import java.util.concurrent.TimeUnit

class AddActivity: AppCompatActivity() {
    private lateinit var prefs: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_reminder)

        prefs = getSharedPreferences("user_prefs", Context.MODE_PRIVATE)
        val email = prefs.getString("email", null)

        //for now, all this code does is send an email when you submit
        val submitButton = findViewById<Button>(R.id.submit)

        //Sends an email to the provided address after 1 minute delay
        submitButton.setOnClickListener {
            val title = findViewById<EditText>(R.id.reminder_title_et).text.toString()
            val body = findViewById<EditText>(R.id.description_body).text.toString()
            val inputData = Data.Builder()
                .putString("email", email)
                .putString("subject", title)
                .putString("body", body)
                .build()
            //For now, initial delay fixed at 1 minute
            val workRequest = OneTimeWorkRequestBuilder<EmailReminderWorker>()
                .setInitialDelay(1, TimeUnit.MINUTES)
                .setInputData(inputData)
                .build()

            WorkManager.getInstance(this).enqueue(workRequest)
            Toast.makeText(this, "Email sent!", Toast.LENGTH_SHORT).show()
            finish()
        }








    }

}