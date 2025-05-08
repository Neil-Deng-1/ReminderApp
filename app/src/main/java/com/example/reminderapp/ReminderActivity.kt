package com.example.reminderapp

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class ReminderActivity : AppCompatActivity() {
    private lateinit var addReminderBtn : Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_reminders)

        addReminderBtn = findViewById(R.id.add_button)
        addReminderBtn.setOnClickListener {
            val intent = Intent(this, AddActivity::class.java)
            startActivity(intent)
        }

        var firebase : FirebaseDatabase = FirebaseDatabase.getInstance()

        // Need a reference to write
        var reference : DatabaseReference = firebase.getReference("country")
        // Now we can associate the value 'USA' with the key 'country'
        reference.setValue("USA")
    }
}