package com.example.reminderapp

class Reminder {
    private lateinit var title: String
    private lateinit var date: String
    private lateinit var time: String
    private lateinit var description: String

    constructor (title : String, date : String, time : String, description : String) {
        this.title = title
        this.date = date
        this.time = time
        this.description = description
    }

    fun getTitle() : String {
        return title;
    }

    fun getDate() : String {
        return date;
    }

    fun getTime() : String {
        return time;
    }

    fun getDescription() : String {
        return description;
    }
}