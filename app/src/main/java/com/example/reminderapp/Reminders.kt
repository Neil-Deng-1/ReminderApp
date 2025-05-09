package com.example.reminderapp

import android.util.Log
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import java.text.SimpleDateFormat
import java.util.Locale

class Reminders {
    private lateinit var reminders : ArrayList<Reminder>
    private lateinit var user : String

    constructor(user : String) {
        this.user = user
        reminders = ArrayList<Reminder>()
    }

    fun queryReminders(onComplete: () -> Unit) {
        var firebase : FirebaseDatabase = FirebaseDatabase.getInstance()
        var reference : DatabaseReference = firebase.getReference(user);

        reference.get().
            addOnSuccessListener { dataSnapshot ->
                for (reminderSnapshot in dataSnapshot.children) {
                    val reminder = reminderSnapshot.getValue(Reminder::class.java)
                    reminder?.let { reminders.add(it)}
                }

                reminders = ArrayList<Reminder>(sortReminders())

                onComplete()
            }
            .addOnFailureListener { exception ->
                Log.w("MainActivity", "Error fetching reminders", exception)
            }
    }

    fun getReminders() : ArrayList<Reminder> {
        return reminders
    }


    fun sortReminders(): List<Reminder> {
        val sdf = SimpleDateFormat("yyyy-MM-dd HH:mm", Locale.getDefault())

        return reminders.sortedWith(compareBy { reminder ->
            sdf.parse("${reminder.date} ${reminder.time}")
        })
    }
}