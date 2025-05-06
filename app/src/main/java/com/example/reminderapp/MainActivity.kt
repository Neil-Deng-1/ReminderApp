package com.example.reminderapp

import android.content.SharedPreferences
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {

    private lateinit var prefs: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        prefs = getSharedPreferences("user_prefs", MODE_PRIVATE)
        val name = prefs.getString("name", null)
        val email = prefs.getString("email", null)


        if (name != null && email != null) {
            showReminders()
        } else {
           showWelcome()
        }
    }

    private fun showWelcome() {
        setContentView(R.layout.activity_welcome)

        val nameEt = findViewById<EditText>(R.id.name_et)
        val emailEt = findViewById<EditText>(R.id.email_et)
        val signupBtn = findViewById<Button>(R.id.signup)

        signupBtn.setOnClickListener {
            val nameInput = nameEt.text.toString().trim()
            val emailInput = emailEt.text.toString().trim()

            if (nameInput.isNotEmpty() && emailInput.isNotEmpty()) {
                prefs.edit()
                    .putString("name", nameInput)
                    .putString("email", emailInput)
                    .apply()

                showReminders()
            } else {
                Toast.makeText(this, "Please enter both name and email", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun showReminders() {
        setContentView(R.layout.activity_reminders)

    }
}