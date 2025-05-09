package com.example.reminderapp

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class ReminderActivity : AppCompatActivity() {
    private lateinit var addReminderBtn : Button
    private lateinit var reminders : Reminders
    private lateinit var remindersArr : ArrayList<Reminder>
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: ReminderAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_reminders)

        recyclerView = findViewById(R.id.recycler_view)
        addReminderBtn = findViewById(R.id.add_button)
        addReminderBtn.setOnClickListener {
            val intent = Intent(this, AddActivity::class.java)
            startActivity(intent)
        }

        reminders = MainActivity.reminders
        remindersArr = reminders.getReminders()
        for (r in remindersArr) {
            Log.w("MainActivity", "title: " + r.title + "date: " + r.date + "time: " + r.time + "desc: " + r.description)
        }

        adapter = ReminderAdapter(remindersArr)

        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = adapter
    }
}