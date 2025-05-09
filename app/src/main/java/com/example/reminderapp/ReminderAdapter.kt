package com.example.reminderapp

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class ReminderAdapter(private val reminders: List<Reminder>,
                      private val onClick: (Reminder) -> Unit) :
    RecyclerView.Adapter<ReminderAdapter.ReminderViewHolder>() {

    class ReminderViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val title: TextView = itemView.findViewById(R.id.title)
        val dateTime: TextView = itemView.findViewById(R.id.date_time)
        val description: TextView = itemView.findViewById(R.id.description)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ReminderViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.reminder_item, parent, false)
        return ReminderViewHolder(view)
    }

    override fun onBindViewHolder(holder: ReminderViewHolder, position: Int) {
        val reminder = reminders[position]
        holder.title.text = reminder.title
        holder.dateTime.text = "${reminder.date} at ${reminder.time}"
        holder.description.text = reminder.description

        holder.itemView.setOnClickListener {onClick(reminder)}
    }

    override fun getItemCount(): Int = reminders.size
}
