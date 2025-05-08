package com.example.reminderapp

import android.content.Context
import androidx.work.Worker
import androidx.work.WorkerParameters

class EmailReminderWorker(ctx: Context, params: WorkerParameters) : Worker(ctx, params) {
    override fun doWork(): Result {
        val email = inputData.getString("email") ?: return Result.failure()
        val subject = inputData.getString("subject") ?: "Reminder"
        val body = inputData.getString("body") ?: "Don't forget!"

        return try {
            val sender = GmailSender("cmsc436sender@gmail.com", "sgxw gota eoql mopy")
            sender.sendEmail(email, subject, body)
            Result.success()
        } catch (e: Exception) {
            e.printStackTrace()
            Result.failure()
        }
    }
}
