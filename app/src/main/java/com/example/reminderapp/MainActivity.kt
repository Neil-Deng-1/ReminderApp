package com.example.reminderapp

import android.content.Intent
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

    public lateinit var prefs: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        prefs = getSharedPreferences("user_prefs", MODE_PRIVATE)
        val name = prefs.getString("name", null)
        val email = prefs.getString("email", null)


        if (name != null && email != null) {
            //goes directly to reminder screen if user has logged in
            showReminders()
        } else {
            //shows welcome screen if nothing stored in shared prefs
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
        val intent = Intent(this, ReminderActivity::class.java)
        startActivity(intent)


    }
}