package com.example.shikh.updater.model

/**
 * Created by shikh on 11-02-2018.
 */
data class task(
        val task_userid: String,
        val task_content: String,
        val task_done: Boolean,
        val task_date: String,
        val task_reminder: String
)
